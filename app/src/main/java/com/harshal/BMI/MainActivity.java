package com.harshal.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private TextView resultInfo;
    private RadioButton male;
    private RadioButton female;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Calling the findView method
        findView();
        setButtonClickListener();
        

//        String toastAlert = "This is an alert pop up by using TOAST attribute stored in variable";
//
//        /* Toast is a we can say attribute in android that is use to store the value that we want to show to the user as
//        a pop-up message. */
//        Toast.makeText(this, toastAlert, Toast.LENGTH_SHORT).show();
    }

    private void findView(){
        // Below this comment we are creating a special variable that store the id's of the XML layout attributes in java.
        resultInfo = findViewById(R.id.view_result);
        male = findViewById(R.id.radio_button_male);
        female = findViewById(R.id.radio_button_female);
        age = findViewById(R.id.text_Age);
        feet = findViewById(R.id.text_feet);
        inches = findViewById(R.id.text_inches);
        weight = findViewById(R.id.text_weight);
        calculate = findViewById(R.id.button_calculate);
    }

    private void setButtonClickListener() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiRes = calculateBmi();

                String ageText = age.getText().toString();
                int ageT = Integer.parseInt(ageText);
                if(ageT >= 18) {
                    displayResult(bmiRes);
                }
                else{
                    dispalyGuidence(bmiRes);
                }
            }
        });
    }


    private double calculateBmi() {
        String feetText = feet.getText().toString();
        String inchesText = inches.getText().toString();
        String weightText = weight.getText().toString();

        //converting the "String" into "int"
        int feetT = Integer.parseInt(feetText);
        int inchT = Integer.parseInt(inchesText);
        int weightT = Integer.parseInt(weightText);

        //converting the height in meters
        int totalInches = (feetT * 12) + inchT;
        double heightInMeters = totalInches * 0.0254;

        //calculating the bmi
        return weightT / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi){


        //converting double to string return
        String bmiResult = String.valueOf(bmi);

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        bmiResult = myDecimalFormatter.format(bmi);

        String fullResult;
        if(bmi < 18.5){
            fullResult = bmiResult + " - You are below the normal bmi rating!";
        }
        else if(bmi > 18.5 && bmi < 24.5){
            fullResult = bmiResult + " - Your BMI falls perfectly under the Normal and Healthy BMI rating!";
        }
        else{
            fullResult = bmiResult + " - Something is wrong with your health take care of your diet! ";
        }


        resultInfo.setText(fullResult);
    }
    private void dispalyGuidence(double bmi) {

        String bmiResult = String.valueOf(bmi);
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        bmiResult = myDecimalFormatter.format(bmi);

        String fullResult;
        if(male.isChecked()){
            fullResult = bmiResult + " - You need to concern to your male doctor as you are teen and teen's have different BMI range! ";
        }
        else if(female.isChecked()){
            fullResult = bmiResult + " - You need to concern to your female doctor as you are teen and teen's have different BMI range! ";
        }
        else {
            fullResult = bmiResult + " - You need to concern to your doctor as you are under AGE! ";
        }

    }

}