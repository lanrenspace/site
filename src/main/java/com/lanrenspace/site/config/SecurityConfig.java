package com.lanrenspace.site.config;

import com.lanrenspace.site.config.handler.AccessDeniedHandler;
import com.lanrenspace.site.config.handler.AuthenticationFailHandler;
import com.lanrenspace.site.config.handler.AuthenticationSuccessHandler;
import com.lanrenspace.site.config.handler.LogoutSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@Slf4j
@EnableWebFluxSecurity
public class SecurityConfig {


    /**
     * http请求路径权限与过滤链配置
     *
     * @param httpSecurity
     * @return
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        log.info("load security webFilterChain.....................");
        httpSecurity.csrf().disable().cors().disable().httpBasic().disable()
                .securityContextRepository(new JwtSecurityContextRepository())
                .formLogin()
                .authenticationFailureHandler(new AuthenticationFailHandler())
                .loginPage("/login")
                .authenticationSuccessHandler(new AuthenticationSuccessHandler())
                .and()
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/login", "/openApi/options", "/openApi/createOption").permitAll()
                .anyExchange()
                .authenticated()
                .and().logout().logoutSuccessHandler(new LogoutSuccessHandler())
                .and().exceptionHandling().accessDeniedHandler(new AccessDeniedHandler());
        return httpSecurity.build();
    }

    /**
     * 设置密码验证器
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
