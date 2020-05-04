package com.luiza.minhapedida.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.luiza.minhapedida.R;
import com.luiza.minhapedida.cotroller.CadastroProdutoControl;

public class cadastrarProduto_activity extends AppCompatActivity {

    private EditText etNomeProduto;
    private EditText etPrecoProduto;
    private Button btnSalvar;
    private ListView lvProdutosCadastrados;
    private Switch switchStatus;
    private CadastroProdutoControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);
        inicializa();
        control = new CadastroProdutoControl(this);
    }

    private void inicializa() {
        etNomeProduto = findViewById(R.id.etNomeProduto);
        etPrecoProduto = findViewById(R.id.etPrecoProduto);
        btnSalvar = findViewById(R.id.btnSalvar);
        lvProdutosCadastrados = findViewById(R.id.lvProdutosCadastrados);
        switchStatus = findViewById(R.id.switchStatus);
    }

    public void salvarProdutoAction(View view) {
        control.salvarAction();
    }

    public EditText getEtNomeProduto() {
        return etNomeProduto;
    }

    public void setEtNomeProduto(EditText etNomeProduto) {
        this.etNomeProduto = etNomeProduto;
    }

    public EditText getEtPrecoProduto() {
        return etPrecoProduto;
    }

    public void setEtPrecoProduto(EditText etPrecoProduto) {
        this.etPrecoProduto = etPrecoProduto;
    }

    public Button getBtnSalvar() {
        return btnSalvar;
    }

    public void setBtnSalvar(Button btnSalvar) {
        this.btnSalvar = btnSalvar;
    }

    public ListView getLvProdutosCadastrados() {
        return lvProdutosCadastrados;
    }

    public void setLvProdutosCadastrados(ListView lvProdutosCadastrados) {
        this.lvProdutosCadastrados = lvProdutosCadastrados;
    }

    public Switch getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(Switch switchStatus) {
        this.switchStatus = switchStatus;
    }
}
