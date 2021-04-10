package pamo.bmicalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayBmi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bmi);

        Intent intent = getIntent();
        String result = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView tv_bmiResult = findViewById(R.id.tv_displayBmi);
        tv_bmiResult.setText(result);
    }
}