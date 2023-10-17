package com.btbox.monitor.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import jakarta.servlet.DispatcherType;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

import static java.nio.file.attribute.AclEntryPermission.DELETE;
import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;

@Configuration(proxyBeanMethods = false)
public class SecuritySecureConfig {

        private final AdminServerProperties adminServer;

        private final SecurityProperties security;

        public SecuritySecureConfig(AdminServerProperties adminServer, SecurityProperties security) {
                this.adminServer = adminServer;
                this.security = security;
        }


        @Bean
        protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
                successHandler.setTargetUrlParameter("redirectTo");
                successHandler.setDefaultTargetUrl(this.adminServer.getContextPath() + "/");
                System.out.println("this.adminServer.getContextPath() = " + this.adminServer.getContextPath());


                http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests //
                        .requestMatchers(new AntPathRequestMatcher(this.adminServer.getContextPath() + "/assets/**"))
                        .permitAll()
                        .requestMatchers(new AntPathRequestMatcher(this.adminServer.getContextPath() + "/actuator/info"))
                        .permitAll()
                        .requestMatchers(new AntPathRequestMatcher(this.adminServer.getContextPath() + "/actuator/health"))
                        .permitAll()
                        .requestMatchers(new AntPathRequestMatcher(this.adminServer.getContextPath() + "/login"))
                        .permitAll()
                        .dispatcherTypeMatchers(DispatcherType.ASYNC)
                        .permitAll() // https://github.com/spring-projects/spring-security/issues/11027
                        .anyRequest()
                        .authenticated())
                        .formLogin(
                                        (formLogin) -> formLogin.loginPage(this.adminServer.getContextPath() + "/login").successHandler(successHandler))
                        .logout((logout) -> logout.logoutUrl(this.adminServer.getContextPath() + "/logout"))
                        .httpBasic(Customizer.withDefaults());

                http.csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                                .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                                .ignoringRequestMatchers(
                                                new AntPathRequestMatcher(this.adminServer.getContextPath() + "/instances", POST.toString()),
                                                new AntPathRequestMatcher(this.adminServer.getContextPath() + "/instances/*", DELETE.toString()),
                                                new AntPathRequestMatcher(this.adminServer.getContextPath() + "/actuator/**")
                                ));

                http.rememberMe((rememberMe) -> rememberMe.key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600));

                return http.build();

        }

}