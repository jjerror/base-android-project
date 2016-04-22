package com.example.jyuen.baseproject.dagger.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jyuen on 1/16/16.
 */
@Module
public class AppModule {

    Application mApplication;

    public AppModule (Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication () {
        return mApplication;
    }
}
