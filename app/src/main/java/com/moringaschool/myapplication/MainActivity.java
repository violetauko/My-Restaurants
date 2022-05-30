package com.moringaschool.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindRestaurantsButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                if (v == mFindRestaurantsButton) {
                    String location = mLocationEditText.getText().toString();
                    if (location.isEmpty()) {
                        Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(this, RestaurantsActivity.class);
                        intent.putExtra("location", location);
                        startActivity(intent);
                    }
                }
            }
}
