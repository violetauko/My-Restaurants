package com.moringaschool.myapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.myapplication.models.Business;
import com.moringaschool.myapplication.ui.RestaurantDetailFragment;

import java.util.List;

public class RestaurantPagerAdapter extends FragmentPagerAdapter {
    private List<Business> mRestaurants;

            public RestaurantPagerAdapter(@NonNull FragmentManager fm, int behaviour , List<Business> restaurants) {
                super(fm, behaviour);
                mRestaurants = restaurants;
            }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return RestaurantDetailFragment.newInstance(mRestaurants.get(position));
    }

    @Override
    public int getCount() {
        return mRestaurants.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRestaurants.get(position).getName();
    }

}
