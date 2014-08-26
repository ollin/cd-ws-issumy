package com.inventage.issumy.issues.test.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.vertx.testtools.VertxAssert.assertThat;
import static org.vertx.testtools.VertxAssert.testComplete;

import com.inventage.issumy.issues.IssuesRestVerticle;
import com.inventage.issumy.issues.IssuesStarterVerticle;
import com.inventage.issumy.issues.IssuesVerticle;
import org.junit.Test;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.java.core.json.JsonArray;
import org.vertx.testtools.TestVerticle;

/**
 * integration test.
 */
public class IssuesViaRestTest extends TestVerticle {

    private final String EMPTY_JSON_ARRAY = "[]";

    @Test
    public void shouldReturnEmptyListOfIssues() throws Exception {
        // when
        HttpClient client = vertx.createHttpClient()
            .setHost("localhost")
            .setPort(8080);

        client.getNow("/issues", event -> event.bodyHandler(bodyEvent -> {
            String result = bodyEvent.toString();
            assertThat(result, is(EMPTY_JSON_ARRAY));
            testComplete();
        }));
    }

    @Override
    public void start() {
        initialize();

        container.deployVerticle(IssuesStarterVerticle.class.getName(), event -> {
            if (event.failed()) {
                throw new IllegalStateException("deployment of module failed", event.cause());
            }
            startTests();
        });
    }
}
