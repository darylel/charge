package edu.neu.madcourse.charge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

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
import java.util.Objects;
import java.util.Scanner;

public class MoveActivity extends AppCompatActivity {
    private String TAG;
    private MoveAdapter moveAdapter;
    private ArrayList<MoveVideo> moveVideoList;
    private Handler handler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        // Set the custom app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);
        TextView toolbar = findViewById(R.id.custom_toolbar);
        toolbar.setText(R.string.move);

        RecyclerView stretchRecyclerView = findViewById(R.id.move_recycler_view);
        TAG = "MoveActivity";
        moveVideoList = new ArrayList<>();
        moveAdapter = new MoveAdapter(moveVideoList, stretchRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        stretchRecyclerView.setLayoutManager(layoutManager);
        stretchRecyclerView.setAdapter(moveAdapter);
        handler = new Handler();
        context = MoveActivity.this;
        getVideos();
    }

    private void getVideos() {
        Runnable runnable = new RunnableThread();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    class RunnableThread implements Runnable {

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void run() {

            URL url;
            String youTubeURL = context.getResources().getString(R.string.youtube_url);
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
                url = new URL(String.valueOf(youTubeURL));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(GET);
                connection.setDoInput(true);
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                final String response = convertStringToStream(inputStream);

                JSONObject jsonObject = new JSONObject(response);
                JSONArray videoArray = jsonObject.getJSONArray(jsonItems);

                for (int i = 0; i < videoArray.length(); i++) {
                    if (i != 1 && i != 2) {
                        JSONObject video = videoArray.getJSONObject(i);
                        JSONObject videoId = video.getJSONObject(jsonId);
                        JSONObject snippet = video.getJSONObject(jsonSnippet);
                        JSONObject thumbnails = snippet.getJSONObject(jsonThumbnails).getJSONObject(jsonMedium);

                        String link = thumbnails.getString(stringURL);
                        String videoIdString = videoId.getString(stringVideoId);
                        String title = snippet.getString(stringTitle)
                                .replace("&#39;", "'")
                                .replace("&amp;", "&");
                        MoveVideo moveVideo = new MoveVideo(title, link, videoIdString);
                        moveVideoList.add(moveVideo);
                    }
                }

                if (moveVideoList.size() > 0) {
                    handler.post(() ->  moveAdapter.notifyDataSetChanged());
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