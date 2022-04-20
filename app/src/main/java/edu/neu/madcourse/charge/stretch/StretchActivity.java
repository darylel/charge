package edu.neu.madcourse.charge.stretch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import edu.neu.madcourse.charge.R;

public class StretchActivity extends AppCompatActivity {
    private String TAG;
    private RecyclerView stretchRecyclerView;
    private StretchAdapter stretchAdapter;
    private ArrayList<StretchVideo> stretchVideoList;
    RecyclerView.LayoutManager layoutManager;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch);
        stretchRecyclerView = findViewById(R.id.stretch_recycler_view);
        TAG = "StretchActivity";
        stretchVideoList = new ArrayList<>();
        stretchAdapter = new StretchAdapter(stretchVideoList, StretchActivity.this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
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
        @Override
        public void run() {

            URL url;
            String youTubeURL = "https://www.googleapis.com/youtube/v3/search?part=snippet&" +
                    "channelId=UCBINFWq52ShSgUFEoynfSwg&maxResults=10&key=AIzaSyCgyiPkLYH0j" +
                    "QI0wrZlhzZscbMCPnJmSt4";
            String GET = "GET";
            String jsonVideos = "videos";
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

                String inputStream = connection.getInputStream().toString();
                JSONObject jsonObject = new JSONObject(inputStream);
                JSONArray videos = jsonObject.getJSONArray(jsonVideos);


                for (int i = 0; i < videos.length(); i++) {
                    JSONObject video = videos.getJSONObject(i);
                    JSONObject videoId = video.getJSONObject(jsonId);
                    JSONObject snippet = jsonObject.getJSONObject(jsonSnippet);
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
    }
}