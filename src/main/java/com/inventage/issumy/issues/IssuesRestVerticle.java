package com.inventage.issumy.issues;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.platform.Verticle;

/**
 * handles the REST calls.
 */
public class IssuesRestVerticle extends Verticle {

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
                req.response().headers().set("Content-Type", "text/plain");
                req.response().end("Hello ----- issumy");
            }
        }).listen(8080);
    }
}