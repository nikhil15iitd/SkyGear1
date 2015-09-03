package com.example.nikhil.skygear1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nikhil.skygear1.listview.adapter.FeedListAdapter;
import com.example.nikhil.skygear1.listview.app.AppController;
import com.example.nikhil.skygear1.listview.data.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method;

/**
 * Created by nikhil on 22-08-2015.
 */
public class Home extends Activity {
    private static final String TAG = Home.class.getSimpleName();
    private ListView listView;
    private ListViewAdapter listAdapter;

    private boolean homeSet = false;
    private boolean eventsSet = false;
    private boolean friendsSet = false;
    private boolean groupsSet = false;
    private boolean professionalServicesSet = false;

    private FeedListAdapter feedAdapter;
    private List<FeedItem> feedItems;

    //Set URL feed here
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        listView = (ListView) findViewById(R.id.listView);
        //listAdapter = new ListViewAdapter(this, R.layout.list_item_layout, getData());
        //listView.setAdapter(listAdapter);

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(Home.this, DetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image", item.getImage());

                //Start details activity
                startActivity(intent);
            }
        });
        */

        feedItems = new ArrayList<FeedItem>();

        feedAdapter = new FeedListAdapter(this, feedItems);
        listView.setAdapter(feedAdapter);

        // These two lines not needed,
        // just to get the look of facebook (changing background color & hiding the icon)
        //getActionBar().setBackgroundDrawable(new ColorDrawable(parseColor("#3b5998")));
        //getActionBar().setIcon(
        //        new ColorDrawable(getResources().getColor(android.R.color.transparent)));

        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
                    URL_FEED, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
        }
        
    }

    private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj
                        .getString("url");
                item.setUrl(feedUrl);

                feedItems.add(item);
            }

            // notify data changes to list adapater
            feedAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    // Prepare some dummy data for gridview
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, "Image#" + i));
        }
        return imageItems;
    }

    public void setHome(View v){
        homeSet=true;
        eventsSet=false;
        groupsSet=false;
        friendsSet=false;
        professionalServicesSet=false;
    }

    public void setEvents(View v){
        homeSet=false;
        eventsSet=true;
        groupsSet=false;
        friendsSet=false;
        professionalServicesSet=false;
        //Create intent
        Intent intent = new Intent(this, EventsScreen.class);
        startActivity(intent);
    }

    public void setGroups(View v){
        homeSet=false;
        eventsSet=false;
        groupsSet=true;
        friendsSet=false;
        professionalServicesSet=false;
    }

    public void setFriends(View v){
        homeSet=false;
        eventsSet=false;
        groupsSet=false;
        friendsSet=true;
        professionalServicesSet=false;
    }

    public void setProfessionalServices(View v){
        homeSet=false;
        eventsSet=false;
        groupsSet=false;
        friendsSet=false;
        professionalServicesSet=true;
    }
}
