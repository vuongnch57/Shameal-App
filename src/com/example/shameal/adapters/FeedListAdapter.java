package com.example.shameal.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.shameal.FeedImageView;
import com.example.shameal.R;
import com.example.shameal.controller.AppController;
import com.example.shameal.data.FeedItem;
 
public class FeedListAdapter extends BaseAdapter {  
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
 
    public FeedListAdapter(Activity activity, List<FeedItem> feedItems) {
        this.activity = activity;
        this.feedItems = feedItems;
    }
 
    @Override
    public int getCount() {
        return feedItems.size();
    }
 
    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.feed_item, null);
 
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
 
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView address = (TextView) convertView
                .findViewById(R.id.address);
        TextView description = (TextView) convertView
                .findViewById(R.id.description);
        TextView rate = (TextView) convertView.findViewById(R.id.rate);
        NetworkImageView image = (NetworkImageView) convertView
                .findViewById(R.id.image);
        FeedImageView feedImageView = (FeedImageView) convertView
                .findViewById(R.id.feedImage1);
 
        FeedItem item = feedItems.get(position);
 
        name.setText(item.getName());
 
        // Converting timestamp into x ago format
        address.setText(item.getAddress());
 
        // Chcek for empty status message
        if (!TextUtils.isEmpty(item.getDescription())) {
            description.setText(item.getDescription());
            description.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            description.setVisibility(View.GONE);
        }
 
        String r = "";
        r = r + item.getRate();
        rate.setText(r);
        image.setImageUrl(item.getImage(), imageLoader);
 
        // Feed image
        if (item.getMenu() != null || item.getMenu() != "0") {
            feedImageView.setImageUrl(item.getMenu(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }
 
                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            feedImageView.setVisibility(View.GONE);
        }
 
        return convertView;
    }
 
}