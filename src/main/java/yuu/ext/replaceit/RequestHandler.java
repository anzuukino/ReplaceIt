package yuu.ext.replaceit;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.handler.*;
import burp.api.montoya.logging.Logging;

public class RequestHandler implements HttpHandler {
    private final Logging logger;
    public RequestHandler(MontoyaApi api) {
        logger = api.logging();
    }

    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent httpRequestToBeSent) {
        logger.logToOutput("HTTP request to " + httpRequestToBeSent.httpService() + " [" + httpRequestToBeSent.toolSource().toolType().toolName() + "]");

        return RequestToBeSentAction.continueWith(httpRequestToBeSent);
    }
    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived) {
        logger.logToOutput("HTTP response from " + httpResponseReceived.initiatingRequest().httpService() + " [" + httpResponseReceived.toolSource().toolType().toolName() + "]");

        return ResponseReceivedAction.continueWith(httpResponseReceived);
    }
}
