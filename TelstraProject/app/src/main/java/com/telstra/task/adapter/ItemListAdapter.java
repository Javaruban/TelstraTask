package com.telstra.task.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.telstra.task.R;
import com.telstra.task.common.ApplicationController;
import com.telstra.task.model.ListItem;
import java.util.List;

/**
 * Created by Ruban on 1/22/2016.
 * This class is a custom adapter for ListView shown in ItemListActivity
 */

public class ItemListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    AppCompatActivity activity;
    List<ListItem> itemListArray;
    ImageLoader imageLoader = ApplicationController.getInstance().getImageLoader();//initializing Image loader

    /**
     * Constructor to Initialize data
     */
    public ItemListAdapter(AppCompatActivity activity, List<ListItem> itemListArray) {
        this.activity = activity;
        this.itemListArray = itemListArray;
    }

    @Override
    public int getCount() {
        //items are in the data set represented by this Adapter.
        return itemListArray.size();
    }

    @Override
    public Object getItem(int i) {
        //Get the data item associated with the specified position in the data set.
        return itemListArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        //Get the row id associated with the specified position in the list.
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set
     */
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_list_row, null);

           // Get the ImageLoader through your singleton class.
        if (imageLoader == null)//if image loader null initialize again
            imageLoader = ApplicationController.getInstance().getImageLoader();

        ViewHolder holder = new ViewHolder();
        holder.thumbNail = (NetworkImageView) convertView.findViewById(R.id.contentImage);
        holder.title = (TextView) convertView.findViewById(R.id.title);
        holder.description = (TextView) convertView.findViewById(R.id.description);

        holder.title.setText(itemListArray.get(i).getTitle());
        holder.description.setText(itemListArray.get(i).getDescription());
        holder.thumbNail.setImageUrl(itemListArray.get(i).getImgUrl(), imageLoader);
        return convertView;
    }

    /**
     * A ViewHolder object stores each of the component views inside the tag field of the Layout,
     * so you can immediately access them without the need to look them up repeatedly.
     */
    static class ViewHolder {
        NetworkImageView thumbNail;
        TextView title;
        TextView description;

    }
}