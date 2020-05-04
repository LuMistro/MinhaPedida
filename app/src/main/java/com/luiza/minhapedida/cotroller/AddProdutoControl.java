package com.luiza.minhapedida.cotroller;

import android.widget.ArrayAdapter;

import com.luiza.minhapedida.model.dao.ProdutoDao;
import com.luiza.minhapedida.model.dao.ProdutoItemDao;
import com.luiza.minhapedida.model.vo.Produto;
import com.luiza.minhapedida.model.vo.ProdutoItem;
import com.luiza.minhapedida.view.addProduto_activity;

import java.sql.SQLException;

public class AddProdutoControl {

    private addProduto_activity activity;
    private ArrayAdapter adapterProdutos;
    private ProdutoItemDao produtoItemDao;
    private ProdutoDao produtoDao;
    private ProdutoItem produtoItem;
    private Boolean jaFoiCriado;

    public AddProdutoControl(addProduto_activity activity) {
        this.activity = activity;
        produtoItemDao = new ProdutoItemDao(this.activity);
        produtoDao = new ProdutoDao(this.activity);
        inicializa();
        pegaDadosTela();
    }

    private void inicializa() {
        configuraNumberPicker();
        attSpinner();
        produtoItem = new ProdutoItem();
    }


    public void configuraNumberPicker() {
        activity.getNumberPicker().setMaxValue(99);
        activity.getNumberPicker().setMinValue(1);
        activity.getNumberPicker().setWrapSelectorWheel(true);
    }

    public void attSpinner() {
        adapterProdutos = new ArrayAdapter<>(
                activity,
                android.R.layout.simple_spinner_item,
                produtoDao.listaSomenteOsAtivos()
        );

        //A linha abaixo serve para deixar um separação maior entre os itens do dropdown
        adapterProdutos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activity.getSpinner().setAdapter(adapterProdutos);
    }

    public void configSpinner() {
        try {
            if (jaFoiCriado == null || jaFoiCriado == false) {
                produtoDao.getDao().createIfNotExists(new Produto(1, "Refrigerante", 3.0, true));
                produtoDao.getDao().createIfNotExists(new Produto(2, "Cerveja", 5.0, true));
                produtoDao.getDao().createIfNotExists(new Produto(3, "Batata Frita", 10.0, true));
                produtoDao.getDao().createIfNotExists(new Produto(4, "Água", 2.5, true));
                produtoDao.getDao().createIfNotExists(new Produto(5, "Pastel", 3.5, true));
                produtoDao.getDao().createIfNotExists(new Produto(6, "Petiscos", 6.0, true));
                jaFoiCriado = true;
            }
            attSpinner();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void pegaDadosTela() {
        produtoItem.setProduto((Produto) activity.getSpinner().getSelectedItem());
        produtoItem.setQuantidade(activity.getNumberPicker().getValue());
    }

    public void cadastrarProdutoItem(ProdutoItem produtoItem) {
        try {
            produtoItemDao.getDao().createOrUpdate(produtoItem);
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

    public ProdutoItem getProdutoItem() {
        return produtoItem;
    }

    public void setProdutoItem(ProdutoItem produtoItem) {
        this.produtoItem = produtoItem;
    }


}
