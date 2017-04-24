package com.example.viper2.agendasqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // declaramos variables
    EditText eNombre,eTelefono,eCorreo;
   // Button bGuardar,bModificar,bBuscar,bEliminar;

    String nombre,telefono,correo;

    //declaraciones para la bd
    ContactosSQLiteHelper contactosSQLiteHelper;
    SQLiteDatabase dbContactos;
    ContentValues dataBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*
        bGuardar = (Button) findViewById(R.id.bGuardar);
        bModificar = (Button) findViewById(R.id.bModificar);
        bBuscar = (Button) findViewById(R.id.bBuscar);
        bEliminar = (Button) findViewById(R.id. bEliminar);
        */

        eNombre = (EditText) findViewById(R.id.eNombre);
        eTelefono = (EditText) findViewById(R.id.eTelefono);
        eCorreo = (EditText) findViewById(R.id.eCorreo);

    contactosSQLiteHelper = new ContactosSQLiteHelper(this,"ContactosDB",null,1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();

    }
    public void onClick(View view) {
        int id = view.getId();

        nombre = eNombre.getText().toString();
        telefono = eTelefono.getText().toString();
        correo = eCorreo.getText().toString();

        dataBD= new ContentValues();

        switch (id){
            case R.id.bGuardar:
                ///forma 1 -> SQLite
                dataBD.put("nombre",nombre);
                dataBD.put("telefono",telefono);
                dataBD.put("cooreo",correo);
                dbContactos.insert("Contactos",null,dataBD);
                //forma 2 -> SQL
                //dbContactos.execSQL("INSERT INTO Contacctos VALUES(null,'"+nombre+"','"+telefono+"','"+correo +"')");
                break;
            case R.id.bBuscar:
                Cursor cursor = dbContactos.rawQuery("SELECT * FROM Contantos WHERE nombre='"+nombre+"'",null);

                if(cursor.moveToFirst()){
                    eTelefono.setText(cursor.getString(2));
                    eCorreo.setText(cursor.getString(3));
                }
                break;
            case R.id.bModificar:
                ///forma 1 -> SQLite///forma 1 -> SQLite
                dataBD.put("telefono",telefono);
                dataBD.put("cooreo",correo);
                dbContactos.update("Contactos",dataBD,"nombre='"+nombre+"'",null);
                //forma 2 -> SQL
                //dbContactos.execSQL("UPDATE Contactos SET telefono='"+nombre+"',correo='"+correo+"' WHERE nombre='"+nombre+"'");
                break;
            case R.id.bEliminar:
                ///forma 1 -> SQLite///forma 1 -> SQLite
                dbContactos.delete("Contactos","nombre='"+nombre+"'",null);
                //forma 2 -> SQL (* es todo)
                //dbContactos.execSQL("DELETE FROM Contactos WHERE nombre='"+nombre+"'");
                break;
        }
    }
}
