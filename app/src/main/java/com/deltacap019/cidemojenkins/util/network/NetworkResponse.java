package com.deltacap019.cidemojenkins.util.network;

public class NetworkResponse {

    private String response;
    private int responseCode;
    private String responseCodeMessage;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseCodeMessage() {
        return responseCodeMessage;
    }

    public void setResponseCodeMessage(String responseCodeMessage) {
        this.responseCodeMessage = responseCodeMessage;
    }
}
