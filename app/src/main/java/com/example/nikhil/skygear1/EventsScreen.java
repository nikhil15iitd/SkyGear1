package com.example.nikhil.skygear1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by nikhil on 23-08-2015.
 */
public class EventsScreen extends Activity {
    private ListView listView;
    private ListViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_events);

        listView = (ListView) findViewById(R.id.listView);
        //listAdapter = new ListViewAdapter(this, R.layout.list_item_layout, getData());
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            }
        });

    }

    public void createNewEvent(View v){
        Intent intent = new Intent(this, CreateEvent.class);
        startActivity(intent);
    }
}
