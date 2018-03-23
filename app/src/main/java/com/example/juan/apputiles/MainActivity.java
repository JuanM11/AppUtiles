package com.example.juan.apputiles;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private EditText palabra;
    private ImageView imagen;
    private Button comprobar;
    private String[] nombr={"eraser", "pencil", "book", "backpack", "sharpener", "clippers", "pen", "rule"};
    private int generado=0;
    private int intentos=0;
    TextView co;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        palabra =(EditText)findViewById(R.id.texto);
        imagen =(ImageView)findViewById(R.id.imagen);
        comprobar=(Button)findViewById(R.id.comprobar);
        co=(TextView)findViewById(R.id.correctas);


        generado=generar();
        int resId=getResources().getIdentifier(nombr[generado],"drawable",getPackageName());
        imagen.setImageResource(resId);
        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String p = palabra.getText().toString().toLowerCase();
               if (p.equals(nombr[generado]))
                {
                    Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_SHORT).show();
                 establecerimagen(generado);
                 esperar();
                 palabra.setText("");
                 intentos++;
                 co.setText("Lleva "+intentos+""+" correctas");

                }
                else
               {
                   Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                   establecerimagen(generado);
                   esperar();
                   palabra.setText("");
               }
               if(intentos==5)
               {
                   String resu2 = String.valueOf(getResources().getString(R.string.felicitacion));
                   Toast toas2 = Toast.makeText(getApplicationContext(), resu2, Toast.LENGTH_LONG);
                   toas2.show();
                   spl(view);
               }
            }
        });

    }

    private void establecerimagen(int numero)
    {
        int resiId =getResources().getIdentifier(nombr[numero],"drawable",getPackageName());
        imagen.setImageResource(resiId);
    }

    public void acerca (View view) {
        Intent i = new Intent(this, AcercaDe.class);
        startActivity(i);
    }
    public void spl (View view) {
        Intent i = new Intent(this, SplashScreen.class);
        startActivity(i);
    }
    private int generar(){
        return (int)(Math.random()*nombr.length);
    }

    public void esperar()
    {
        generado=generar();
        establecerimagen(generado);
    }
}
