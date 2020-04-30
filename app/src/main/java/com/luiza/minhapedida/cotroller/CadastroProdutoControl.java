package com.luiza.minhapedida.cotroller;

import android.widget.ArrayAdapter;

import com.luiza.minhapedida.model.dao.ProdutoDao;
import com.luiza.minhapedida.model.vo.Produto;
import com.luiza.minhapedida.view.cadastrarProduto_activity;

import java.util.List;

public class CadastroProdutoControl {

    private cadastrarProduto_activity activity;

    private Produto produto;

    private ProdutoDao produtoDao;

    //Para o ListView
    private ArrayAdapter<Produto> adapterProduto;

    private List<Produto> listProduto;


    public CadastroProdutoControl(cadastrarProduto_activity activity) {
        this.activity = activity;
        produto = new Produto();
        produtoDao = new ProdutoDao(this.activity);
    }

    

}
