package edu.neu.madcourse.charge;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import edu.neu.madcourse.charge.stretch.StretchActivity;

public class LandingPageActivity extends AppCompatActivity {
    private ColorMatrixColorFilter filter;
    private ImageView m1;
    private ImageView m2;
    private ImageView m3;
    private ImageView m4;
    private ImageView m5;
    private ImageView b1;
    private ImageView b2;
    private ImageView b3;
    private ImageView b4;
    private ImageView b5;
    private int mind;
    private int body;
    private FirebaseAuth auth;
    private DatabaseReference db;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the custom app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);

        setContentView(R.layout.activity_landing_page);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser().getUid();
        db = FirebaseDatabase.getInstance().getReference(user);

        ImageView account = findViewById(R.id.accountImageView);
        ImageView gratitude = findViewById(R.id.imageViewGratitude);
        ImageView doodle = findViewById(R.id.imageViewDoodle);
        ImageView walking = findViewById(R.id.imageViewWalking);
        ImageView stretching = findViewById(R.id.imageViewStretching);
        ImageView quotes = findViewById(R.id.imageViewQuote);
        Button feelings = findViewById(R.id.suggestButton);
        m1 = findViewById(R.id.mind1ImageView);
        m2 = findViewById(R.id.mind2ImageView);
        m3 = findViewById(R.id.mind3ImageView);
        m4 = findViewById(R.id.mind4ImageView);
        m5 = findViewById(R.id.mind5ImageView);
        b1 = findViewById(R.id.body1ImageView);
        b2 = findViewById(R.id.body2ImageView);
        b3 = findViewById(R.id.body3ImageView);
        b4 = findViewById(R.id.body4ImageView);
        b5 = findViewById(R.id.body5ImageView);

        // Initialize mind and body values
        mind = 0;
        body = 0;

        // Initiate grayscale color matrix
        ColorMatrix grayscale = new ColorMatrix();
        grayscale.setSaturation(0f);
        filter = new ColorMatrixColorFilter(grayscale);

        account.setOnClickListener(view -> startActivity(new Intent(
                LandingPageActivity.this, AccountActivity.class
        )));

        gratitude.setOnClickListener(view -> startActivity(new Intent(
                LandingPageActivity.this, GratitudeActivity.class
        )));

        doodle.setOnClickListener(view -> startActivity(new Intent(
                LandingPageActivity.this, DoodleActivity.class
        )));

        walking.setOnClickListener(view -> startActivity(new Intent(
                LandingPageActivity.this, StepsActivity.class
        )));

        stretching.setOnClickListener(view -> startActivity(new Intent(
                LandingPageActivity.this, StretchActivity.class
        )));

        quotes.setOnClickListener(view -> startActivity(new Intent(
                LandingPageActivity.this, QuoteActivity.class
        )));
    }

    public void onClick(View view) {
        switch (view.getTag().toString())
        {
            case "1":
                if(view.getId() == R.id.mind1ImageView) {
                    if(m1.getColorFilter() == null) {
                        resetColor("mind");
                        m1.setColorFilter(filter);
                        mind = 1;
                    } else {
                        m1.clearColorFilter();
                        mind = 0;
                    }
                } else {
                    if(b1.getColorFilter() == null) {
                        resetColor("body");
                        b1.setColorFilter(filter);
                        body = 1;
                    } else {
                        b1.clearColorFilter();
                        body = 0;
                    }
                }
                break;
            case "2":
                if(view.getId() == R.id.mind2ImageView) {
                    if(m2.getColorFilter() == null) {
                        resetColor("mind");
                        m2.setColorFilter(filter);
                        mind = 2;
                    } else {
                        m2.clearColorFilter();
                        mind = 0;
                    }
                } else {
                    if(b2.getColorFilter() == null) {
                        resetColor("body");
                        b2.setColorFilter(filter);
                        body = 2;
                    } else {
                        b2.clearColorFilter();
                        body = 0;
                    }
                }
                break;
            case "3":
                if(view.getId() == R.id.mind3ImageView) {
                    if(m3.getColorFilter() == null) {
                        resetColor("mind");
                        m3.setColorFilter(filter);
                        mind = 3;
                    } else {
                        m3.clearColorFilter();
                        mind = 0;
                    }
                } else {
                    if(b3.getColorFilter() == null) {
                        resetColor("body");
                        b3.setColorFilter(filter);
                        body = 3;
                    } else {
                        b3.clearColorFilter();
                        body = 0;
                    }
                }
                break;
            case "4":
                if(view.getId() == R.id.mind4ImageView) {
                    if(m4.getColorFilter() == null) {
                        resetColor("mind");
                        m4.setColorFilter(filter);
                        mind = 4;
                    } else {
                        m4.clearColorFilter();
                        mind = 0;
                    }
                } else {
                    if(b4.getColorFilter() == null) {
                        resetColor("body");
                        b4.setColorFilter(filter);
                        body = 4;
                    } else {
                        b4.clearColorFilter();
                        body = 0;
                    }
                }
                break;
            case "5":
                if(view.getId() == R.id.mind5ImageView) {
                    if(m5.getColorFilter() == null) {
                        resetColor("mind");
                        m5.setColorFilter(filter);
                        mind = 5;
                    } else {
                        m5.clearColorFilter();
                        mind = 0;
                    }
                } else {
                    if(b5.getColorFilter() == null) {
                        resetColor("body");
                        b5.setColorFilter(filter);
                        body = 5;
                    } else {
                        b5.clearColorFilter();
                        body = 0;
                    }
                }
                break;
            default:
                break;
        }
    }

    public void resetColor(String type) {
        ArrayList<ImageView> mindEmoticons = new ArrayList<>(Arrays.asList(m1,m2,m3,m4,m5));
        ArrayList<ImageView> bodyEmoticons = new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5));

        // Reset all the emoticons of a single type to full color
        if(type.equals("mind")) {
            for(ImageView x : mindEmoticons) {
                x.clearColorFilter();
            }
            mind = 0;
        } else {
            for(ImageView x : bodyEmoticons) {
                x.clearColorFilter();
            }
            body = 0;
        }
    }

    public void suggestActivity(View view) {
        String newKey = db.child("feelings").push().getKey();

        if(mind != 0 && body != 0) {
            // Save values to database
            db.child("feelings").child(newKey).child("mind").setValue(mind);
            db.child("feelings").child(newKey).child("body").setValue(body);

            // Reset emoticon state and values
            resetColor("body");
            resetColor("mind");
        } else {
            Toast.makeText(this, "Mind and body must be selected",
                    Toast.LENGTH_SHORT).show();
        }
    }
}