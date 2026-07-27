package com.ticketsystem.apigateway.route;

import org.springframework.cloud.gateway.server.mvc.common.MvcUtils;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.setPath;

@Configuration
public class InventoryServiceRoutes {

    @Bean
    public RouterFunction<ServerResponse> inventoryRoutes() {
        return GatewayRouterFunctions.route("inventory-service")
                .route(RequestPredicates.path("/api/v1/inventory/venue/{venueId}"),
                        request -> forwardWithPathVariable(request, "venueId", "http://localhost:8080/api/v1/inventory/venue/"))
                .route(RequestPredicates.path("/api/v1/inventory/event/{eventId}"),
                        request -> forwardWithPathVariable(request, "eventId", "http://localhost:8080/api/v1/inventory/event/"))
                .build();
    }
    private static ServerResponse forwardWithPathVariable(ServerRequest request,
                                                          String pathVariable,
                                                          String baseUrl) throws Exception {
        String value = request.pathVariable(pathVariable);
        MvcUtils.setRequestUrl(request, URI.create(baseUrl + value));
        return HandlerFunctions.http().handle(request);
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceApiDocs() {
        return GatewayRouterFunctions.route("inventory-service-api-docs")
                .route(RequestPredicates.path("/docs/inventoryservice/v3/api-docs"),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8080"))
                .before(setPath("v3/api-docs"))
                .build();
    }
}