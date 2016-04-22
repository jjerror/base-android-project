package com.example.jyuen.baseproject.search;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jyuen.baseproject.BaseApplication;
import com.example.jyuen.baseproject.R;
import com.example.jyuen.baseproject.base.activity.DrawerActivity;
import com.example.jyuen.baseproject.model.flickr.Photo;
import com.example.jyuen.baseproject.network.interfaces.FlickrApiInterface;
import com.example.jyuen.baseproject.widget.recyclerview.BaseRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends DrawerActivity {

    private static final String TAG = "MAIN";

    @Inject
    FlickrApiInterface mFlickrApiInterface;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.search_view)
    SearchView mSearchView;

    @OnClick(R.id.fab)
    void onFabClick (View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private SearchResultAdapter mAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((BaseApplication) getApplication()).getFlickrComponent().inject(this);


        //mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setQueryHint("Search User");
        mSearchView.setOnQueryTextListener(
            new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit (String query) {
                    if (!TextUtils.isEmpty(query)) {
                        mAdapter.setTerm(query);

                    }

                    return false;
                }

                @Override
                public boolean onQueryTextChange (String newText) {
                    return false;
                }
            });

        mAdapter = new SearchResultAdapter(mFlickrApiInterface);
        if (savedInstanceState != null) {
            mAdapter.restoreFromBundle(savedInstanceState);
        }

        mAdapter.setOnItemClickListener(
            new BaseRecyclerViewAdapter.OnItemClickListener<Photo>() {
                @Override
                public void onItemClick (View v, Photo user, int position) {
                    //Snackbar.make(v, String.format("User %s selected", user.getUrl()), Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show();
                }
            });


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int column = (int) (metrics.widthPixels / 400f);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, column));
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.addItemDecoration(RecyclerViewMarginDecoration.fromDimenIdForBottom(this, R.dimen.list_spacing));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.saveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
