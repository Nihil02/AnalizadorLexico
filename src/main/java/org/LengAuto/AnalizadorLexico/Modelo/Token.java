package org.LengAuto.AnalizadorLexico.Modelo;

public class Token {
    private Diccionario diccionario;
    private String lexema;

    public Token(Diccionario diccionario, String lexema) {
        this.diccionario = diccionario;
        this.lexema = lexema;
    }

    public Diccionario getDiccionario() {
        return diccionario;
    }

    public void setDiccionario(Diccionario diccionario) {
        this.diccionario = diccionario;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    @Override
    public String toString() {
        return diccionario + "\tLexema = " + lexema;
    }
}
