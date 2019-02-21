package com.deltacap019.cidemojenkins.util.network;

import android.os.AsyncTask;

import com.deltacap019.cidemojenkins.service.ServiceCallback;

public class NetworkTaskUtility extends AsyncTask<Void, Void, NetworkResponse> {

    private String url;
    private ServiceCallback<NetworkResponse> serviceCallback;
    private NetworkUtil.RequestType requestType;

    public NetworkTaskUtility(String url, NetworkUtil.RequestType requestType, ServiceCallback<NetworkResponse> serviceCallback) {
        this.url = url;
        this.serviceCallback = serviceCallback;
        this.requestType = requestType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected NetworkResponse doInBackground(Void... voids) {
       return new NetworkUtil(this.url, this.requestType).getResponse();
    }


    @Override
    protected void onCancelled() {
        super.onCancelled();
        NetworkResponse networkResponse = new NetworkResponse();
        networkResponse.setResponseCodeMessage("Task Cancelled");
        serviceCallback.onFailure(networkResponse);
    }

    @Override
    protected void onPostExecute(NetworkResponse networkResponse) {
        if (networkResponse.getResponseCode() == 200)
        {
            serviceCallback.onSuccess(networkResponse);
        }else {
            serviceCallback.onFailure(networkResponse);
        }
    }
}
