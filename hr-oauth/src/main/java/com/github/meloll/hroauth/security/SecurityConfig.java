package com.github.meloll.hroauth.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.io.InputStream;
import java.security.KeyStore;
import java.time.Duration;
import java.util.UUID;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig   {


    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        return http.formLogin(withDefaults()).build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain standardSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests(auth -> auth.antMatchers("/oauth2/***").authenticated())
                .httpBasic(withDefaults());

        return http.build();
    }


    @Bean
    @Order(2)
    public SecurityFilterChain standardSecurityFilterChain2(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests(auth -> auth.antMatchers("/users/***").hasAuthority("SCOPE_ROLE_OPERATOR")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }



    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        RegisteredClient loginClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("myappname123")
                .clientSecret(passwordEncoder.encode("myappsecret123"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("read")
                .scope("write")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofHours(1)).build())
                .build();

        return new InMemoryRegisteredClientRepository(loginClient);
    }


    @Bean
    public AuthorizationServerSettings providerSettings(OauthProperties oauthProperties) {
        return AuthorizationServerSettings.builder().issuer(oauthProperties.getProviderUri()).build();
    }

    @Bean
    public JWKSet jwkSet(OauthProperties oauthProperties) throws Exception {
        final InputStream inputStream = new ClassPathResource(oauthProperties.getJksProperties().getPath()).getInputStream();

        final KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream,oauthProperties.getJksProperties().getStorePass().toCharArray());

        RSAKey rsaKey = RSAKey.load(keyStore,oauthProperties.getJksProperties().getAlias(),oauthProperties.getJksProperties().getKeyPass().toCharArray());

        return new JWKSet(rsaKey);
    }

    @Bean
    public  JWKSource<SecurityContext> jwkSource(JWKSet jwkSet){
        return ((jwkSelector, securityContext) -> jwkSelector.select(jwkSet));
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource){
        return new NimbusJwtEncoder(jwkSource);

    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource){
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:8765/hr-oauth/oauth2/jwks").build();

    }


}
