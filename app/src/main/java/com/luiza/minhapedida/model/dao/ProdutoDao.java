package com.luiza.minhapedida.model.dao;

import android.content.Context;

import com.luiza.minhapedida.model.helpers.DaoHelper;
import com.luiza.minhapedida.model.vo.Produto;

import java.sql.SQLException;
import java.util.List;

public class ProdutoDao extends DaoHelper<Produto> {
    public ProdutoDao(Context c) {
        super(c, Produto.class);
    }

    public List<Produto> listar() {
        try {
            return getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
