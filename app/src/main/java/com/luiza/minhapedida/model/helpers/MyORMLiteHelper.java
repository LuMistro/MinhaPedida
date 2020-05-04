package com.luiza.minhapedida.model.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.luiza.minhapedida.model.vo.Comanda;
import com.luiza.minhapedida.model.vo.Produto;
import com.luiza.minhapedida.model.vo.ProdutoItem;

import java.sql.SQLException;

public class MyORMLiteHelper extends OrmLiteSqliteOpenHelper {
    //Configuração do banco de dados
    private static final String DATABASE_NAME = "comandasMinhaPedida.db";
    private static final int DATABASE_VERSION = 7;

    public MyORMLiteHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Comanda.class);
            TableUtils.createTableIfNotExists(connectionSource, Produto.class);
            TableUtils.createTableIfNotExists(connectionSource, ProdutoItem.class);

            preencheBanco();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Comanda.class, true);
            TableUtils.dropTable(connectionSource, Produto.class, true);
            TableUtils.dropTable(connectionSource, ProdutoItem.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void preencheBanco() {
        try {

            Produto p1 = new Produto();
            p1.setStatus(true);
            p1.setNome("Refrigerante");
            p1.setPreco(3.0);
            p1.setId(1);

            Produto p2 = new Produto();
            p2.setStatus(true);
            p2.setNome("Cerveja");
            p2.setPreco(5.0);
            p2.setId(2);

            Produto p3 = new Produto();
            p3.setStatus(true);
            p3.setNome("Batata Frita");
            p3.setPreco(10.0);
            p3.setId(3);

            Produto p4 = new Produto();
            p4.setStatus(true);
            p4.setNome("Água");
            p4.setPreco(2.5);
            p4.setId(4);

            Produto p5 = new Produto();
            p5.setStatus(true);
            p5.setNome("Pastel");
            p5.setPreco(3.5);
            p5.setId(5);

            Produto p6 = new Produto();
            p6.setStatus(true);
            p6.setNome("Petiscos");
            p6.setPreco(6.0);
            p6.setId(6);


            getDao(Produto.class).create(p1);
            getDao(Produto.class).create(p2);
            getDao(Produto.class).create(p3);
            getDao(Produto.class).create(p4);
            getDao(Produto.class).create(p5);
            getDao(Produto.class).create(p6);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}