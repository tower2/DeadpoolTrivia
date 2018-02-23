package com.example.android.deadpooltrivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int pointCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Counts points
    public void addPoint(View view) {
        pointCount = pointCount + 1;
    }

    // ************* Put in onSumbit method
    // Adds correct answer
    public void onSubmit(View view) {

        // Extract the answer from illness_answer and verifies response.
        EditText illEditText = (EditText) findViewById(R.id.illness_answer);
        String answer = illEditText.getText().toString();
        illEditText.setText(answer);
        compareIll(answer);

        // Checks through checkbox answer and verifies correct boxes are checked
        CheckBox check1 = (CheckBox) findViewById(R.id.chimichanga);
        CheckBox check2 = (CheckBox) findViewById(R.id.handgun);
        CheckBox check3 = (CheckBox) findViewById(R.id.knife);
        CheckBox check4 = (CheckBox) findViewById(R.id.grenade);
        CheckBox check5 = (CheckBox) findViewById(R.id.sword);

        if (!check1.isChecked() && !check4.isChecked()) {
            if (check2.isChecked() && check3.isChecked() && check5.isChecked()) {
                pointCount += 3;
            }
        }

        displayDeadpool(pointCount);

        // Display message that answers have been received.
        Toast.makeText(this, "Your answers have been received. \n" + pointCount + " out of 8", Toast.LENGTH_SHORT).show();
        pointCount = 0;
    }

    private void compareIll(String cancer) {
        if (cancer.equalsIgnoreCase("cancer")) {
            pointCount += 1;
        }
    }

    private void displayDeadpool(int total) {
        // Goes to second screen

        // Compares score to verify if user passed or failed the trivia.
        if (total < 5) {
            Intent startYourDoomed = new Intent(this, yourDoomed.class);
            startActivity(startYourDoomed);

        } else {
            Intent startGoodJob = new Intent(this, goodJob.class);
            startActivity(startGoodJob);
        }
    }
}