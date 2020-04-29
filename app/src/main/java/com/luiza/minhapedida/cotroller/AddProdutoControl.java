package com.luiza.minhapedida.cotroller;

import android.widget.ArrayAdapter;

import com.luiza.minhapedida.model.dao.ProdutoDao;
import com.luiza.minhapedida.model.vo.Produto;
import com.luiza.minhapedida.model.vo.ProdutoItem;
import com.luiza.minhapedida.view.addProduto_activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddProdutoControl {

    private addProduto_activity activity;
    private ArrayAdapter adapterProdutos;
    private List<ProdutoItem> produtoItems;
    private ProdutoDao produtoDao;
    private ProdutoItem produtoItem;

    public AddProdutoControl(addProduto_activity activity) {
        this.activity = activity;
        produtoDao = new ProdutoDao(this.activity);
        inicializa();
        pegaDadosTela();
    }

    private void inicializa() {
        configuraNumberPicker();
        configSpinner();
        produtoItem = new ProdutoItem();
        produtoItems = new ArrayList<>();
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
            produtoDao.getDao().createIfNotExists(new Produto(5, "Pastel", 3.5));
            produtoDao.getDao().createIfNotExists(new Produto(6, "Petiscos", 6.0));

            adapterProdutos = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_spinner_item,
                    produtoDao.listar()
            );
            //A linha abaixo serve para deixar um separação maior entre os itens do dropdown
            adapterProdutos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            activity.getSpinner().setAdapter(adapterProdutos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void pegaDadosTela() {
        produtoItem.setProduto((Produto) this.activity.getSpinner().getSelectedItem());
        produtoItem.setQuantidade(this.activity.getNumberPicker().getValue());
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

    public List<ProdutoItem> getProdutoItems() {
        return produtoItems;
    }

    public void setProdutoItems(List<ProdutoItem> produtoItems) {
        this.produtoItems = produtoItems;
    }

    public ProdutoItem getProdutoItem() {
        return produtoItem;
    }

    public void setProdutoItem(ProdutoItem produtoItem) {
        this.produtoItem = produtoItem;
    }
}
