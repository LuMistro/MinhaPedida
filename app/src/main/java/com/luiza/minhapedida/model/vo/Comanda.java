package com.luiza.minhapedida.model.vo;

import java.util.List;

public class Comanda {

    private Integer id;
    private List<ProdutoItem> produtoItems;

    public Comanda() {
    }

    public Comanda(Integer id, List<ProdutoItem> produtoItems) {
        this.id = id;
        this.produtoItems = produtoItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProdutoItem> getProdutoItems() {
        return produtoItems;
    }

    public void setProdutoItems(List<ProdutoItem> produtoItems) {
        this.produtoItems = produtoItems;
    }
}
