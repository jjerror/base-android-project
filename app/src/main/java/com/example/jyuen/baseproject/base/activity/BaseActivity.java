package com.example.jyuen.baseproject.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.jyuen.baseproject.BaseApplication;
import com.example.jyuen.baseproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jyuen on 1/17/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication) getApplication()).getNetComponent().inject(this);
    }

    @Override
    public void setContentView (int layoutResID) {
        super.setContentView(layoutResID);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
    }


}
