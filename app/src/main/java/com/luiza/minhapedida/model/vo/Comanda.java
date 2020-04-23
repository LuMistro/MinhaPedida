package com.luiza.minhapedida.model.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

@DatabaseTable
public class Comanda {
    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    @ForeignCollectionField(eager = true)
    private Collection<ProdutoItem> produtoItemCollection;

    @DatabaseField(canBeNull = false)
    private Double totalComanda;

    public Comanda() {
    }

    public Comanda(Integer id, Collection<ProdutoItem> produtoItemCollection, Double totalComanda) {
        this.id = id;
        this.produtoItemCollection = produtoItemCollection;
        this.totalComanda = totalComanda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<ProdutoItem> getProdutoItemCollection() {
        return produtoItemCollection;
    }

    public void setProdutoItemCollection(Collection<ProdutoItem> produtoItemCollection) {
        this.produtoItemCollection = produtoItemCollection;
    }

    public Double getTotalComanda() {
        return totalComanda;
    }

    public void setTotalComanda(Double totalComanda) {
        this.totalComanda = totalComanda;
    }
}
