package com.luiza.minhapedida.utils;

public class Validadores {

    public boolean naoEhNuloOuVazio(String texto) {
        Boolean isValido = true;
        if (texto.equals("") || texto == null){
            isValido = false;
        }
        return isValido;
    }

    public boolean isEntre0e9999OuVazio(String numero) {
        Boolean isValido = true;
        if (numero == null || numero.equals("")) {
            isValido = false;
        }else{
            Double num = Double.valueOf(numero);
            if (num < 0 || num > 9999) {
                isValido = false;
            }
        }
        return isValido;
    }

}
