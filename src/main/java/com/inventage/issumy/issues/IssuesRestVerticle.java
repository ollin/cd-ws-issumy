package com.inventage.issumy.issues;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.platform.Verticle;

/**
 * handels the REST calls.
 */
public class IssuesRestVerticle extends Verticle {

    @Override
    public void start() {
        RouteMatcher rm = new RouteMatcher();

        rm.get("/issues", new Handler<HttpServerRequest>() {
            @Override
            public void handle(HttpServerRequest req) {
                vertx.eventBus().send("com.inventage.issumy.issues", "", new Handler<Message<JsonArray>>() {
                    @Override
                    public void handle(Message<JsonArray> msg) {
                        req.response().headers().set("Content-Type", "application/json");
                        req.response().end(msg.body().encode());
                    }
                });
            }
        });

        vertx.createHttpServer().requestHandler(rm).listen(8080);
        container.logger().info("IssuesRestVerticle started");
    }
}