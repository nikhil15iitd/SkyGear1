package com.example.nikhil.skygear1;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by nikhil on 23-08-2015.
 */
public class CreateEvent extends Activity {
    private ListView listView;
    private ListViewAdapter listAdapter;
    DatePicker pickerDate;
    TextView info;

    //Variables to hold form data
    String curEventType;
    String curVenue;
    String curTheme;
    String curDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        //setters for drop down lists
        setEventType();
        setVenue();
        setThemes();
        setDate();
    }

    private void setVenue() {
        Spinner dropdown = (Spinner)findViewById(R.id.venue);
        String[] items = getResources().getStringArray(R.array.venues);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // Whatever you want to happen when the first item gets selected
                        break;
                    case 1:
                        // Whatever you want to happen when the second item gets selected
                        break;
                    case 2:
                        // Whatever you want to happen when the thrid item gets selected
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setThemes() {
        Spinner dropdown = (Spinner)findViewById(R.id.themeType);
        String[] items = getResources().getStringArray(R.array.themes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // Whatever you want to happen when the first item gets selected
                        break;
                    case 1:
                        // Whatever you want to happen when the second item gets selected
                        break;
                    case 2:
                        // Whatever you want to happen when the thrid item gets selected
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setDate() {
        info = (TextView)findViewById(R.id.info);
        pickerDate = (DatePicker)findViewById(R.id.pickerdate);

        Calendar today = Calendar.getInstance();

        pickerDate.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view,
                                              int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(),
                                "onDateChanged", Toast.LENGTH_SHORT).show();

                        info.setText(
                                "Year: " + year + "\n" +
                                        "Month of Year: " + monthOfYear + "\n" +
                                        "Day of Month: " + dayOfMonth);

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

    private void setEventType(){
        Spinner dropdown = (Spinner)findViewById(R.id.eventType);
        String[] items = getResources().getStringArray(R.array.event_types);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // Whatever you want to happen when the first item gets selected
                        break;
                    case 1:
                        // Whatever you want to happen when the second item gets selected
                        break;
                    case 2:
                        // Whatever you want to happen when the thrid item gets selected
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params){
            // HTTP Post
            try {
                JSONObject jsonobj = new JSONObject();
                if(curEventType!=null && curDate!=null && curTheme!=null && curVenue!=null){
                    jsonobj.put("Event Type",curEventType);
                    jsonobj.put("Venue",curVenue);
                    jsonobj.put("Theme", curTheme);
                    jsonobj.put("Date", curDate);
                    URL url = new URL("http://192.168.1.3:3000/");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setRequestProperty("Accept", "application/json");
                    urlConnection.setRequestMethod("POST");

                    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                    wr.write(jsonobj.toString());
                    wr.flush();
                }
            } catch (Exception e ) {
                System.out.println(e.getMessage());
                e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

}
