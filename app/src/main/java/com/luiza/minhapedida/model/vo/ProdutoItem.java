package com.luiza.minhapedida.model.vo;

public class ProdutoItem {

    private Integer id;
    private Produto produto;
    private Integer quantidade;
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
