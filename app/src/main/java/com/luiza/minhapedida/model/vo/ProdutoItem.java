package com.luiza.minhapedida.model.vo;

import androidx.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable
public class ProdutoItem implements Serializable {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false)
    private Integer quantidade;

    @DatabaseField
    private Double subTotal;

    @DatabaseField(foreign = true, foreignColumnName = "id")
    private Produto produto;


    public ProdutoItem() {
    }

    public ProdutoItem(Integer id, Integer quantidade, Double subTotal, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.subTotal = subTotal;
        this.produto = produto;
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

    @NonNull
    @Override
    public String toString() {
        return produto.getNome() + " - " + quantidade + "un - R$" + getSubTotal();
    }
}
