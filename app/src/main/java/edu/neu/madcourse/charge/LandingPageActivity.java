package edu.neu.madcourse.charge;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LandingPageActivity extends AppCompatActivity {
    private ColorMatrixColorFilter filter;
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
        int tagValue = (int) view.getTag();
        switch (view.getTag().toString())
        {
            case "1":
                if(view.getId() == R.id.mind1ImageView) {
                    ImageView m1 = findViewById(R.id.mind1ImageView);
                    if(m1.getColorFilter() == null) {
                        m1.setColorFilter(filter);
                        mind = tagValue;
                    } else {
                        m1.clearColorFilter();
                    }
                } else {
                    ImageView b1 = findViewById(R.id.body1ImageView);
                    if(b1.getColorFilter() == null) {
                        b1.setColorFilter(filter);
                        body = tagValue;
                    } else {
                        b1.clearColorFilter();
                    }
                }
                break;
            case "2":
                if(view.getId() == R.id.mind2ImageView) {
                    ImageView m2 = findViewById(R.id.mind2ImageView);
                    if(m2.getColorFilter() == null) {
                        m2.setColorFilter(filter);
                        mind = tagValue;
                    } else {
                        m2.clearColorFilter();
                    }
                } else {
                    ImageView b2 = findViewById(R.id.body2ImageView);
                    if(b2.getColorFilter() == null) {
                        b2.setColorFilter(filter);
                        body = tagValue;
                    } else {
                        b2.clearColorFilter();
                    }
                }
                break;
            case "3":
                if(view.getId() == R.id.mind3ImageView) {
                    ImageView m3 = findViewById(R.id.mind3ImageView);
                    if(m3.getColorFilter() == null) {
                        m3.setColorFilter(filter);
                        mind = tagValue;
                    } else {
                        m3.clearColorFilter();
                    }
                } else {
                    ImageView b3 = findViewById(R.id.body3ImageView);
                    if(b3.getColorFilter() == null) {
                        b3.setColorFilter(filter);
                        body = tagValue;
                    } else {
                        b3.clearColorFilter();
                    }
                }
                break;
            case "4":
                if(view.getId() == R.id.mind4ImageView) {
                    ImageView m4 = findViewById(R.id.mind4ImageView);
                    if(m4.getColorFilter() == null) {
                        m4.setColorFilter(filter);
                        mind = tagValue;
                    } else {
                        m4.clearColorFilter();
                    }
                } else {
                    ImageView b4 = findViewById(R.id.body4ImageView);
                    if(b4.getColorFilter() == null) {
                        b4.setColorFilter(filter);
                        body = tagValue;
                    } else {
                        b4.clearColorFilter();
                    }
                }
                break;
            case "5":
                if(view.getId() == R.id.mind5ImageView) {
                    ImageView m5 = findViewById(R.id.mind5ImageView);
                    if(m5.getColorFilter() == null) {
                        m5.setColorFilter(filter);
                        mind = tagValue;
                    } else {
                        m5.clearColorFilter();
                    }
                } else {
                    ImageView b5 = findViewById(R.id.body5ImageView);
                    if(b5.getColorFilter() == null) {
                        b5.setColorFilter(filter);
                        body = tagValue;
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
}