package com.example.jyuen.baseproject.dagger.component;

import com.example.jyuen.baseproject.dagger.module.FlickrModule;
import com.example.jyuen.baseproject.dagger.scrope.UserScope;
import com.example.jyuen.baseproject.search.MainActivity;

import dagger.Component;

/**
 * Created by jyuen on 1/17/16.
 */
@UserScope
@Component(dependencies = NetComponent.class, modules = FlickrModule.class)
public interface FlickrComponent {
    void inject (MainActivity activity);
}
