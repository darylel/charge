package edu.neu.madcourse.charge.stretch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
import java.util.Scanner;

import edu.neu.madcourse.charge.R;

public class StretchActivity extends AppCompatActivity {
    private String TAG;
    private StretchAdapter stretchAdapter;
    private ArrayList<StretchVideo> stretchVideoList;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch);
        RecyclerView stretchRecyclerView = findViewById(R.id.stretch_recycler_view);
        TAG = "StretchActivity";
        stretchVideoList = new ArrayList<>();
        stretchAdapter = new StretchAdapter(stretchVideoList, stretchRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        stretchRecyclerView.setLayoutManager(layoutManager);
        stretchRecyclerView.setAdapter(stretchAdapter);
        handler = new Handler();
        getVideos();
    }

    private void getVideos() {
        Runnable runnable = new RunnableThread();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    class RunnableThread implements Runnable {

        private DatabaseReference databaseReference;
        private DatabaseReference videoDatabaseReference;

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void run() {

            databaseReference = FirebaseDatabase.getInstance().getReference().child("YouTubeAPI");

            URL url;
            String youTubeURL = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCBINFWq52ShSgUFEoynfSwg&maxResults=10&key=AIzaSyDoSLl3iQJVeRphG7GdF32pL4LAZmhQzjk";
//            String youTube = String.valueOf(databaseReference);
            String GET = "GET";

            String jsonItems = "items";
            String jsonId = "id";
            String jsonSnippet = "snippet";
            String jsonThumbnails = "thumbnails";
            String jsonMedium = "medium";

            String stringURL = "url";
            String stringTitle = "title";
            String stringVideoId = "videoId";

            try {
                url = new URL(youTubeURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(GET);
                connection.setDoInput(true);
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                final String response = convertStringToStream(inputStream);

                JSONObject jsonObject = new JSONObject(response);
                JSONArray videoArray = jsonObject.getJSONArray(jsonItems);

                for (int i = 0; i < videoArray.length(); i++) {
                    JSONObject video = videoArray.getJSONObject(i);
                    JSONObject videoId = video.getJSONObject(jsonId);
                    JSONObject snippet = video.getJSONObject(jsonSnippet);
                    JSONObject thumbnails = snippet.getJSONObject(jsonThumbnails).getJSONObject(jsonMedium);

                    StretchVideo stretchVideo = new StretchVideo();
                    stretchVideo.setLink(thumbnails.getString(stringURL));
                    stretchVideo.setVideo(videoId.getString(stringVideoId));
                    stretchVideo.setTitle(snippet.getString(stringTitle));
                    stretchVideoList.add(stretchVideo);
                }

                if (stretchVideoList.size() > 0) {
                    handler.post(() ->  stretchAdapter.notifyDataSetChanged());
                }
            } catch (MalformedURLException e) {
                Log.e(TAG, "Malformed URL Exception thrown");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG, "Protocol Exception thrown");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG, "IO Exception thrown");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG, "JSON Exception thrown");
                e.printStackTrace();
            }
        }
        private String convertStringToStream(InputStream inputStream) {
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            String stream;
            if (scanner.hasNext()) {
                stream = scanner.next().replace(",", ",\n");
            } else {
                stream = "";
            }
            return stream;
        }
    }
}