package com.example.jyuen.baseproject.dagger.component;

import com.example.jyuen.baseproject.base.activity.BaseActivity;
import com.example.jyuen.baseproject.dagger.module.AppModule;
import com.example.jyuen.baseproject.dagger.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.Retrofit;

/**
 * Created by jyuen on 1/16/16.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject (BaseActivity activity);

    Retrofit retrofit();
}
