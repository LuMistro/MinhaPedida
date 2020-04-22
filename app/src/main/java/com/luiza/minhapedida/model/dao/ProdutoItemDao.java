package com.luiza.minhapedida.model.dao;

import android.content.Context;

import com.luiza.minhapedida.model.helpers.DaoHelper;
import com.luiza.minhapedida.model.vo.ProdutoItem;

public class ProdutoItemDao extends DaoHelper<ProdutoItem> {
    public ProdutoItemDao(Context c) {
        super(c, ProdutoItem.class);
    }

}