package com.lanrenspace.site.routers;

import com.lanrenspace.site.base.ServerRouters;
import com.lanrenspace.site.service.OpenApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@Configuration
public class OpenApiRouters extends ServerRouters {

    public OpenApiRouters() {
        super("/openApi");
    }

    @Bean
    public RouterFunction<ServerResponse> apiRouters(OpenApiService openApiService) {
        return this.buildGetRoute("/options", openApiService::getOptions)
                .and(this.buildPostRoute("/createOption", openApiService::createOption))
                .and(this.buildPostRoute("/uploadFile", openApiService::uploadFile));
    }
}
