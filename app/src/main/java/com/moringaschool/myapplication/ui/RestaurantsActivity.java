package com.moringaschool.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.myapplication.MyRestaurantsArrayAdapter;
import com.moringaschool.myapplication.R;
import com.moringaschool.myapplication.adapters.RestaurantListAdapter;
import com.moringaschool.myapplication.models.Business;
import com.moringaschool.myapplication.models.YelpApiBusinessSearchResponse;
import com.moringaschool.myapplication.network.YelpApi;
import com.moringaschool.myapplication.network.YelpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RestaurantsActivity extends AppCompatActivity {
    private static final String TAG = RestaurantsActivity.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    private List<Business> restaurants;
    private RestaurantListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        //mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          //  @Override
            //public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               // Log.v("RestaurantsActivity", "In the onItemClickListener!");
               // String restaurant = ((TextView)view).getText().toString();
                //Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_LONG).show();
                //Log.d("RestaurantsActivity", "In the onCreate method");
            //}
        ;
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
       // mLocationTextView.setText("Here are all the restaurants near: " + location);

        YelpApi client = YelpClient.getClient();

        Call<YelpApiBusinessSearchResponse> call = client.getRestaurants(location, "restaurants");
        call.enqueue(new Callback<YelpApiBusinessSearchResponse>() {
            @Override
            public void onResponse(Call<YelpApiBusinessSearchResponse> call, Response<YelpApiBusinessSearchResponse> response){
                hideProgressBar();

                if (response.isSuccessful()){

                restaurants = response.body().getBusinesses();
                mAdapter = new RestaurantListAdapter(RestaurantsActivity.this, restaurants);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager =
                        new LinearLayoutManager(RestaurantsActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);


                showRestaurants();
                }else{
                    showUnsuccessMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpApiBusinessSearchResponse> call, Throwable t) {

                Log.e("Error Message", "onFailure: " + t);
                hideProgressBar();
                showFailureMessage();

            }
        });
    }

    private void showFailureMessage(){
        mErrorTextView.setText("Something went wrong.Please check your internet connection and try again.");
        mErrorTextView.setVisibility(View.VISIBLE);
    }
    private void showUnsuccessMessage(){
        mErrorTextView.setText("Something went wrong.Please try again later.");
        mErrorTextView.setVisibility(View.VISIBLE);
    }
    private void showRestaurants(){
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
}