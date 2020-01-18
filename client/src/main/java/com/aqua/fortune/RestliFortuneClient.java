package com.aqua.fortune;

import com.aqua.fortune.Fortune;
import com.aqua.fortune.FortunesGetRequestBuilder;
import com.aqua.fortune.FortunesRequestBuilders;
import com.linkedin.common.callback.FutureCallback;
import com.linkedin.common.util.None;
import com.linkedin.r2.transport.common.Client;
import com.linkedin.r2.transport.common.bridge.client.TransportClientAdapter;
import com.linkedin.r2.transport.http.client.HttpClientFactory;
import com.linkedin.restli.client.Request;
import com.linkedin.restli.client.Response;
import com.linkedin.restli.client.ResponseFuture;
import com.linkedin.restli.client.RestClient;
import java.util.Collections;


public class RestliFortuneClient {
  public static void main(String[] args) throws Exception {

    // Create an HttpClient and wrap it in an abstraction layer
    final HttpClientFactory http = new HttpClientFactory();
    final Client r2Client = new TransportClientAdapter(
        http.getClient(Collections.<String, String>emptyMap()));

    // Create a RestClient to talk to localhost:8080
    final RestClient restClient = new RestClient(r2Client, "http://localhost:8080/");

    // Construct a request for the specified fortune
    final FortunesRequestBuilders fortuneBuilders = new FortunesRequestBuilders();

    final FortunesGetRequestBuilder getBuilder = fortuneBuilders.get();
    final Request<Fortune> getRequest = getBuilder.id("Chen").build();

    // Send the request and wait for a response
    final ResponseFuture<Fortune> getFuture = restClient.sendRequest(getRequest);
    final Response<Fortune> response = getFuture.getResponse();

    // Print the response
    System.out.println(response.getEntity().getMessage());

    // Shutdown
    restClient.shutdown(new FutureCallback<None>());
    http.shutdown(new FutureCallback<None>());
  }
}