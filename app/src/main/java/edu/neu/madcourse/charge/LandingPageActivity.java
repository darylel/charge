package edu.neu.madcourse.charge;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LandingPageActivity extends AppCompatActivity {

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
        ImageView mind1 = findViewById(R.id.mind1ImageView);

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
}