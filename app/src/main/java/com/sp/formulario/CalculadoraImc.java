package com.sp.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ib.custom.toast.CustomToastView;

public class CalculadoraImc extends AppCompatActivity implements OnClickListener {

    private TextView tvInformation;
    private TextView tvResult;
    private EditText txtHeight;
    private EditText txtWeight;
    private Button btnCalculator;
    private ImageView imgState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_imc);
        Intent intent = getIntent();
        String name = intent.getStringExtra("namecalculator");
        String surname = intent.getStringExtra("surnamecalculator");
        String email = intent.getStringExtra("emailcalculator");
        String message = "Hola " + name + " " + surname + " es un gusto tenerlo acá, su correo para el informe es: " + email;
        tvInformation = findViewById(R.id.tvInformation);
        tvResult = findViewById(R.id.tvResult);
        txtHeight = findViewById(R.id.txtHeight);
        txtWeight = findViewById(R.id.txtWeight);
        btnCalculator = findViewById(R.id.btnCalculator);
        imgState = findViewById(R.id.imgState);
        tvInformation.setText(message);

        btnCalculator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnCalculator) {
            String Weight = txtWeight.getText().toString();
            String Height = txtHeight.getText().toString();


            if (Weight.isEmpty()) {
                CustomToastView.makeErrorToast(this, "Error al validar el Peso", R.layout.custom_toast).show();
                return;
            }
            if (Height.isEmpty()) {
                CustomToastView.makeErrorToast(this, "Error al validar el Altura", R.layout.custom_toast).show();
                return;
            }
            Float imc = calculoImc(Float.parseFloat(Weight),Float.parseFloat(Height));
            String mensaje = "El IMC  es de: "+ imc;

            if(imc < 18.5){
                tvResult.setText(mensaje+"\n BAJO PESO");
                imgState.setImageResource(R.drawable.bajo);
            }
            if(imc > 18.5 && imc < 24.9){
                tvResult.setText(mensaje+"\n PESO SALUDABLE");
                imgState.setImageResource(R.drawable.normal);
            }
            if(imc > 25 && imc < 29.9){
                tvResult.setText(mensaje+"\n SOBREPESO");
                imgState.setImageResource(R.drawable.sobre);
            }
            if(imc >= 30){
                tvResult.setText(mensaje+"\n OBESIDAD");
                imgState.setImageResource(R.drawable.obeso);
            }
        }
    }


    private float calculoImc(float peso, float altura) {
        return (peso / (altura * altura));
    }
}



