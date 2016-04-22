package com.example.jyuen.baseproject.dagger.module;

import com.example.jyuen.baseproject.dagger.scrope.UserScope;
import com.example.jyuen.baseproject.network.interfaces.FlickrApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;

/**
 * Created by jyuen on 1/17/16.
 */
@Module
public class FlickrModule {

    @Provides
    @UserScope
    public FlickrApiInterface providesFlickrInterface(Retrofit retrofit) {
        return retrofit.create(FlickrApiInterface.class);
    }
}
