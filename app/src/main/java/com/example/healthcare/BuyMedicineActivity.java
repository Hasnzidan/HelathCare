package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private final String[][] packages = {

            {"Uprise-03 1000IU Capsule", " ", " ", " ", "50"},
            {"HealthVit Chromium Pollinate 200mcg", " ", " ", "", "350"},
            {"Vitamin B Complex Capsule", " ", " ", "", "400"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule", " ", " ", "", "600"},
            {"Dolo 650 Tablet", " ", " ", "", "620"},
            {"Crocin 650 Advance Tablet", " ", " ", "", "40"},
            {"Strepsils Medicated Lozengers for Sore Throat", " ", " ", "", "100"},
            {"Tala 1mg Calcium + Vitamin D3", " ", " ", "", "230"},
            {"Feronia -XT Tablet", " ", " ", "", "120"}

    };
    private final String[] packaged_details = {
           "Bulding and Keeping the Bones & teeth Strong\n"+
                   "Reducing Fatique/stress and muscular pains\n"+
                   "Boosting immunity and icreasing resistance against infection",
           "Chromium is an essintial trace mineral that plays an important role in helping insulin regulation",
            "Provides relief from vitamin B deficiencies\n"+
                    "Helps in formation of red bloodd cells\n"+
                    "Maintains healthy nervous system",
            "It  promotes health as well as skin bebfit\n"+
                    "It helps reduce skin blemish and pigmentation\n"+
                    "It act as safegaurd hte skin from the harsh UVA and UVB sun rays.",
            "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical",
            "Helps release fever and bring down a high temperature\n"+
                    "Suitable for people with a heart condition or high blood pressure",
            "Relives the symptoms of a bacterial throat infection and soothes the recovery process\n"+
                    "Provides a warm and comforting feeling during sore throat",
            "Reduces the risk of calcium deficiency, Rickets, and Osteoporosis",
            "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"
    };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listViewBM);
        btnBack = findViewById(R.id.buttonBMBack);
        btnGoToCart = findViewById(R.id.buttonBMGoToCart);

        btnGoToCart.setOnClickListener(v -> startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class)));
        btnBack.setOnClickListener(v -> startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class)));


        list = new ArrayList<>();
        for (String[] aPackage : packages) {
            item = new HashMap<>();
            item.put("line1", aPackage[0]);
            item.put("line2", aPackage[1]);
            item.put("line3", aPackage[2]);
            item.put("line4", aPackage[3]);
            item.put("line5", "Total Cost" + aPackage[4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", packaged_details[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }
        });


    }
}