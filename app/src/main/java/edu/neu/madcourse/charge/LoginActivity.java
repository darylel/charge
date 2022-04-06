package edu.neu.madcourse.charge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private TextView email;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set the custom app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        TextView register = findViewById(R.id.textViewRegisterText);
        Button signIn = findViewById(R.id.buttonLogin);

        signIn.setOnClickListener(view -> signInUser());

        register.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this,
                SignUpActivity.class)));
    }

    private void signInUser() {
        String emailRegister = email.getText().toString();
        String passwordRegister = "hello1234";

        if (emailRegister.isEmpty() || passwordRegister.isEmpty()) {
            Toast.makeText(this, "Email password cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(emailRegister, passwordRegister)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this,
                                    LandingPageActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Unable to login",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}