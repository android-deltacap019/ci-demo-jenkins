package com.deltacap019.cidemojenkins.model.controller;

import com.deltacap019.cidemojenkins.model.pojo.albums.Album;
import com.deltacap019.cidemojenkins.model.pojo.albums.AlbumsWrapper;
import com.deltacap019.cidemojenkins.service.AlbumsService;
import com.deltacap019.cidemojenkins.service.ServiceCallback;
import com.deltacap019.cidemojenkins.util.network.NetworkResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class AlbumsController {

    private AlbumsWrapper albumsWrapper = new AlbumsWrapper();

    public void getAlbums(final ServiceCallback<AlbumsWrapper> albumsServiceCallback) {

        new AlbumsService().fetchAlbums(new ServiceCallback<NetworkResponse>() {
            @Override
            public void onSuccess(NetworkResponse response) {
                if(response.getResponseCode() == 200) {
                    Gson gson = new Gson();
                    Type collectionType = new TypeToken<Collection<Album>>(){}.getType();
                    List<Album> albumList = gson.fromJson(response.getResponse(), collectionType);
                    albumsWrapper.setAlbumList(albumList);
                    if (albumsWrapper.getAlbumList() != null) {
                        albumsWrapper.setMessage("Success");
                        albumsServiceCallback.onSuccess(albumsWrapper);

                    } else {
                        albumsWrapper.setMessage("No Albums present");
                        albumsServiceCallback.onFailure(albumsWrapper);
                    }
                }
                else {
                    albumsWrapper.setMessage(response.getResponseCodeMessage());
                    albumsServiceCallback.onFailure(albumsWrapper);
                }
            }

            @Override
            public void onFailure(NetworkResponse error) {
                albumsWrapper.setMessage(error.getResponseCodeMessage());
                albumsServiceCallback.onFailure(albumsWrapper);
            }
        });
    }
}
