package com.example.jyuen.baseproject;

import android.app.Application;

import com.example.jyuen.baseproject.dagger.component.FlickrComponent;
import com.example.jyuen.baseproject.dagger.component.NetComponent;
import com.example.jyuen.baseproject.dagger.module.AppModule;
import com.example.jyuen.baseproject.dagger.module.FlickrModule;
import com.example.jyuen.baseproject.dagger.module.NetModule;

/**
 * Created by jyuen on 1/16/16.
 */
public class BaseApplication extends Application {

    private NetComponent mNetComponent;
    private FlickrComponent mFlickrComponent;

    @Override
    public void onCreate () {
        super.onCreate();

        // specify the full namespace of the component
        mNetComponent = com.example.jyuen.baseproject.dagger.component.DaggerNetComponent.builder()
            // list of modules that are part of this component need to be created here too
            .appModule(new AppModule(this))
            .netModule(new NetModule("https://api.flickr.com/services/rest/"))
            .build();

        mFlickrComponent = com.example.jyuen.baseproject.dagger.component.DaggerFlickrComponent.builder()
            // list of modules that are part of this component need to be created here too
            .netComponent(mNetComponent)
            .flickrModule(new FlickrModule())
            .build();
    }

    public NetComponent getNetComponent () {
        return mNetComponent;
    }

    public FlickrComponent getFlickrComponent () {
        return mFlickrComponent;
    }
}
