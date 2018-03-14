package mx.edu.ittepic.proyectounidad2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;
import mx.edu.ittepic.proyectounidad2.Adaptadores.AdaptadorObras;
import mx.edu.ittepic.proyectounidad2.Clases.Obra;

public class ActivityAdministrarObra extends AppCompatActivity   implements DatePickerDialog.OnDateSetListener{
    DatePickerDialog fecha_ini,fecha_fini;
    EditText aodescripcion,aomonto;
    Button aofechai,aofechafin,agregar;
    CheckBox aofinalizada;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    int idobra=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_obra);

        aodescripcion = findViewById(R.id.adecripo);
        aomonto = findViewById(R.id.montoedit);
        aofechai = findViewById(R.id.fechaini2);
        aofechafin = findViewById(R.id.fechafin2);
        agregar = findViewById(R.id.agregaro);
        aofinalizada = findViewById(R.id.checkBox);

        con = new ConexionSQLiteHelper(ActivityAdministrarObra.this);
        con.openDB();

        idobra=getIntent().getIntExtra("idobra",0);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        fecha_ini = new DatePickerDialog(
                ActivityAdministrarObra.this, ActivityAdministrarObra.this,year,month, day);

        fecha_fini = new DatePickerDialog(
                ActivityAdministrarObra.this, ActivityAdministrarObra.this,year,month, day);

        try {
            Cursor cursor = con.getAllObraID(""+idobra);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                aodescripcion.setText(cursor.getString(1));
                aomonto.setText(cursor.getString(2));
                int i=cursor.getInt(3);
                if (i==1){
                    aofinalizada.setChecked(true);
                }else
                {
                    aofinalizada.setChecked(false);
                }
            }
        } catch (SQLException e) {

        }

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String des=aodescripcion.getText().toString();
                String mon=aomonto.getText().toString();
                int valor=0;

                String fecha_iniciod=""+fecha_ini.getDatePicker().getDayOfMonth()+" "+fecha_ini.getDatePicker().getMonth()+" "+fecha_ini.getDatePicker().getYear();
                String fecha_find=""+fecha_fini.getDatePicker().getDayOfMonth()+" "+fecha_ini.getDatePicker().getMonth()+" "+fecha_ini.getDatePicker().getYear();

                if (aofinalizada.isChecked()){
                    valor=1;
                }else
                {
                    valor=0;
                }
                con.updateObra(des,mon,valor,fecha_iniciod,fecha_find,idobra);
                Toasty.success(getApplicationContext(), "Obra Actualizada", Toast.LENGTH_SHORT, true).show();
                Intent abrir= new Intent(ActivityAdministrarObra.this,MainActivity.class);

                startActivity(abrir);

            }
        });
        aofechai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                fecha_ini.show();

            }
        });
        aofechafin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fecha_fini.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
