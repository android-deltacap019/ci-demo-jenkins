package com.deltacap019.cidemojenkins.utility;

import com.deltacap019.cidemojenkins.service.ServiceCallback;
import com.deltacap019.cidemojenkins.util.network.NetworkResponse;
import com.deltacap019.cidemojenkins.util.network.NetworkTaskUtility;
import com.deltacap019.cidemojenkins.util.network.NetworkUtil;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.TestCase.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
@Ignore
public class NetworkTaskUtilityTest {

    private NetworkResponse networkResponse;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void correctAPI_HTTP_ReturnsTrue() {
        String url = "http://www.google.com";
        new NetworkTaskUtility(url, NetworkUtil.RequestType.GET, new ServiceCallback<NetworkResponse>() {
            @Override
            public void onSuccess(NetworkResponse response) {
                networkResponse = response;
            }

            @Override
            public void onFailure(NetworkResponse error) {
                networkResponse = error;
            }
        }).execute();
        Robolectric.flushBackgroundThreadScheduler();

        assertEquals(200,networkResponse.getResponseCode());
    }

    @Test
    public void correctAPI_HTTPS_ReturnsTrue() {
        String url = "https://www.google.com";
        new NetworkTaskUtility(url, NetworkUtil.RequestType.GET, new ServiceCallback<NetworkResponse>() {
            @Override
            public void onSuccess(NetworkResponse response) {
                networkResponse = response;
            }

            @Override
            public void onFailure(NetworkResponse error) {
                networkResponse = error;
            }
        }).execute();
        Robolectric.flushBackgroundThreadScheduler();

        assertEquals(200,networkResponse.getResponseCode());
    }

    @Test
    public void emptyURL_ReturnsFalse() {
        String url = "";
        new NetworkTaskUtility(url, NetworkUtil.RequestType.GET, new ServiceCallback<NetworkResponse>() {
            @Override
            public void onSuccess(NetworkResponse response) {
                networkResponse = response;
            }

            @Override
            public void onFailure(NetworkResponse error) {
                networkResponse = error;
            }
        }).execute();
        Robolectric.flushBackgroundThreadScheduler();

        assertEquals(0,networkResponse.getResponseCode());
    }

    @Test
    public void nullURL_ReturnsFalse() {
        String url = null;
        new NetworkTaskUtility(url, NetworkUtil.RequestType.GET, new ServiceCallback<NetworkResponse>() {
            @Override
            public void onSuccess(NetworkResponse response) {
                networkResponse = response;
            }

            @Override
            public void onFailure(NetworkResponse error) {
                networkResponse = error;
            }
        }).execute();
        Robolectric.flushBackgroundThreadScheduler();

        assertEquals(0,networkResponse.getResponseCode());
    }

    @Test
    public void incorrectURL_ReturnsFalse() {
        String url = "abcXYZ";
        new NetworkTaskUtility(url, NetworkUtil.RequestType.GET, new ServiceCallback<NetworkResponse>() {
            @Override
            public void onSuccess(NetworkResponse response) {
                networkResponse = response;
            }

            @Override
            public void onFailure(NetworkResponse error) {
                networkResponse = error;
            }
        }).execute();
        Robolectric.flushBackgroundThreadScheduler();

        assertEquals(0,networkResponse.getResponseCode());
    }
}
