package com.luiza.minhapedida.cotroller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.luiza.minhapedida.R;
import com.luiza.minhapedida.model.dao.ProdutoDao;
import com.luiza.minhapedida.model.vo.Produto;
import com.luiza.minhapedida.utils.validadores.Validadores;
import com.luiza.minhapedida.view.cadastrarProduto_activity;

import java.sql.SQLException;
import java.util.List;

public class CadastroProdutoControl {

    private cadastrarProduto_activity activity;
    private Produto produto;
    private ProdutoDao produtoDao;
    //Para o ListView
    private ArrayAdapter<Produto> adapterProduto;
    private Validadores valida;
    private String erroPreço;
    private String erroNome;
    private boolean ehCadastro = true;

    private List<Produto> listProduto;


    public CadastroProdutoControl(cadastrarProduto_activity activity) {
        this.activity = activity;
        inicializa();
    }

    private void inicializa() {
        produto = new Produto();
        produtoDao = new ProdutoDao(this.activity);
        valida = new Validadores();
        erroPreço = activity.getString(R.string.numeroInvalido);
        erroNome = activity.getString(R.string.nomeInvalido);
        limpaCampos();
        atualizaListView();
    }

    public void pegaDadosTela() {
        if (inseriuDadosValidos()) {
            produto.setNome(activity.getEtNomeProduto().getText().toString());
            produto.setPreco(new Double(activity.getEtPrecoProduto().getText().toString()));
            produto.setStatus(activity.getSwitchStatus().isChecked());
        }
    }


    private void limpaCampos() {
        activity.getEtPrecoProduto().setText("");
        activity.getEtNomeProduto().setText("");
        activity.getSwitchStatus().setChecked(false);
        produto = new Produto();
    }

    private boolean inseriuDadosValidos() {
        Boolean ehValido = true;
        if (!valida.naoEhNuloOuVazio(activity.getEtNomeProduto().getText().toString())) {
            activity.getEtNomeProduto().setError(erroNome);
            ehValido = false;
        }

        if (!valida.isEntre0e9999OuVazio(activity.getEtPrecoProduto().getText().toString())) {
            activity.getEtPrecoProduto().setError(erroPreço);
            ehValido = false;
        }
        return ehValido;
    }


    public void atualizaListView() {
        try {
            adapterProduto = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_list_item_1,
                    produtoDao.listar()
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

    private void preencheComProdutoSelecionado(Produto produtoParaEditar) {
        ehCadastro = false;
        activity.getEtNomeProduto().setText(produtoParaEditar.getNome().toString());
        activity.getEtPrecoProduto().setText(produtoParaEditar.getPreco().toString());
        activity.getSwitchStatus().setChecked(produtoParaEditar.getStatus());
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
                        preencheComProdutoSelecionado(produto);
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

    public void salvarAction() {
        if (ehCadastro) {
            cadastrar();
        } else {
            editar(produto);
            ehCadastro = true;
        }

        produto = null;
        limpaCampos();
    }


    public void cadastrar() {
        produto = new Produto();
        pegaDadosTela();
        try {
            int res = produtoDao.getDao().create(produto); //Envia para o banco de dados
            adapterProduto.add(produto); //Atualiza no ListView

            if (res > 0) {
                Toast.makeText(activity, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                limpaCampos();
            } else {
                Toast.makeText(activity, "Tente novamente em breve", Toast.LENGTH_SHORT).show();
            }

            //LOG
            Log.i("Testando", "Cadastrou");
//            Toast.makeText(activity, "Id:" + produto.getId(), Toast.LENGTH_SHORT).show();
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
                    System.out.println(produtoDao.getDao().queryForAll());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                CadastroProdutoControl.this.produto = null;
            }
        });
        alerta.show();
    }


    private void editar(Produto produtoSelecionado) {
        produto.setId(produtoSelecionado.getId());
        produto.setNome(produtoSelecionado.getNome());
        produto.setPreco(produtoSelecionado.getPreco());
        produto.setStatus(produtoSelecionado.getStatus());

        Integer id = produtoSelecionado.getId();
        try {

            Produto verificaSeExiste = produtoDao.getDao().queryForId(id);
            if (verificaSeExiste != null) {
                produtoDao.getDao().deleteById(id);
                cadastrar();
                atualizaListView();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

}
