package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ConfirmActivity extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_activity);
        editText=(EditText) findViewById(R.id.nacimiento);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ConfirmActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            ((TextView) findViewById(R.id.Nombre)).setText(extras.getString("Nombre"));
            ((TextView) findViewById(R.id.nacimiento)).setText(extras.getString("Nacimiento"));
            ((TextView) findViewById(R.id.email)).setText(extras.getString("Email"));
            ((TextView) findViewById(R.id.telefono)).setText(extras.getString("Telefono"));
            ((TextView) findViewById(R.id.descripcioncontacto)).setText(extras.getString("Descripcioncontacto"));
        }
        final Button editEditar = (Button) findViewById(R.id.edit);
        editEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmActivity.this,MainActivity.class );
                intent.putExtra("Nombre",((TextView)findViewById(R.id.Nombre)).getText().toString());
                intent.putExtra("Nacimiento",((TextView)findViewById(R.id.nacimiento)).getText().toString());
                intent.putExtra("Email",((TextView)findViewById(R.id.email)).getText().toString());
                intent.putExtra("Telefono",((TextView)findViewById(R.id.telefono)).getText().toString());
                intent.putExtra("Descripcioncontacto",((TextView)findViewById(R.id.descripcioncontacto)).getText().toString());
                startActivity(intent);
            }
        });

    }
    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }
}
