package pamo.bmicalc;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "pamo.bmicalc.TEST";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateBmi(View view) {
        Intent intent = new Intent(this, DisplayBmi.class);
        EditText getHeight = (EditText) findViewById(R.id.text_editHeight);
        EditText getWeight = (EditText) findViewById(R.id.text_editWeight);
        int height = Integer.parseInt(getHeight.getText().toString());
        int weight = Integer.parseInt(getWeight.getText().toString());
        int bmi_i = (int) Math.round(weight / Math.pow(((double) height / 100), 2));

        String bmiResult = Integer.toString(bmi_i);
        intent.putExtra(EXTRA_MESSAGE, bmiResult);
        startActivity(intent);
    }

}