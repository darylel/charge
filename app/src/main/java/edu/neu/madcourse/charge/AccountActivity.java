package edu.neu.madcourse.charge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class AccountActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Set the custom app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);

        auth = FirebaseAuth.getInstance();

        Button logoutButton = findViewById(R.id.buttonLogout);
        TextView username = findViewById(R.id.textViewUsername);

        username.setText(auth.getCurrentUser().getEmail());

        logoutButton.setOnClickListener(view -> {
            auth.signOut();
            startActivity(new Intent(AccountActivity.this, LoginActivity.class));
        });
    }
}