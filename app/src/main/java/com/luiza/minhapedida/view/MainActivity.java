package com.luiza.minhapedida.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.luiza.minhapedida.R;
import com.luiza.minhapedida.cotroller.MainControl;
import com.luiza.minhapedida.model.vo.ProdutoItem;

public class MainActivity extends AppCompatActivity {

    private Button btnAddProduto;
    private Button btnLimparLista;
    private TextView tvTotal;
    private ListView lvProdutos;

    private ProdutoItem produtoItem;

    private MainControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializa();
        control = new MainControl(this);
    }

    private void inicializa() {
        btnAddProduto = findViewById(R.id.btnAddProduto);
        btnLimparLista = findViewById(R.id.btnLimparLista);
        tvTotal = findViewById(R.id.tvTotal);
        lvProdutos = findViewById(R.id.lvProdutos);

        produtoItem = new ProdutoItem();
    }

    public void chamaTelaAddProduto(View view) {
        Intent intent = new Intent(this, addProduto_activity.class);
        startActivity(intent);
    }

    public void limparLista(View view) {
        control.limparLista();
        Toast.makeText(this, "Você limpou a lista de Itens", Toast.LENGTH_SHORT).show();
    }

    public Button getBtnAddProduto() {
        return btnAddProduto;
    }

    public void setBtnAddProduto(Button btnAddProduto) {
        this.btnAddProduto = btnAddProduto;
    }

    public Button getBtnLimparLista() {
        return btnLimparLista;
    }

    public void setBtnLimparLista(Button btnLimparLista) {
        this.btnLimparLista = btnLimparLista;
    }

    public TextView getTvTotal() {
        return tvTotal;
    }

    public void setTvTotal(TextView tvTotal) {
        this.tvTotal = tvTotal;
    }

    public ListView getLvProdutos() {
        return lvProdutos;
    }

    public void setLvProdutos(ListView lvProdutos) {
        this.lvProdutos = lvProdutos;
    }

}
