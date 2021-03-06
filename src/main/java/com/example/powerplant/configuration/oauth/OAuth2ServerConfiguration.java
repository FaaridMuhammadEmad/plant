package com.example.powerplant.configuration.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
public class OAuth2ServerConfiguration {

    private static final String SERVER_RESOURCE_ID = "oauth2-server";
    private static InMemoryTokenStore tokenStore = new InMemoryTokenStore ();

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource ();
        dataSource.setUrl (datasourceUrl);
        dataSource.setUsername (dbUsername);
        dataSource.setPassword (dbPassword);
        return dataSource;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource ());
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenStore (tokenStore).resourceId (SERVER_RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .headers ()
                    .frameOptions ()
                    .disable ()
                    .and ()
                    .authorizeRequests ()
                    .antMatchers ( "/api/1.0/user", "/login").permitAll ()
                    .antMatchers("/swagger-ui/**").permitAll()
                    .antMatchers("/swagger-ui.html/**").permitAll()
                    .antMatchers ("/**").authenticated ();
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private PasswordEncoder passwordEncoder;

        /**
         * Setting up the endpointsconfigurer authentication manager.
         * The AuthorizationServerEndpointsConfigurer defines the authorization and token endpoints and the token services.
         *
         * @param endpoints
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .authenticationManager (authenticationManager)
                    .tokenStore (tokenStore);
        }
        /**
         * Setting up the clients with a clientId, a clientSecret, a scope, the grant types and the authorities.
         *
         * @param clients
         * @throws Exception
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory ()
                    .withClient ("socol")
                    .authorizedGrantTypes ("client_credentials", "password", "refresh_token")
                    .authorities ("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "refresh_token")
                    .scopes ("read", "write", "trust")
                    .resourceIds (SERVER_RESOURCE_ID)
                    .refreshTokenValiditySeconds(24 * 60 * 60 * 30)
                    .accessTokenValiditySeconds (24 * 60 * 60)
                    .secret (passwordEncoder.encode ("secret")).refreshTokenValiditySeconds (1400);
        }
        /**
         * We here defines the security constraints on the token endpoint.
         * We set it up to isAuthenticated, which returns true if the user is not anonymous
         *
         * @param security the AuthorizationServerSecurityConfigurer.
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security
                    .checkTokenAccess ("isAuthenticated()");
        }
    }
}