package com.example.jyuen.baseproject.model.flickr;

import java.io.Serializable;

/**
 * Created by jyuen on 1/22/16.
 */
public class Photo implements Serializable {
    int farm;
    String server;
    String id;
    String secret;

    public String getUrl () {
        return String.format("http://farm%s.static.flickr.com/%s/%s_%s.jpg", farm, server, id, secret);
    }
}
