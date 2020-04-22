package com.luiza.minhapedida.model.dao;

import android.content.Context;

import com.luiza.minhapedida.model.helpers.DaoHelper;
import com.luiza.minhapedida.model.vo.Produto;

public class ProdutoDao extends DaoHelper<Produto> {
    public ProdutoDao(Context c) {
        super(c, Produto.class);
    }
}
