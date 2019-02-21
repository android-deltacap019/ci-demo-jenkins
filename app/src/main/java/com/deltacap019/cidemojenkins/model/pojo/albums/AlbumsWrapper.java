package com.deltacap019.cidemojenkins.model.pojo.albums;

import java.util.ArrayList;
import java.util.List;

public class AlbumsWrapper {

    private String message = "success";
    private List<Album> albumList = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }
}
