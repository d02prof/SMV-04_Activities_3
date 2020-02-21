package com.smv.activities_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity
{
    TextView textViewNo1, textViewNo2;
    Button buttonAdd, buttonSubtract;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        textViewNo1 = findViewById(R.id.TextViewNo1);
        textViewNo2 = findViewById(R.id.TextViewNo2);
        Intent intent = getIntent();
        textViewNo1.setText(intent.getStringExtra("EXTRA_NO1"));
        textViewNo2.setText(intent.getStringExtra("EXTRA_NO2"));

        buttonAdd = findViewById(R.id.ButtonAdd);
        buttonSubtract = findViewById(R.id.ButtonSubtract);
        buttonAdd.setOnClickListener(BothButtons_Click);
        buttonSubtract.setOnClickListener(BothButtons_Click);

        setTitle("Second Activity");
    }

    View.OnClickListener BothButtons_Click = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int no1 = Integer.parseInt(textViewNo1.getText().toString());
            int no2 = Integer.parseInt(textViewNo2.getText().toString());
            int result = 0;

            //preverimo, kateri gumb je bil kliknjen (v C# bi uporabili sender)
            if(v == findViewById(R.id.ButtonAdd)) result = no1 + no2;
            else if(v == findViewById(R.id.ButtonSubtract)) result = no1 - no2;

            Intent resultIntent = new Intent();
            resultIntent.putExtra("result", result);
            setResult(RESULT_OK, resultIntent);

            //zapremo (uničimo) aktivnost
            finish();
        }
    };

}
