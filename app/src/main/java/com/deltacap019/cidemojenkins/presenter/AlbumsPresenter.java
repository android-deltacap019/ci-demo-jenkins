package com.deltacap019.cidemojenkins.presenter;

import android.util.Log;

import com.deltacap019.cidemojenkins.model.controller.AlbumsController;
import com.deltacap019.cidemojenkins.model.pojo.albums.AlbumsWrapper;
import com.deltacap019.cidemojenkins.service.ServiceCallback;

import java.util.logging.Logger;

public class AlbumsPresenter {

    private static final String TAG = AlbumsPresenter.class.getSimpleName();

    public void bindAlbumData() {
        AlbumsController albumsController = new AlbumsController();

        albumsController.getAlbums(new ServiceCallback<AlbumsWrapper>() {
            @Override
            public void onSuccess(AlbumsWrapper response) {
                Log.d(TAG, response.getAlbumList().toString());
            }

            @Override
            public void onFailure(AlbumsWrapper error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }
}
