package com.luiza.minhapedida.cotroller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.luiza.minhapedida.R;
import com.luiza.minhapedida.model.dao.ProdutoItemDao;
import com.luiza.minhapedida.model.vo.ProdutoItem;
import com.luiza.minhapedida.view.MainActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainControl {
    private MainActivity activity;

    //Para o ListView
    private ArrayAdapter<ProdutoItem> adapterProdutoItem;

    private List<ProdutoItem> listProdutoItems;

    private ProdutoItem produtoItem;

    private ProdutoItemDao produtoItemDao;

    public MainControl(MainActivity activity) {
        this.activity = activity;
        produtoItemDao = new ProdutoItemDao(this.activity);
        atualizaListView();
    }


    public void atualizaListView() {
        try {
            listProdutoItems = produtoItemDao.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        atualizaTotalComanda();

        adapterProdutoItem = new ArrayAdapter<>(
                activity,
                android.R.layout.simple_list_item_1,
                listProdutoItems
        );
        activity.getLvProdutos().setAdapter(adapterProdutoItem);

        addCliqueLongolvProdutoItem();
        addCliqueCurtolvProdutoItem();
    }


    private void addCliqueLongolvProdutoItem() {
        activity.getLvProdutos().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                produtoItem = adapterProdutoItem.getItem(position);
                confirmarExclusaoAction(produtoItem);
                return true;
            }
        });
    }

    public void addCliqueCurtolvProdutoItem() {
        activity.getLvProdutos().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                produtoItem = adapterProdutoItem.getItem(position);

                final AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setIcon(android.R.drawable.ic_menu_edit);
                alerta.setTitle(activity.getString(R.string.produto_text));
                alerta.setMessage(produtoItem.toString());
                alerta.setNegativeButton("-1un", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        produtoItem.setQuantidade(produtoItem.getQuantidade() - 1);
                        editar(produtoItem);
                        atualizaTotalComanda();
                        if (produtoItem.getQuantidade() < 1) {
                            try {
                                produtoItemDao.getDao().delete(produtoItem);
                                adapterProdutoItem.remove(produtoItem);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });

                alerta.setPositiveButton("+1un", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        produtoItem.setQuantidade(produtoItem.getQuantidade() + 1);
                        editar(produtoItem);
                        atualizaListView();
                    }
                });
                alerta.setNeutralButton(activity.getString(R.string.fechar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alerta.setCancelable(true);
                    }
                });
                alerta.show();
            }
        });
    }

    public void confirmarExclusaoAction(final ProdutoItem produtoItem) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle(activity.getString(R.string.excluindo));
        alerta.setMessage(activity.getString(R.string.confirmaExclusao) + produtoItem.getProduto().getNome() + "?");
        alerta.setIcon(android.R.drawable.ic_menu_delete);
        alerta.setNegativeButton(activity.getString(R.string.nao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainControl.this.produtoItem = null;
            }
        });
        alerta.setPositiveButton(activity.getString(R.string.sim), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    produtoItemDao.getDao().delete(produtoItem);
                    adapterProdutoItem.remove(produtoItem);
                    atualizaTotalComanda();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                MainControl.this.produtoItem = null;
            }
        });
        alerta.show();
    }


    private void editar(ProdutoItem produtoSelecionado) {
        produtoItem.setProduto(produtoSelecionado.getProduto());
        produtoItem.setQuantidade(produtoSelecionado.getQuantidade());
        produtoItem.setSubTotal(produtoSelecionado.getSubTotal());
        try {
            adapterProdutoItem.notifyDataSetChanged(); //Atualiza no view
            int res = produtoItemDao.getDao().update(produtoItem); //Editar no banco de dados
            if (res > 0) {
                Toast.makeText(activity, activity.getString(R.string.sucesso), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, activity.getString(R.string.tenteDeNovo), Toast.LENGTH_SHORT).show();
                System.out.println(produtoItemDao.getDao().queryForAll());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limparLista() {
        adapterProdutoItem.clear();
        produtoItemDao.deleteAll();
        listProdutoItems = new ArrayList<>();
        atualizaTotalComanda();
    }

    public void atualizaTotalComanda() {
        String totalString = activity.getString(R.string.total_text);
        String moedaString = activity.getString(R.string.moeda_text);
        Double totalComanda = 0.0;


        for (int i = 0; i < listProdutoItems.size(); i++) {
            totalComanda += listProdutoItems.get(i).getSubTotal();
        }
        activity.getTvTotal().setText(totalString + " " + moedaString + totalComanda);
    }

    public List<ProdutoItem> getListProdutoItems() {
        return listProdutoItems;
    }

    public void setListProdutoItems(List<ProdutoItem> listProdutoItems) {
        this.listProdutoItems = listProdutoItems;
    }

    public ProdutoItem getProdutoItem() {
        return produtoItem;
    }

    public void setProdutoItem(ProdutoItem produtoItem) {
        this.produtoItem = produtoItem;
    }
}
