package com.example.healthcare;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton;
    private Button timeButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextBAppFullName);
        ed2 = findViewById(R.id.editTextBAppAdress);
        ed3 = findViewById(R.id.editTextAppBContactNumber);
        ed4 = findViewById(R.id.editTextBAppFees);
        dateButton = findViewById(R.id.buttonBAppDate);
        timeButton = findViewById(R.id.buttonBAppTime);
        Button btnBook = findViewById(R.id.buttonBookAppointment);
        Button btnBack = findViewById(R.id.buttonBAppBack);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

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


        initDatePicker();
        dateButton.setOnClickListener(view -> datePickerDialog.show());
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", month, dayOfMonth, year);
        dateButton.setText(formattedDate);

        initTimePicker();
        timeButton.setOnClickListener(view -> timePickerDialog.show());

        calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hours, minutes);
        timeButton.setText(formattedTime);

        btnBack.setOnClickListener(view -> startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class)));
        btnBook.setOnClickListener(view -> {
            Database db = new Database(getApplicationContext(),"healthcare",null,1);
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username","");
            if (db.checkAppointmentExists(username,title+" => "+fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1){
                Toast.makeText(getApplicationContext(), "Appointment already booked", Toast.LENGTH_SHORT).show();
            }else {
                db.addOrder(username,title+" => "+fullname, address, contact, 0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
                Toast.makeText(getApplicationContext(), "Appointment is done successfully ", Toast.LENGTH_SHORT).show();
                Intent serviceIntent = new Intent(this, NotificationService.class);
                serviceIntent.putExtra("title", fullname);
                serviceIntent.putExtra("date", dateButton.getText().toString());
                serviceIntent.putExtra("time", timeButton.getText().toString());
                serviceIntent.putExtra("doctorDetails", new String[]{fullname, address, contact, fees});
                startService(serviceIntent);
                startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));


            }
        });

    }
    private void initDatePicker(){
        @SuppressLint("SetTextI18n") DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, i, i1, i2) -> {
            i1=i1+1;
            dateButton.setText(12+"/"+i1+"/"+i);
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

    }

    private void initTimePicker(){

        @SuppressLint("SetTextI18n") TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, i, i1) -> timeButton.setText(i+":"+i1);
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }
}