package io.dropwizard.metrics5.httpclient;

import io.dropwizard.metrics5.MetricName;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.utils.URIUtils;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HttpClientMetricNameStrategiesTest {

    @Test
    public void methodOnlyWithName() {
        assertThat(HttpClientMetricNameStrategies.METHOD_ONLY.getNameFor("some-service", new HttpGet("/whatever")),
                is(MetricName.build("org.apache.http.client.HttpClient.some-service.get-requests")));
    }

    @Test
    public void methodOnlyWithoutName() {
        assertThat(HttpClientMetricNameStrategies.METHOD_ONLY.getNameFor(null, new HttpGet("/whatever")),
                is(MetricName.build("org.apache.http.client.HttpClient.get-requests")));
    }

    @Test
    public void hostAndMethodWithName() {
        assertThat(HttpClientMetricNameStrategies.HOST_AND_METHOD.getNameFor("some-service", new HttpPost("http://my.host.com/whatever")),
                is(MetricName.build("org.apache.http.client.HttpClient.some-service.my.host.com.post-requests")));
    }

    @Test
    public void hostAndMethodWithoutName() {
        assertThat(HttpClientMetricNameStrategies.HOST_AND_METHOD.getNameFor(null, new HttpPost("http://my.host.com/whatever")),
                is(MetricName.build("org.apache.http.client.HttpClient.my.host.com.post-requests")));
    }

    @Test
    public void hostAndMethodWithNameInWrappedRequest() throws URISyntaxException {
        HttpRequest request = rewriteRequestURI(new HttpPost("http://my.host.com/whatever"));

        assertThat(HttpClientMetricNameStrategies.HOST_AND_METHOD.getNameFor("some-service", request),
                is(MetricName.build("org.apache.http.client.HttpClient.some-service.my.host.com.post-requests")));
    }

    @Test
    public void hostAndMethodWithoutNameInWrappedRequest() throws URISyntaxException {
        HttpRequest request = rewriteRequestURI(new HttpPost("http://my.host.com/whatever"));

        assertThat(HttpClientMetricNameStrategies.HOST_AND_METHOD.getNameFor(null, request),
                is(MetricName.build("org.apache.http.client.HttpClient.my.host.com.post-requests")));
    }

    @Test
    public void querylessUrlAndMethodWithName() {
        assertThat(HttpClientMetricNameStrategies.QUERYLESS_URL_AND_METHOD.getNameFor(
                "some-service",
                new HttpPut("https://thing.com:8090/my/path?ignore=this&and=this")),
                is(MetricName.build("org.apache.http.client.HttpClient.some-service.https://thing.com:8090/my/path.put-requests")));
    }

    @Test
    public void querylessUrlAndMethodWithNameInWrappedRequest() throws URISyntaxException {
        HttpRequest request = rewriteRequestURI(new HttpPut("https://thing.com:8090/my/path?ignore=this&and=this"));
        assertThat(HttpClientMetricNameStrategies.QUERYLESS_URL_AND_METHOD.getNameFor(
                "some-service",
                request),
                is(MetricName.build("org.apache.http.client.HttpClient.some-service.https://thing.com:8090/my/path.put-requests")));
    }

    private static HttpRequest rewriteRequestURI(HttpRequest request) throws URISyntaxException {
        HttpRequestWrapper wrapper = HttpRequestWrapper.wrap(request);
        URI uri = URIUtils.rewriteURI(wrapper.getURI(), null, true);
        wrapper.setURI(uri);

        return wrapper;
    }
}
