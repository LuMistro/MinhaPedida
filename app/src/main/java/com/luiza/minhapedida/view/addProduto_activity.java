package com.luiza.minhapedida.view;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.luiza.minhapedida.R;
import com.luiza.minhapedida.cotroller.AddProdutoControl;

public class addProduto_activity extends AppCompatActivity {

    private AddProdutoControl control;
    private Spinner spinner;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);
        inicializa();
        control = new AddProdutoControl(this);
    }

    private void inicializa() {
        spinner = findViewById(R.id.spnrProdutos);
        numberPicker = findViewById(R.id.npQuantidade);
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


