package com.moringaschool.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.myapplication.R;
import com.moringaschool.myapplication.models.Business;
import com.moringaschool.myapplication.ui.RestaurantDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {
    private Context mContext;
    private List<Business> mRestaurants;

    public RestaurantListAdapter(Context context, List<Business> restaurants) {
        this.mContext = context;
        this.mRestaurants = restaurants;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent .getContext()).inflate(R.layout.restaurant_list_item, parent, false);//inflate the layout
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);//create a new viewholder
        return viewHolder;//return the viewholder to the onBindViewHolder method .
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bindRestaurant(mRestaurants.get(position));//

    }


    @Override
    public int getItemCount() {
        return mRestaurants.size();//return the size of the list of restaurants
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.restaurantImageView)
        ImageView mRestaurantImageView;
        @BindView(R.id.restaurantNameTextView)
        TextView mNameTextView;
        @BindView(R.id.categoryTextView)
        TextView mCategoryTextView;
        @BindView(R.id.ratingTextView)
        TextView mRatingTextView;
        private Context mContext;

        public RestaurantViewHolder(View itemView) {
            super(itemView);//call the superclass constructor with the view passed in as a parameter .
            ButterKnife.bind(this, itemView);//bind the viewholder to the view
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);//set the onclicklistener to the itemview
        }

        public void bindRestaurant(Business restaurant) {
            mNameTextView.setText(restaurant.getName());//set the name of the restaurant to the textview in the layout file (restaurant_list_item.xml).
            mCategoryTextView.setText(restaurant.getCategories().get(0).getTitle());//set the category of the restaurant to the textview in the layout file (restaurant_list_item.xml).
            mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");//set the rating .
            Picasso.get().load(restaurant.getImageUrl()).into(mRestaurantImageView);

        }

        @Override
        public void onClick(View v) {
           int itemPosition = getLayoutPosition();//get the position of the item that was clicked.
           Intent intent = new Intent(mContext, RestaurantDetailActivity.class);//create a new intent to open the restaurant detail activity.
              intent.putExtra("position", itemPosition);//put the position of the restaurant in the intent.
                intent.putExtra("restaurants", Parcels.wrap(mRestaurants));//put the restaurant in the intent.
                mContext.startActivity(intent);//start the activity.
        }
    }
}
