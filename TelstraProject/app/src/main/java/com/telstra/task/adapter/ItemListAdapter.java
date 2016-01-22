package com.telstra.task.adapter;


import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.telstra.task.ApplicationController;
import com.telstra.task.R;
import com.telstra.task.model.ListItem;

public class ItemListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    AppCompatActivity activity;
    ImageLoader imageLoader = ApplicationController.getInstance().getImageLoader();

    public ItemListAdapter(AppCompatActivity activity){
        this.activity=activity;

    }
    @Override
    public int getCount() {
        return ListItem.listItemsArray.size();
    }

    @Override
    public Object getItem(int i) {
        return ListItem.listItemsArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
String url="http://3.bp.blogspot.com/__mokxbTmuJM/RnWuJ6cE9cI/AAAAAAAAATw/6z3m3w9JDiU/s400/019843_31.jpg";
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_list_row, null);

        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.contentImage);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView description = (TextView) convertView.findViewById(R.id.description);

        title.setText(ListItem.listItemsArray.get(i).getTitle());
        description.setText(ListItem.listItemsArray.get(i).getDescription());
        thumbNail.setImageUrl(ListItem.listItemsArray.get(i).getImgUrl(), imageLoader);
        //thumbNail.setImageUrl(url, imageLoader);
        return convertView;
    }
}