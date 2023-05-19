package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername,edEmail,edPassword,edconfirmPassword;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername = findViewById(R.id.editTextBAppFullName);
        edEmail = findViewById(R.id.editTextBAppAdress);
        edPassword = findViewById(R.id.editTextAppBContactNumber);
        edconfirmPassword = findViewById(R.id.editTextBAppFees);
        btn = findViewById(R.id.buttonBookAppointment);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edconfirmPassword.getText().toString();
                String email = edEmail.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if (username.length() == 0 || password.length() == 0 || confirm.length() == 0 || email.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill All details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.compareTo(confirm)==0) {
                        if (isValid(password)) {
                             db.register(username,email,password);
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter,digit and special symbol ", Toast.LENGTH_SHORT).show();
                        }
                    }
                   else {
                        Toast.makeText(getApplicationContext(), "Password and ConfirmPassword didn't Match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String password) {
        int hasLetter = 0, hasDigit = 0, hasSpecialChar = 0;
        if (password.length() < 8) {
            return false;
        } else {
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (Character.isLetter(c)) {
                    hasLetter = 1;
                } else if (Character.isDigit(c)) {
                    hasDigit = 1;
                } else if (c >= 33 && c <= 46 || c == 64) {
                    hasSpecialChar = 1;
                }
            }
            return hasLetter == 1 && hasDigit == 1 && hasSpecialChar == 1;
        }
    }
}