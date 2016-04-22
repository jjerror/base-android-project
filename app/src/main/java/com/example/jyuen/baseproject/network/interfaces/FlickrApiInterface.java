package com.example.jyuen.baseproject.network.interfaces;

import com.example.jyuen.baseproject.model.flickr.SearchResult;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by jyuen on 1/17/16.
 */
public interface FlickrApiInterface {

    @GET("?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1")
    Observable<SearchResult> searchUser (@Query("text") String term);

    @GET("?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1")
    Observable<SearchResult> searchUser (@Query("text") String term, @Query("page") int page);
}
