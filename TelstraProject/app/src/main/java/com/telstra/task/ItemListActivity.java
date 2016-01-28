package com.telstra.task;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telstra.task.adapter.ItemListAdapter;
import com.telstra.task.common.ApplicationController;
import com.telstra.task.common.CommVar;
import com.telstra.task.model.ListItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruban on 1/22/2016.
 * This Activity Class is used to show json data from url in a list
 */

public class ItemListActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView itemListView;
    private ItemListAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //request server every time user do pull to refresh
                sendServerRequest();

            }
        });
        itemListView = (ListView) findViewById(R.id.itemListview);
            progressBar.setVisibility(View.VISIBLE);
            sendServerRequest();//request server for data


    }

    /*
     * This method is used to send string request to server
     * return void
     */
    private void sendServerRequest() {
        // Instantiate the RequestQueue.
        if (ApplicationController.isConnectingToInternet()) {
            RequestQueue queue = Volley.newRequestQueue(this);


            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, CommVar.url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("success Response", response);
                            //update Array list and load listview
                            updateListAndroidListView(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Error Response", error.getMessage());
                    ApplicationController.displayToast(CommVar.NO_RESPONSE);

                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        } else {
            ApplicationController.displayToast(CommVar.NO_INTERNET);
            progressBar.setVisibility(View.INVISIBLE);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }



    /*
     * This method is used to Parse response json object and store it in arraylist and later display it in listview
     * return void
     */

    private void updateListAndroidListView(String response) {
        JSONArray rowArray = null;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            rowArray = jsonObject.getJSONArray(CommVar.ROWS);
            setTitle(jsonObject.getString(CommVar.TITLE));
        } catch (JSONException e) {
            ApplicationController.displayToast(CommVar.INVALID_RESPONSE);
        }

       //GSON Library used for Parsing JSON
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();
        List<ListItem> itemList= new ArrayList<ListItem>();//store rows in Arraylist
        itemList= Arrays.asList(gson.fromJson(rowArray.toString(), ListItem[].class));


        if(itemList.size()>0){//if list view data is empty throw an alert
            adapter = new ItemListAdapter(this,itemList);
            itemListView.setAdapter(adapter);
        }
        else{
            ApplicationController.displayToast(CommVar.EMPTY_VALUES);
        }
        mSwipeRefreshLayout.setRefreshing(false);
        // notifying list adapter about data changes
        // so that it renders the list view with updated data
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.INVISIBLE);

    }

}
