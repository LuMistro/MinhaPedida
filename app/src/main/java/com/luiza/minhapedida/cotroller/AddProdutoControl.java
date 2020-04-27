package com.luiza.minhapedida.cotroller;

import android.widget.ArrayAdapter;

import com.luiza.minhapedida.model.dao.ProdutoDao;
import com.luiza.minhapedida.model.vo.Produto;
import com.luiza.minhapedida.view.addProduto_activity;

import java.sql.SQLException;

public class AddProdutoControl {

    private addProduto_activity activity;
    private ProdutoDao produtoDao;
    private ArrayAdapter adapterProdutos;

    public AddProdutoControl(addProduto_activity activity) {
        this.activity = activity;
        produtoDao = new ProdutoDao(this.activity);
        inicializa();
    }

    private void inicializa() {
        configuraNumberPicker();
        configSpinner();
    }


    public void configuraNumberPicker() {
        activity.getNumberPicker().setMaxValue(100);
        activity.getNumberPicker().setMinValue(1);
        activity.getNumberPicker().setWrapSelectorWheel(true);
    }

    private void configSpinner() {
        try {
            produtoDao.getDao().createIfNotExists(new Produto(1, "Refrigerante", 3.0));
            produtoDao.getDao().createIfNotExists(new Produto(2, "Cerveja", 5.0));
            produtoDao.getDao().createIfNotExists(new Produto(3, "Batata Frita", 10.0));
            produtoDao.getDao().createIfNotExists(new Produto(4, "Água", 2.5));
            produtoDao.getDao().createIfNotExists(new Produto(5, "Petiscos", 6.0));

            adapterProdutos = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_spinner_item,
                    produtoDao.listar()
            );
            activity.getSpinner().setAdapter(adapterProdutos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public ArrayAdapter getAdapterProdutos() {
        return adapterProdutos;
    }

    public void setAdapterProdutos(ArrayAdapter adapterProdutos) {
        this.adapterProdutos = adapterProdutos;
    }
}
