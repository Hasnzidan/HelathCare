package com.example.healthcare;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NotificationBooking extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton;
    private Button timeButton;
    private String appointmentDate;
    private String appointmentTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_booking);

        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextBAppFullName);
        ed2 = findViewById(R.id.editTextBAppAdress);
        ed3 = findViewById(R.id.editTextAppBContactNumber);
        ed4 = findViewById(R.id.editTextBAppFees);
        dateButton = findViewById(R.id.buttonBAppDate);
        timeButton = findViewById(R.id.buttonBAppTime);
        Button btnBack = findViewById(R.id.buttonBAppBack);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);
        dateButton.setKeyListener(null);
        timeButton.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees:"+fees+"/-");

        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        ArrayList<String> orderData = db.getOrderData(username);
        if (orderData.size() > 0) {
            String[] orderDetails = orderData.get(0).split("\\$");
            if (orderDetails.length >= 7) {
                String orderDate = orderDetails[4];
                String orderTime = orderDetails[5];
                dateButton.setText(orderDate);
                timeButton.setText(orderTime);
            }
        }
        btnBack.setOnClickListener(view -> finish());

    }



}