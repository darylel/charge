package edu.neu.madcourse.charge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private DatabaseReference db;
    private EditText email;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Set the custom app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        email = findViewById(R.id.editTextEmailAddress);
        Button register = findViewById(R.id.buttonLogin);

        register.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        String emailAddress = email.getText().toString();
        String userPassword = "hello1234";

        if (emailAddress.isEmpty()) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            auth.createUserWithEmailAndPassword(emailAddress, userPassword).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    user = Objects.requireNonNull(task.getResult().getUser()).getUid();
                    // Set the default value of steps to 0 when a user first registers
                    db.child(user).child("steps").child("total").setValue(0);
                    db.child(user).child("steps").child("previous").setValue(0);

                    Toast.makeText(SignUpActivity.this, "New user successfully created",
                            Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(SignUpActivity.this, "Unable to create user " +
                                    Objects.requireNonNull(task.getException()).getMessage(),
                                    Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}