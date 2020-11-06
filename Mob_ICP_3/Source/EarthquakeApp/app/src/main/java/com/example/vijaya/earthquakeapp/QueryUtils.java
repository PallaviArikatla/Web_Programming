package com.example.vijaya.earthquakeapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Earthquake> fetchEarthquakeData2(String requestUrl) {
        // An empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        //  URL object to store the url for a given string
        URL url = null;
        // A string to store the response obtained from rest call in the form of string
        String jsonResponse = "";
        StringBuilder result = new StringBuilder();
        try {
            //TODO: 1. Create a URL from the requestUrl string and make a GET request to it
            url = new URL(requestUrl);
            //TODO: 2. Read from the Url Connection and store it as a string(jsonResponse
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            jsonResponse = result.toString();
            Log.i("myTag",jsonResponse);
            Log.i("myTag","i am hereereree");


                /*TODO: 3. Parse the jsonResponse string obtained in step 2 above into JSONObject to extract the values of
                        "mag","place","time","url"for every earth quake and create corresponding Earthquake objects with them
                        Add each earthquake object to the list(earthquakes) and return it.
                */
            try {
                JSONObject obj = new JSONObject(jsonResponse);
                JSONArray values = obj.getJSONArray("features");
                JSONObject obj2;
                JSONObject obj1 = new JSONObject(values.getString(0));
                //
                for (int i = 0; i < values.length(); i++) {
                    //earthquakes[i] = jsonArray.getString(i);
                    obj1 = new JSONObject(values.getString(i));
                    obj2 = new JSONObject(obj1.getString("properties"));
                    Earthquake earth = new Earthquake(Double.parseDouble(obj2.getString("mag")), obj2.getString("place"), Long.parseLong(obj2.getString("time")), obj2.getString("url"));
                    earthquakes.add(earth);
                    Log.d("My App", obj2.getString("mag"));
                }
            } catch (Throwable t) {
                Log.e("My App", "Could not parse malformed JSON: \"" + jsonResponse + "\"");
            }

            // Return the list of earthquakes

        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception:  ", e);
        }
        // Return the list of earthquakes
        return earthquakes;
    }
}