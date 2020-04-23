package com.luiza.minhapedida.model.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ProdutoItem {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    @DatabaseField(foreign = true)
    private Produto produto;

    @DatabaseField(canBeNull = false)
    private Integer quantidade;

    @DatabaseField
    private Double subTotal;

    public ProdutoItem() {
    }

    public ProdutoItem(Integer id, Produto produto, Integer quantidade, Double subTotal) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.subTotal = subTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getSubTotal() {
        return this.produto.getPreco() * this.quantidade;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
