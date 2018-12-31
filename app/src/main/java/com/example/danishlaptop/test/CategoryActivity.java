package com.example.danishlaptop.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoryActivity extends AppCompatActivity {

    Button HWBtn;
    Button BWBtn;
    Button LWBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        HWBtn=findViewById(R.id.HWBtn);
        BWBtn=findViewById(R.id.BWBtn);
        LWBtn=findViewById(R.id.LWBtn);

        BWBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryActivity.this,UserHollwoodActivity.class);
                startActivity(intent);
            }
        });



        HWBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryActivity.this,UserHollwoodActivity.class);
                startActivity(intent);
            }
        });
    }
}
