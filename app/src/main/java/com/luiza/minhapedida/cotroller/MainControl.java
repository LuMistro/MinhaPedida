package com.luiza.minhapedida.cotroller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.luiza.minhapedida.model.dao.ComandaDao;
import com.luiza.minhapedida.model.dao.ProdutoDao;
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

    //Criação dos DAOS
    private ComandaDao comandaDao;
    private ProdutoDao produtoDao;
    private ProdutoItemDao produtoItemDao;

    public MainControl(MainActivity activity) {
        this.activity = activity;
        comandaDao = new ComandaDao(this.activity);
        produtoDao = new ProdutoDao(this.activity);
        produtoItemDao = new ProdutoItemDao(this.activity);
        listProdutoItems = new ArrayList<>();
//        atualizaListView(listProdutoItems);
    }


    public void atualizaListView(List<ProdutoItem> produtoItems) {

        adapterProdutoItem = new ArrayAdapter<>(
                activity,
                android.R.layout.simple_list_item_1,
                produtoItems
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

                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Estado");
                alerta.setMessage(produtoItem.toString());
                alerta.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        produtoItem = null;
                    }
                });
                alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editar(produtoItem);
                    }
                });
                alerta.show();
            }
        });
    }

    public void confirmarExclusaoAction(final ProdutoItem produtoItem) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Excluindo Produto da comanda");
        alerta.setMessage("Deseja realmente excluir o produto " + produtoItem.getProduto().getNome() + " da comanda?");
        alerta.setIcon(android.R.drawable.ic_menu_delete);
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainControl.this.produtoItem = null;
            }
        });
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    produtoItemDao.getDao().delete(produtoItem);
                    adapterProdutoItem.remove(produtoItem);
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
                Toast.makeText(activity, "Sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Tente mais tarde", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//

//
//    public void salvarAction() {
//        if (produtoItem == null) {
//            cadastrar();
//        } else {
//            editar(get());
//        }
//
//        produtoItem = null;

       /*
       Método utilizando creatrOrUpdate()
       try {
            Dao.CreateOrUpdateStatus res = estadoDao.getDao().createOrUpdate(estado);
            if(res.isCreated()){
                //Criou
            } else if(res.isUpdated()){
                //Editado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

//        limparForm();
//    }


//
//    private void cadastrar() {
//        Estado estado = getEstadoForm();
//        try {
//            int res = estadoDao.getDao().create(estado); //Envia par o banco de dados
//            adapterEstados.add(estado); //Atualiza no ListView
//
//            if (res > 0) {
//                Toast.makeText(activity, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(activity, "Tente novamente em breve", Toast.LENGTH_SHORT).show();
//            }
//
//            //LOG
//            Log.i("Testando", "Cadastrou");
//            Toast.makeText(activity, "Id:" + estado.getId(), Toast.LENGTH_SHORT).show();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


}
