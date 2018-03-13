package com.example.nhattruong.financialmanager.interactor.api.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeadersInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(processRequest(chain.request()));
    }

    private Request processRequest(Request request) {
        Request.Builder newBuilder = request.newBuilder();
        HttpUrl newUrl = HttpUrl.parse("http://");
        if (null != newUrl) {
            newUrl = request.url().newBuilder()
                    .scheme(newUrl.scheme())
                    .host(newUrl.host())
                    .port(newUrl.port())
                    .build();
            return newBuilder
                    .url(newUrl)
                    .build();
        }
        return newBuilder.build();
    }

}
