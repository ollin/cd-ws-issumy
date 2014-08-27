package httphelloworld;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import com.inventage.issumy.issues.IssuesVerticle;
import org.junit.Test;

/**
 * unit test.
 */
public class HelloWorldServerTest {
    @Test
    public void defaultConstructor() throws Exception {
        assertThat(new HelloWorldServer(), notNullValue());
    }
}