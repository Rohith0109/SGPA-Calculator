package com.example.sgpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CalculationActivity extends AppCompatActivity {

    EditText marks1, marks2, marks3, marks4,  marks5, marks6, marks7, marks8, credits1, credits2, credits3, credits4, credits5, credits6, credits7, credits8;
    Button btnCal;
    TextView disResult;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        init();




        btnCal = findViewById(R.id.btnCal);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DecimalFormat df = new DecimalFormat("##.##");

                double points = (conv(marks1) * conv(credits1)) +
                        (conv(marks2) * conv(credits2)) +
                        (conv(marks3) * conv(credits3)) +
                        (conv(marks4) * conv(credits4)) +
                        (conv(marks5) * conv(credits5)) +
                        (conv(marks6) * conv(credits6)) +
                        (conv(marks7) * conv(credits7)) +
                        (conv(marks8) * conv(credits8));
                double credits = conv(credits1) +
                        conv(credits2) +
                        conv(credits3) +
                        conv(credits4) +
                        conv(credits5) +
                        conv(credits6) +
                        conv(credits7) +
                        conv(credits8);
                try {
                    if (points == 0 || credits == 0) {
                        disResult.setText("Invalid input");
                        Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                        closeKeyboard();
                        return;
                    }
                } catch(Exception e){
                    return;
                }

                double result = 0;
                try {
                    result = Double.parseDouble(df.format(points/credits));
                }
                catch (Exception e){
                    result = 0;
                }
                disResult.setText("Your SGPA is "+result);
                closeKeyboard();
            }
        });
    }

    private void init(){
        marks1=findViewById(R.id.marks1);
        marks2=findViewById(R.id.marks2);
        marks3=findViewById(R.id.marks3);
        marks4=findViewById(R.id.marks4);
        marks5=findViewById(R.id.marks5);
        marks6=findViewById(R.id.marks6);
        marks7=findViewById(R.id.marks7);
        marks8=findViewById(R.id.marks8);
        credits1=findViewById(R.id.credits1);
        credits2=findViewById(R.id.credits2);
        credits3=findViewById(R.id.credits3);
        credits4=findViewById(R.id.credits4);
        credits5=findViewById(R.id.credits5);
        credits6=findViewById(R.id.credits6);
        credits7=findViewById(R.id.credits7);
        credits8=findViewById(R.id.credits8);
        disResult = findViewById(R.id.disResult);
    }

    private double conv(EditText edt){
        try{
            return Double.parseDouble(edt.getText().toString());
        }
        catch (Exception e){
            return 0;
        }
    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        InputMethodManager inm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}