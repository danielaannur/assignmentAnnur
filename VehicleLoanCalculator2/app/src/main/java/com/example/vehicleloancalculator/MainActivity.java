package com.example.vehicleloancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText etVehiclePrice, etDownPayment, etLoanPeriod, etInterestRate;
    Button btnCalculate;
    LinearLayout layoutResult;
    TextView tvLoanAmount, tvTotalInterest, tvTotalPayment, tvMonthlyPayment;

    // Bottom navigation TextViews
    TextView navHome, navAbout;

    DecimalFormat df = new DecimalFormat("#,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize input fields
        etVehiclePrice = findViewById(R.id.etVehiclePrice);
        etDownPayment = findViewById(R.id.etDownPayment);
        etLoanPeriod = findViewById(R.id.etLoanPeriod);
        etInterestRate = findViewById(R.id.etInterestRate);

        btnCalculate = findViewById(R.id.btnCalculate);

        // Initialize result layout and TextViews
        layoutResult = findViewById(R.id.layoutResult);
        tvLoanAmount = findViewById(R.id.tvLoanAmount);
        tvTotalInterest = findViewById(R.id.tvTotalInterest);
        tvTotalPayment = findViewById(R.id.tvTotalPayment);
        tvMonthlyPayment = findViewById(R.id.tvMonthlyPayment);

        // Initialize bottom navigation TextViews
        navHome = findViewById(R.id.navHome);
        navAbout = findViewById(R.id.navAbout);

        // Calculate button click listener
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLoan();
            }
        });

        // Bottom navigation click listeners
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Already on Home page", Toast.LENGTH_SHORT).show();
            }
        });

        navAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
    }

    // Method to calculate loan
    private void calculateLoan() {
        String priceStr = etVehiclePrice.getText().toString().trim();
        String downStr = etDownPayment.getText().toString().trim();
        String yearsStr = etLoanPeriod.getText().toString().trim();
        String interestStr = etInterestRate.getText().toString().trim();

        // Validate inputs
        if (priceStr.isEmpty() || downStr.isEmpty() || yearsStr.isEmpty() || interestStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price, down, years, rate;

        try {
            price = Double.parseDouble(priceStr);
            down = Double.parseDouble(downStr);
            years = Double.parseDouble(yearsStr);
            rate = Double.parseDouble(interestStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        if (down > price) {
            Toast.makeText(this, "Down payment cannot exceed vehicle price", Toast.LENGTH_SHORT).show();
            return;
        }

        // Perform calculations
        double loanAmount = price - down;
        double totalInterest = loanAmount * (rate / 100) * years;
        double totalPayment = loanAmount + totalInterest;
        double monthlyPayment = totalPayment / (years * 12);

        // Display results
        tvLoanAmount.setText("Loan Amount: RM " + df.format(loanAmount));
        tvTotalInterest.setText("Total Interest: RM " + df.format(totalInterest));
        tvTotalPayment.setText("Total Payment: RM " + df.format(totalPayment));
        tvMonthlyPayment.setText("Monthly Payment: RM " + df.format(monthlyPayment));

        layoutResult.setVisibility(View.VISIBLE);
    }
}
