package edu.neu.madcourse.charge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class AccountActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Set the custom app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        Button logoutButton = findViewById(R.id.buttonLogout);
        TextView username = findViewById(R.id.textViewUsername);
        Button update = findViewById(R.id.buttonUpdate);
        EditText newUsername = findViewById(R.id.editTextTextEmailAddress);
        ImageView edit = findViewById(R.id.imageViewEditUsername);

        username.setText(user.getEmail());

        logoutButton.setOnClickListener(view -> {
            auth.signOut();
            startActivity(new Intent(AccountActivity.this, LoginActivity.class));
        });

        update.setOnClickListener(view -> {
            if(newUsername.getText() == null || newUsername.getText().toString().equals("")) {
                Toast.makeText(this, "Username cannot be blank", Toast.LENGTH_SHORT).show();
            } else {
                user.updateEmail(newUsername.getText().toString())
                        .addOnCompleteListener(task -> {
                            if(task.isSuccessful()) {
                                Toast.makeText(AccountActivity.this,
                                        "Username successfully update",
                                        Toast.LENGTH_SHORT).show();

                                username.setText(user.getEmail());
                                newUsername.setVisibility(View.INVISIBLE);
                                update.setVisibility(View.INVISIBLE);
                            } else {
                                Toast.makeText(AccountActivity.this,
                                        "Unable to update username",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(e -> {
                            if (e instanceof FirebaseAuthRecentLoginRequiredException) {
                                Toast.makeText(AccountActivity.this, "Please logout and sign in to update username",
                                    Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        edit.setOnClickListener(view -> {
            if(update.getVisibility() == View.INVISIBLE) {
                update.setVisibility(View.VISIBLE);
                newUsername.setVisibility(View.VISIBLE);
            } else {
                update.setVisibility(View.INVISIBLE);
                newUsername.setVisibility(View.INVISIBLE);
            }
        });
    }
}