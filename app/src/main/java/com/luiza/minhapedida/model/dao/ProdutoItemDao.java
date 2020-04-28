package com.luiza.minhapedida.model.dao;

import android.content.Context;

import com.luiza.minhapedida.model.helpers.DaoHelper;
import com.luiza.minhapedida.model.vo.ProdutoItem;

import java.sql.SQLException;

public class ProdutoItemDao extends DaoHelper<ProdutoItem> {
    public ProdutoItemDao(Context c) {
        super(c, ProdutoItem.class);
    }


    public ProdutoItem buscaPorId(Integer id) {
        try {
            return getDao().queryBuilder().where().eq("id", id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}