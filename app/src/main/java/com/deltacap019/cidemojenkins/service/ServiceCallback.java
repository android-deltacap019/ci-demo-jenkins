package com.deltacap019.cidemojenkins.service;

public interface ServiceCallback<T> {

    void onSuccess(T response);
    void onFailure(T error);
}
