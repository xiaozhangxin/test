package com.akan.qf.view.img;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by taoh on 2017/11/16.
 */

public class ImageViewPagerAdapter extends FragmentPagerAdapter {


    private static final String IMAGE_URL = "image";

    List<String> mDatas;
    int mposition;
    String type;

    public ImageViewPagerAdapter(FragmentManager fm, List data, int position,String type) {
        super(fm);
        this.mDatas = data;
        this.mposition = position;
        this.type=type;

    }

    @Override
    public Fragment getItem(int position) {
        String url = mDatas.get(position);
        Fragment fragment = ImageFragment.newInstance(url,type);
        return fragment;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
}
