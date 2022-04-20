package edu.neu.madcourse.charge.stretch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import edu.neu.madcourse.charge.R;

public class StretchActivity extends AppCompatActivity {
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch);
        RecyclerView stretchRecyclerView = findViewById(R.id.stretch_recycler_view);
        TAG = "StretchActivity";
        ArrayList<StretchVideo> stretchVideoArrayList = new ArrayList<>();
        StretchAdapter stretchAdapter = new StretchAdapter(stretchVideoArrayList, StretchActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        stretchRecyclerView.setLayoutManager(layoutManager);
        stretchRecyclerView.setAdapter(stretchAdapter);
    }

    private void getVideo() {
        Runnable runnable = new RunnableThread();
        Thread thread = new Thread(runnable);
        thread.start();

    }

    class RunnableThread implements Runnable {

        @Override
        public void run() {
            try {
                URL url = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCBINFWq52ShSgUFEoynfSwg&maxResults=50&key=AIzaSyCgyiPkLYH0jQI0wrZlhzZscbMCPnJmSt4");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                String inputStream = connection.getInputStream().toString();
                JSONObject jsonObject = new JSONObject(inputStream);
                JSONArray jsonArray = jsonObject.getJSONArray("items");

            } catch (MalformedURLException e) {
                Log.e(TAG, "Malformed URL Exception");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG, "Protocol Exception");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG, "IO Exception");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG, "JSON Exception");
                e.printStackTrace();
            }
        }
    }
}