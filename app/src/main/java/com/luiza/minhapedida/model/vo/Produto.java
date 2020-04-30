package com.luiza.minhapedida.model.vo;

import androidx.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "produto")
public class Produto implements Serializable {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false, width = 110, unique = true)
    private String nome;

    @DatabaseField(canBeNull = false)
    private Double preco;

    @DatabaseField(canBeNull = false)
    private Boolean status;

    public Produto() {
    }

    public Produto(Integer id, String nome, Double preco, Boolean status) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nome + " - " + "R$ " + this.preco;
    }
}
