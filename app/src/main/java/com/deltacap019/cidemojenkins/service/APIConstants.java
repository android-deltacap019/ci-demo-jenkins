package com.deltacap019.cidemojenkins.service;

public class APIConstants {

    private static final String API_ALBUMS = "https://jsonplaceholder.typicode.com/albums";
    private static final String API_PHOTOS = "https://jsonplaceholder.typicode.com/photos";

    public static String getApiAlbums() {
        return API_ALBUMS;
    }

    public static String getApiPhotos() {
        return API_PHOTOS;
    }
}
