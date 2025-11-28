package com.example.vehicleloancalculator;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    ImageView ivAppIcon;
    TextView tvAppName, tvVersion, tvAuthorName, tvMatric, tvCourse, tvGithub;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ivAppIcon = findViewById(R.id.ivAppIcon);
        tvAppName = findViewById(R.id.tvAppName);
        tvVersion = findViewById(R.id.tvVersion);
        tvAuthorName = findViewById(R.id.tvAuthorName);
        tvMatric = findViewById(R.id.tvMatric);
        tvCourse = findViewById(R.id.tvCourse);
        tvGithub = findViewById(R.id.tvGithub);
        btnBack = findViewById(R.id.btnBack);

        // Set values
        tvAppName.setText("Vehicle Loan Calculator");
        tvVersion.setText("Version 1.0");
        tvAuthorName.setText("Author: ANNUR DANIELA BINTI MOHAMAD RIZAL");
        tvMatric.setText("Matric No: 2023226202 ");
        tvCourse.setText("Course: ICT602");

        tvGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/yourrepo";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
