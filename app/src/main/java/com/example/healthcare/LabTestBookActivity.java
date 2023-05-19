package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.editTextBMBFullName);
        edcontact = findViewById(R.id.editTextBMBContact);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edpincode = findViewById(R.id.editTextBMBPincode);
        btnBooking = findViewById(R.id.buttonBMBBooking);

        Intent intent=getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText fields
                String name = edname.getText().toString();
                String address = edaddress.getText().toString();
                String contact = edcontact.getText().toString();
                String pincode = edpincode.getText().toString();

                // Check if any of the fields are empty
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(contact) || TextUtils.isEmpty(pincode)) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    return; // exit the onClick() method
                }

                // Continue with the booking if all fields are filled
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username, name, address, contact, Integer.parseInt(pincode), date.toString(), time.toString(), Float.parseFloat(price[1].toString()), "lab");
                db.removeCart(username,"lab");
                Toast.makeText(getApplicationContext(), "Your Booking is done Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));
            }
        });


    }
}