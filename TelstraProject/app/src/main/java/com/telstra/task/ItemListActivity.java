package com.telstra.task;


import android.app.LauncherActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.telstra.task.adapter.ItemListAdapter;
import com.telstra.task.model.ListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ItemListActivity extends AppCompatActivity {

    SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView itemListview;
    private ItemListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                     @Override
                                                     public void onRefresh() {

                                                         sendServerRequest();

                                                     }
                                                 });
                itemListview = (ListView) findViewById(R.id.itemListview);
        adapter = new ItemListAdapter(this);

        sendServerRequest();

    }
    private void sendServerRequest(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://dl.dropboxusercontent.com/u/746330/facts.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("success Response", response);
                        ListItem.listItemsArray.clear();
                        JSONArray rowArray=null;
                        try {
                        JSONObject jsonObject = new JSONObject(response);
                         rowArray= new JSONArray();
                            rowArray = jsonObject.getJSONArray("rows");


                        for(int i=0;i<rowArray.length();i++) {
                            String title = rowArray.getJSONObject(i).getString("title");
                            String desc= rowArray.getJSONObject(i).getString("description");
                            String url= rowArray.getJSONObject(i).getString("imageHref").replaceAll(" ", "%20");;
                            ListItem listItem = new ListItem(title,desc,url);
                            ListItem.listItemsArray.add(listItem);
                         }//end for loop

                        }//end try
                        catch (JSONException e){

                        }
                        itemListview.setAdapter(adapter);
                        mSwipeRefreshLayout.setRefreshing(false);
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error Response",error.getMessage());

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }



}
