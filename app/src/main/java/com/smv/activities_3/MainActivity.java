package com.smv.activities_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText editTextNo1, editTextNo2;
    Button buttonCalculateInActivity2;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNo1 = findViewById(R.id.EditTextNo1);
        editTextNo2 = findViewById(R.id.EditTextNo2);
        buttonCalculateInActivity2 = findViewById(R.id.ButtonCalculateInActivity2);
        buttonCalculateInActivity2.setOnClickListener(buttonCalculateInActivity2_OnClickLIstener);
        textViewResult = findViewById(R.id.TextViewResult);

        setTitle("Main Activity");
    }

    View.OnClickListener buttonCalculateInActivity2_OnClickLIstener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            String no1 = editTextNo1.getText().toString();
            String no2 = editTextNo2.getText().toString();

            if(no1.equals("") || no2.equals(""))
            {
                //lahko Toast
                Toast.makeText(MainActivity.this, "Edit both numbers!", Toast.LENGTH_SHORT).show();

                //lahko pa bi tudi AlertDialog
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setMessage("Enter both numbers!").setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.setTitle("Error");
                alert.show();
            }
            else
            {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("EXTRA_NO1", no1);
                intent.putExtra("EXTRA_NO2", no2);
                startActivityForResult(intent, 1);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //pogledamo requestCode, da vemo iz katere aktivnosti je rezultat (če jih je več)
        if(requestCode == 1)
        {
            //če je uporabnik uporabil katerega od gumbov in imamo rezultat
            if(resultCode == RESULT_OK)
            {
                int result = data.getIntExtra("result", 0);
                textViewResult.setText("" + result);
            }
            //če smo se vrnili brez rezultata (HW gumb, lahko tudi SW gumb za nazaj)
            //da v Activity2 dobimo puščico za nazaj, je treba v manifest dodati android:parentActivityName
            //če ga nastavimo na MainActivity, s puščico nazaj ne dobimo rezultata aktivnosti, prikaže se prazen Activity
            //če ga nastavimo na samo sebe (.Activity2) pa je obnašanje enako kot pri HW gumbu za nazaj: reultat je RESULT_CANCELED, vpisani podatki se ohranijo
            else if(resultCode == RESULT_CANCELED) textViewResult.setText("Nothing to display");
        }
    }
}
