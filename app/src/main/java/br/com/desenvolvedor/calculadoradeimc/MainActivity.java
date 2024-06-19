package br.com.desenvolvedor.calculadoradeimc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.DecimalFormat;

import br.com.desenvolvedor.calculadoradeimc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String peso = binding.editPeso.getText().toString();
                String altura = binding.editAltura.getText().toString();

                if (peso.isEmpty()) {
                    binding.editPeso.setError("Digite um valor");
                } else if (altura.isEmpty()) {
                    binding.editAltura.setError("Digite um valor");
                    
                }else {

                    calcularIMC();

                }
            }
        });




    }
    private void calcularIMC(){

        float peso = Float.parseFloat(binding.editPeso.getText().toString().replace(",", "."));
        float altura = Float.parseFloat(binding.editAltura.getText().toString().replace(",","."));
        float imc = peso / (altura * altura);

        DecimalFormat decimalFormat = new DecimalFormat("0.0");

        if (imc < 18.5 ){
            binding.txtResultado.setText("Seu ICM é de:" + decimalFormat.format(imc) + "\n\n" + "Peso Baixo");
        } else if (imc <= 24.9) {
            binding.txtResultado.setText("Seu IMC é de:" + decimalFormat.format(imc) +"\n\n" + "Peso Normal");
        } else if (imc <= 29.9) {
            binding.txtResultado.setText("Seu IMC é de:" + decimalFormat.format(imc) + "\n\n" + "Sobrepeso");
        }else if (imc <= 34.9){
            binding.txtResultado.setText("Seu IMC é de:" + decimalFormat.format(imc) + "\n\n" + " Obesidade (Grau I)");
        } else if (imc <= 39.9) {
            binding.txtResultado.setText("Seu IMC é de:" + decimalFormat.format(imc) + "\n\n" + "Obesidade Severa (Grau II)");
        } else if (imc >= 40) {
            binding.txtResultado.setText("Seu IMC é de:" + decimalFormat.format(imc) + "\n\n" + "Obesidade Mórbida (Grau III)");

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemID = item.getItemId();

        if (itemID == R.id.ic_limpar){
            binding.editPeso.setText("");
            binding.editAltura.setText("");
            binding.txtResultado.setText("");
        }

        return super.onOptionsItemSelected(item);
    }
}