package com.luiza.minhapedida.model.dao;

import android.content.Context;

import com.luiza.minhapedida.model.helpers.DaoHelper;
import com.luiza.minhapedida.model.vo.Comanda;

import java.sql.SQLException;
import java.util.List;

public class ComandaDao extends DaoHelper<Comanda> {
    public ComandaDao(Context c) {
        super(c, Comanda.class);
    }

    public List<Comanda> listar() {
        try {
            return getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Comanda> listaPorNome(String nome) {
        try {
            return getDao().queryBuilder().where().eq("nome", nome).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
