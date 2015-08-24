package com.example.nikhil.skygear1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by nikhil on 22-08-2015.
 */
public class Home extends Activity {
    private ListView listView;
    private ListViewAdapter listAdapter;

    private boolean homeSet = false;
    private boolean eventsSet = false;
    private boolean friendsSet = false;
    private boolean groupsSet = false;
    private boolean professionalServicesSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ListViewAdapter(this, R.layout.list_item_layout, getData());
        listView.setAdapter(listAdapter);

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
