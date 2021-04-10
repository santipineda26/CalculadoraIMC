package com.sp.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ib.custom.toast.CustomToastView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDone;
    private EditText txtName;
    private EditText txtSurname;
    private EditText txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDone = findViewById(R.id.btnDone);
        txtName = findViewById(R.id.txtName);
        txtSurname = findViewById(R.id.txtSurname);
        txtEmail = findViewById(R.id.txtEmail);

        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v.getId() ==  R.id.btnDone){
            String name = txtName.getText().toString();
            String surname = txtSurname.getText().toString();
            String email  = txtEmail.getText().toString();
            if (name.isEmpty()){
                CustomToastView.makeErrorToast(this,"Error al validar el Nombre",R.layout.custom_toast).show();
            }
            if (surname.isEmpty()){
                CustomToastView.makeInfoToast(this,"Error al validar el Apellido",R.layout.custom_toast).show();
            }
            if (!isValidEmail(email)){
                CustomToastView.makeWarningToast(this,"Error al validar el Email",R.layout.custom_toast).show();
                return;
            }
            Intent myIntent = new Intent(this,CalculadoraImc.class);
            myIntent.putExtra("namecalculator",name);
            myIntent.putExtra("surnamecalculator",surname);
            myIntent.putExtra("emailcalculator",email);
            startActivity(myIntent);
        }

    }

    private boolean isValidEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}

