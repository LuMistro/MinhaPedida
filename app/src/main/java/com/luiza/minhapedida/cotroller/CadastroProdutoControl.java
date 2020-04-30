package com.luiza.minhapedida.cotroller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.luiza.minhapedida.model.dao.ProdutoDao;
import com.luiza.minhapedida.model.vo.Produto;
import com.luiza.minhapedida.view.cadastrarProduto_activity;

import java.sql.SQLException;
import java.util.List;

public class CadastroProdutoControl {

    private cadastrarProduto_activity activity;
    private Produto produto;
    private ProdutoDao produtoDao;
    //Para o ListView
    private ArrayAdapter<Produto> adapterProduto;

    private List<Produto> listProduto;


    public CadastroProdutoControl(cadastrarProduto_activity activity) {
        this.activity = activity;
        produto = new Produto();
        produtoDao = new ProdutoDao(this.activity);
        atualizaListView();
    }

    public Produto pegaDadosTela() {
        produto.setNome(activity.getEtNomeProduto().getText().toString());
        produto.setPreco(new Double(activity.getEtPrecoProduto().getText().toString()));
        produto.setStatus(activity.getSwitchStatus().isChecked());
        System.out.println(produto);
        return produto;
    }


    public void atualizaListView() {
        try {
            adapterProduto = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_list_item_1,
                    produtoDao.listaSomenteOsAtivos()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        activity.getLvProdutosCadastrados().setAdapter(adapterProduto);

        addCliqueLongolvProdutoItem();
        addCliqueCurtolvProdutoItem();
    }


    private void addCliqueLongolvProdutoItem() {
        activity.getLvProdutosCadastrados().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                produto = adapterProduto.getItem(position);
                confirmarExclusaoAction(produto);
                return true;
            }
        });
    }

    public void addCliqueCurtolvProdutoItem() {
        activity.getLvProdutosCadastrados().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                produto = adapterProduto.getItem(position);

                final AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setIcon(android.R.drawable.ic_menu_edit);
                alerta.setTitle("Produto");
                alerta.setMessage(produto.toString());
                alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editar(produto);
                    }
                });
                alerta.setNeutralButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alerta.setCancelable(true);
                    }
                });
                alerta.show();
            }
        });
    }

    public void cadastrar() {
        Produto produto = pegaDadosTela();
        try {
            int res = produtoDao.getDao().create(produto); //Envia para o banco de dados
            adapterProduto.add(produto); //Atualiza no ListView

            if (res > 0) {
                Toast.makeText(activity, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Tente novamente em breve", Toast.LENGTH_SHORT).show();
            }

            //LOG
            Log.i("Testando", "Cadastrou");
            Toast.makeText(activity, "Id:" + produto.getId(), Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void confirmarExclusaoAction(final Produto produto) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Excluindo Produto");
        alerta.setMessage("Deseja realmente excluir o produto " + produto.getNome() + " do sistema?");
        alerta.setIcon(android.R.drawable.ic_menu_delete);
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CadastroProdutoControl.this.produto = null;
            }
        });
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    produtoDao.getDao().delete(produto);
                    adapterProduto.remove(produto);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                CadastroProdutoControl.this.produto = null;
            }
        });
        alerta.show();
    }


    private void editar(Produto produtoSelecionado) {
        produto = produtoSelecionado;
        try {
            adapterProduto.notifyDataSetChanged(); //Atualiza no view
            int res = produtoDao.getDao().update(produto); //Editar no banco de dados
            if (res > 0) {
                Toast.makeText(activity, "Sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Tente mais tarde", Toast.LENGTH_SHORT).show();
                System.out.println(produtoDao.getDao().queryForAll());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
