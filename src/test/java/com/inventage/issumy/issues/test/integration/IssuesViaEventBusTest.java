package com.inventage.issumy.issues.test.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.vertx.testtools.VertxAssert.assertThat;
import static org.vertx.testtools.VertxAssert.testComplete;

import com.inventage.issumy.issues.IssuesVerticle;
import org.junit.Test;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.testtools.TestVerticle;

/**
 * integration test.
 */
public class IssuesViaEventBusTest extends TestVerticle {

    @Test
    public void shouldReturnEmptyListOfIssues() throws Exception {
        // when
        vertx.eventBus().send("com.inventage.issumy.issues", "", (Message<JsonArray> msg) -> {
            // then
            assertThat(msg.body().size(), is(0));
            testComplete();
        });
    }

    @Override
    public void start() {
        initialize();

        container.deployVerticle(IssuesVerticle.class.getName(), event -> {
            if (event.failed()) {
                throw new IllegalStateException("deployment of module failed", event.cause());
            }
            startTests();
        });
    }
}
