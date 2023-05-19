package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Ahmed Yasser", "Hospital Address : Cairo", "EXP : 5yrs", "Mobile:01002563444", "600"},
                    {"Doctor Name : Ahmed Wael", "Hospital Address : Alex", "EXP : 15yrs", "Mobile:01002563424", "900"},
                    {"Doctor Name : Wael Yasser", "Hospital Address : KafrELsheikh", "EXP : 8yrs", "Mobile:01002563214", "300"},
                    {"Doctor Name : Yasser Ahmed", "Hospital Address : Tanta", "EXP : 6yrs", "Mobile:01002563425", "500"},
                    {"Doctor Name : Mohammed Yasser", "Hospital Address : Mansoura", "EXP : 7yrs", "Mobile:01002563445", "800"}
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Sameh Yasser", "Hospital Address : Cairo", "EXP : 5yrs", "Mobile o:01002563444", "600"},
                    {"Doctor Name : Mazen Wael", "Hospital Address : Alex", "EXP : 15yrs", "Mobile o:01002563424", "900"},
                    {"Doctor Name : Yasser Yasser", "Hospital Address : KafrELsheikh", "EXP : 8yrs", "Mobile o:01002563214", "300"},
                    {"Doctor Name : Wael Ahmed", "Hospital Address : Tanta", "EXP : 6yrs", "Mobile o:01002563425", "500"},
                    {"Doctor Name : Mohammed Yasser", "Hospital Address : Mansoura", "EXP : 7yrs", "Mobile o:01002563445", "800"}
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Ahmed Yasser", "Hospital Address : Cairo", "EXP : 5yrs", "Mobile o:01002563444", "600"},
                    {"Doctor Name : Ahmed Wael", "Hospital Address : Alex", "EXP : 15yrs", "Mobile o:01002563424", "900"},
                    {"Doctor Name : Wael Yasser", "Hospital Address : KafrELsheikh", "EXP : 8yrs", "Mobile o:01002563214", "300"},
                    {"Doctor Name : Yasser Ahmed", "Hospital Address : Tanta", "EXP : 6yrs", "Mobile o:01002563425", "500"},
                    {"Doctor Name : Mohammed Yasser", "Hospital Address : Mansoura", "EXP : 7yrs", "Mobile o:01002563445", "800"}
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name : Ahmed Yasser", "Hospital Address : Cairo", "EXP : 5yrs", "Mobile:01002563444", "600"},
                    {"Doctor Name : Ahmed Wael", "Hospital Address : Alex", "EXP : 15yrs", "Mobile:01002563424", "900"},
                    {"Doctor Name : Wael Yasser", "Hospital Address : KafrELsheikh", "EXP : 8yrs", "Mobile:01002563214", "300"},
                    {"Doctor Name : Yasser Ahmed", "Hospital Address : Tanta", "EXP : 6yrs", "Mobile:01002563425", "500"},
                    {"Doctor Name : Mohammed Yasser", "Hospital Address : Mansoura", "EXP : 7yrs", "Mobile:01002563445", "800"}
            };
    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Ahmed Yasser", "Hospital Address : Cairo", "EXP : 5yrs", "Mobile o:01002563444", "600"},
                    {"Doctor Name : Ahmed Wael", "Hospital Address : Alex", "EXP : 15yrs", "Mobile o:01002563424", "900"},
                    {"Doctor Name : Wael Yasser", "Hospital Address : KafrELsheikh", "EXP : 8yrs", "Mobile o:01002563214", "300"},
                    {"Doctor Name : Yasser Ahmed", "Hospital Address : Tanta", "EXP : 6yrs", "Mobile o:01002563425", "500"},
                    {"Doctor Name : Mohammed Yasser", "Hospital Address : Mansoura", "EXP : 7yrs", "Mobile o:01002563445", "800"}
            };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList List;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewHADTitle);
        btn = findViewById(R.id.buttonHADBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        if (title.compareTo("Family physicians")==0)
            doctor_details = doctor_details1;
        else
        if (title.compareTo("Dentist")==0)
            doctor_details = doctor_details2;
        else
        if (title.compareTo("Surgeon")==0)
            doctor_details = doctor_details3;
        else
        if (title.compareTo("Dietician")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class ));
            }
        });
        List = new ArrayList<>();
        for (int i=0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees"+doctor_details[i][4]+"/-");
            List.add(item);
        }
        sa = new SimpleAdapter(this,List,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        ListView lst = findViewById(R.id.imageViewHAD);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}