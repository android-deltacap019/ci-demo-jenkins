package com.deltacap019.cidemojenkins.service;

import com.deltacap019.cidemojenkins.util.network.NetworkResponse;
import com.deltacap019.cidemojenkins.util.network.NetworkTaskUtility;
import com.deltacap019.cidemojenkins.util.network.NetworkUtil;

public class AlbumsService {

    public void fetchAlbums(ServiceCallback<NetworkResponse> networkResponseServiceCallback) {
        new NetworkTaskUtility(APIConstants.getApiAlbums(), NetworkUtil.RequestType.GET, networkResponseServiceCallback).execute();
    }
}
