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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import edu.neu.madcourse.charge.stretch.StretchActivity;

public class LandingPageActivity extends AppCompatActivity {
    private ColorMatrixColorFilter filter;
    ImageView m1;
    ImageView m2;
    ImageView m3;
    ImageView m4;
    ImageView m5;
    ImageView b1;
    ImageView b2;
    ImageView b3;
    ImageView b4;
    ImageView b5;
    private int mind;
    private int body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the custom app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);

        setContentView(R.layout.activity_landing_page);

        ImageView account = findViewById(R.id.accountImageView);
        ImageView gratitude = findViewById(R.id.imageViewGratitude);
        ImageView doodle = findViewById(R.id.imageViewDoodle);
        ImageView walking = findViewById(R.id.imageViewWalking);
        ImageView stretching = findViewById(R.id.imageViewStretching);
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
                    }
                } else {
                    if(b1.getColorFilter() == null) {
                        resetColor("body");
                        b1.setColorFilter(filter);
                        body = 1;
                    } else {
                        b1.clearColorFilter();
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
                    }
                } else {
                    if(b2.getColorFilter() == null) {
                        resetColor("body");
                        b2.setColorFilter(filter);
                        body = 2;
                    } else {
                        b2.clearColorFilter();
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
                    }
                } else {
                    if(b3.getColorFilter() == null) {
                        resetColor("body");
                        b3.setColorFilter(filter);
                        body = 3;
                    } else {
                        b3.clearColorFilter();
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
                    }
                } else {
                    if(b4.getColorFilter() == null) {
                        resetColor("body");
                        b4.setColorFilter(filter);
                        body = 4;
                    } else {
                        b4.clearColorFilter();
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
                    }
                } else {
                    if(b5.getColorFilter() == null) {
                        resetColor("body");
                        b5.setColorFilter(filter);
                        body = 5;
                    } else {
                        b5.clearColorFilter();
                    }
                }
                break;
            default:
                Toast.makeText(this, "This works default", Toast.LENGTH_SHORT).show();
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
        } else {
            for(ImageView x : bodyEmoticons) {
                x.clearColorFilter();
            }
        }
    }

    public void suggestActivity(View view) {
        Toast.makeText(this, "Suggest click works", Toast.LENGTH_SHORT).show();
    }
}