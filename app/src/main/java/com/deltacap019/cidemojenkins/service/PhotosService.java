package com.deltacap019.cidemojenkins.service;

import com.deltacap019.cidemojenkins.util.network.NetworkResponse;
import com.deltacap019.cidemojenkins.util.network.NetworkTaskUtility;
import com.deltacap019.cidemojenkins.util.network.NetworkUtil;

public class PhotosService {

    public void fetchPhotos(ServiceCallback<NetworkResponse> networkResponseServiceCallback) {
        new NetworkTaskUtility(APIConstants.getApiPhotos(), NetworkUtil.RequestType.GET, networkResponseServiceCallback);
    }
}
