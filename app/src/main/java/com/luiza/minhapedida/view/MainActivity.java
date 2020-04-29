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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAddProduto;
    private Button btnLimparLista;
    private TextView tvTotal;
    private ListView lvProdutos;

    private List<ProdutoItem> produtoItems;
    private Serializable produtoItem;

    private MainControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializa();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recebeIntentComProdutoItem();
        control.atualizaListView(produtoItems);
    }

    private void inicializa() {
        btnAddProduto = findViewById(R.id.btnAddProduto);
        btnLimparLista = findViewById(R.id.btnLimparLista);
        tvTotal = findViewById(R.id.tvTotal);
        lvProdutos = findViewById(R.id.lvProdutos);

        produtoItems = new ArrayList<>();
        produtoItem = new ProdutoItem();

        control = new MainControl(this);

        recebeIntentComProdutoItem();
    }

    public void recebeIntentComProdutoItem() {
        ProdutoItem pItem = new ProdutoItem();
        pItem = (ProdutoItem) getIntent().getSerializableExtra("produtoItemSelecionado");
        if (pItem != null) {
            produtoItems.add((ProdutoItem) pItem);
        }
//        control.atualizaListView(produtoItems);
    }


    public void chamaTelaAddProduto(View view) {
        Intent intent = new Intent(this, addProduto_activity.class);
        startActivity(intent);
    }

    public void limparLista(View view) {
        lvProdutos.setAdapter(null);
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
