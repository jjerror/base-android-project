package com.example.jyuen.baseproject.search;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jyuen.baseproject.R;
import com.example.jyuen.baseproject.model.flickr.Photo;
import com.example.jyuen.baseproject.model.flickr.SearchResult;
import com.example.jyuen.baseproject.network.interfaces.FlickrApiInterface;
import com.example.jyuen.baseproject.widget.recyclerview.BaseRecyclerViewAdapter;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jyuen on 1/18/16.
 */
public class SearchResultAdapter extends BaseRecyclerViewAdapter<Photo, BaseRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Photo> mData;
    private String mTerm;
    private SearchResult mLastResult;

    private final FlickrApiInterface mInterface;
    private Observable<SearchResult> mObservable;

    public SearchResultAdapter (FlickrApiInterface apiInterface) {
        mInterface = apiInterface;
    }

    public void setTerm (String term) {
        if (mTerm == null && term == null) {
            return;
        }
        if (mTerm != null && mTerm.equals(term)) {
            return;
        }

        mTerm = term;
        mLastResult = null;
        mData = null;
        notifyDataSetChanged();

        search();
    }

    private void search () {
        if (TextUtils.isEmpty(mTerm)) {
            return;
        }

        if (mObservable != null) {
            return;
        }

        if (mLastResult == null) {
            mObservable = mInterface.searchUser(mTerm);
        } else if (!mLastResult.hasMorePage()) {
            return;
        } else {
            mObservable = mInterface.searchUser(mTerm, mLastResult.getNextPage());
        }

        mObservable.subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .unsubscribeOn(Schedulers.io())
                   .flatMap(
                       new Func1<SearchResult, Observable<List<Photo>>>() {
                           @Override
                           public Observable<List<Photo>> call (SearchResult userSearchResult) {
                               mLastResult = userSearchResult;
                               return Observable.just(userSearchResult.getItems());
                           }
                       })
                   .subscribe(
                       new Subscriber<List<Photo>>() {
                           @Override
                           public void onCompleted () {
                           }

                           @Override
                           public void onError (Throwable e) {

                           }

                           @Override
                           public void onNext (List<Photo> photo) {
                               mObservable = null;
                               setData(photo);
                           }
                       });

    }

    @Override
    public BaseRecyclerViewAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new PhotoViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder (BaseRecyclerViewAdapter.ViewHolder holder, int position) {
        if (holder instanceof PhotoViewHolder) {
            ((PhotoViewHolder) holder).bind(getItem(position));
        }
    }

    @Override
    public int getItemCount () {
        if (mData == null) {
            return 0;
        }

        return mData.size();
    }

    public void setData (List<Photo> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public Photo getItem (int position) {
        if (mData != null) {
            if (position > mData.size() - 10) {
                search();
            }
            return mData.get(position);
        }

        return null;
    }

    public void saveInstanceState (Bundle bundle) {
        bundle.putSerializable("LAST_RESULT", mLastResult);
        bundle.putSerializable("DATA", mData);
        bundle.putString("TERM", mTerm);
    }

    public void restoreFromBundle (Bundle savedInstanceState) {
        mLastResult = (SearchResult) savedInstanceState.getSerializable("LAST_RESULT");
        mData = (ArrayList<Photo>) savedInstanceState.getSerializable("DATA");
        mTerm = savedInstanceState.getString("TERM");

    }
}
