package com.luiza.minhapedida.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.luiza.minhapedida.R;
import com.luiza.minhapedida.cotroller.AddProdutoControl;

public class addProduto_activity extends AppCompatActivity {

    private AddProdutoControl control;
    private Spinner spinner;
    private NumberPicker numberPicker;
    private Button btnEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);
        inicializa();
        control = new AddProdutoControl(this);
        control.configSpinner();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                control.pegaDadosTela();
                Intent intent = new Intent(addProduto_activity.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(addProduto_activity.this, "Produto adicionado com sucesso!", Toast.LENGTH_SHORT).show();

                control.cadastrarProdutoItem(control.getProdutoItem());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        control = new AddProdutoControl(this);
    }

    private void inicializa() {
        spinner = findViewById(R.id.spnrProdutos);
        numberPicker = findViewById(R.id.npQuantidade);
        btnEnviar = findViewById(R.id.btnEnviar);
    }

    public void chamaTelaCadastrarProduto(View view) {
        Intent intent = new Intent(this, cadastrarProduto_activity.class);
        startActivity(intent);
    }


    public Spinner getSpinner() {
        return spinner;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    public NumberPicker getNumberPicker() {
        return numberPicker;
    }

    public void setNumberPicker(NumberPicker numberPicker) {
        this.numberPicker = numberPicker;
    }
}


