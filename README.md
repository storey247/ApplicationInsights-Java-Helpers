# ApplicationInsights-Java-Helpers

- Got a code block or method that you want to easily track as a dependency in ApplicationInsights?
- Does ApplicationInsights not track your Http Requests as dependencies correctly?
- Are you using Azure Functions and struggling to track your exceptions correctly?

Fear not, this helper library will give you a nice handy wrapper to work with :grin:

# Dependencies

## Maven

```xml
<dependency>
    <groupId>com.evanlennick</groupId>
    <artifactId>retry4j</artifactId>
    <version>0.15.0</version>
</dependency>
```

# Getting started

## Tracking code blocks as dependencies
If you have a block of code that you want to track as a dependency, lets say you are calling another sdk for instance:

```java
TelemetryClient telemetry = new TelemetryClient();
TelemetryClientHelper helper = new TelemetryClientHelper(telemetry);
helper.monitorDependency("myDependency", "doingSomeWork", () -> {
    // some Callable that may throw or return
});
```

By wrapping your code in this block you will start to see telemetry metrics being logged automatically into ApplicationInsights. You will recieve such metrics as:
- If the dependency block executed successfully
- How long the execution of the block took
- Any exceptions that occurred will automatically be logging into AppInsights too
  
Telemetry events will be logged using the string values passed to the `monitorDependency` method

## Tracking http calls as dependencies
If you have an external service that you want to call (an api for instance) and want that to be automatically tracked in your telemetry, then you can use the `` method:

```java
TelemetryClient telemetry = new TelemetryClient();
TelemetryClientHelper helper = new TelemetryClientHelper(telemetry);

HttpUriRequest request = new HttpGet("http://wibble.com/api");

try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
    helper.monitorHttpDependency("myDependency", "callingSomeHttpEndpoint", httpClient, request);
}
```

By calling your upstream services using this code you will recieve such metrics as:
- If the dependency block executed successfully (status code 2xx)
- Result code
- The Http verb that was used for the dependency (`HTTP - GET` in the example above)
- The number of ms that the http call took
- Any exceptions that occurred will automatically be logging into AppInsights too