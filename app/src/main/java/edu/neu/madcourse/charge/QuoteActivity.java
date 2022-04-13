package edu.neu.madcourse.charge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Objects;

public class QuoteActivity extends AppCompatActivity {
    private TextView quote;
    private TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        // Set the custom app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);
        TextView toolbar = findViewById(R.id.custom_toolbar);
        toolbar.setText("Quotations");

        Button inspireMe = findViewById(R.id.buttonNewQuote);
        quote = findViewById(R.id.textViewQuote);
        author = findViewById(R.id.textViewAuthor);

        inspireMe.setOnClickListener(view -> {
            try {
                getInspired();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(this,"Unable to retrieve quote", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getInspired() throws MalformedURLException {
        String line;
        StringBuilder quoteDetails = new StringBuilder();
        String key = "f07cbcf0971fbc180333bc776edf6a404210eb62";

        try {
            URL url = new URL("https://zenquotes.io/api/random/" + key);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            InputStream data = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(data));

            while ((line = in.readLine()) != null) {
                quoteDetails.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}