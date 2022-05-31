package com.moringaschool.myapplication.network;

import static com.moringaschool.myapplication.Constants.YELP_API_KEY;
import static com.moringaschool.myapplication.Constants.YELP_BASE_URL;

import com.moringaschool.myapplication.network.YelpApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YelpClient {
    private static Retrofit retrofit = null;

    public static YelpApi getClient() {
        if (retrofit == null) {
            OkHttpClient OkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                           Request newRequest = chain.request().newBuilder()
                                    .addHeader("Authorization", YELP_API_KEY)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(YELP_BASE_URL)
                    .client(OkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(YelpApi.class);

        }

}
