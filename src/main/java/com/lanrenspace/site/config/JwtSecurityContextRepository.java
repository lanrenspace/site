package com.lanrenspace.site.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@Slf4j
public class JwtSecurityContextRepository implements ServerSecurityContextRepository {

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String path = exchange.getRequest().getPath().toString();
        log.info("path: {}", path);
        if ("/login".equals(path)) {
            return Mono.empty();
        }
        if ("/openApi/register".equals(path)) {
            return this.buildAnonymousSecurityContext();
        }
        return Mono.empty();
    }


    /**
     * 构建匿名授权
     */
    private Mono<SecurityContext> buildAnonymousSecurityContext() {
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("NONE"));
        return Mono.just(new SecurityContextImpl(new AnonymousAuthenticationToken("anonymous", "anonymous", auths)));
    }
}
