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
        control.pegaDadosTela();
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(addProduto_activity.this, MainActivity.class);
                intent.putExtra("produtoItemSelecionado", control.getProdutoItem());
                startActivity(intent);

                printaOProdutoSelecionado();
            }
        });
    }

    private void inicializa() {
        spinner = findViewById(R.id.spnrProdutos);
        numberPicker = findViewById(R.id.npQuantidade);
        btnEnviar = findViewById(R.id.btnEnviar);
    }


    public void printaOProdutoSelecionado() {
        Toast.makeText(this, "Produto adicionado a comanda!" +
                "\n" + "Nome: " + control.getProdutoItem().getProduto().getNome() +
                "\n" + "Quantidade: " + control.getProdutoItem().getQuantidade() +
                "\n" + "Preço Unitário: " + control.getProdutoItem().getProduto().getPreco() +
                "\n" + "Valor Total: " + control.getProdutoItem().getSubTotal(), Toast.LENGTH_SHORT).show();
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


