package org.LengAuto.AnalizadorLexico.Modelo;

public class Token {
    private final Diccionario tipo;
    private final Object lexema;
    private final int line;
    private final int column;

    public Token(Diccionario tipo, int line, int column) {
        this(tipo, line, column, null);
    }

    public Token(Diccionario type, int line, int column, Object lexema) {
        this.tipo = type;
        this.lexema = lexema;
        this.line = line;
        this.column = column;
    }

    public Diccionario getTipo() {
        return tipo;
    }

    public Object getLexema() {
        return lexema;
    }

    @Override
    public String toString() {
        if (lexema == null){
            return tipo.toString();
        }
        //return tipo + "\tLexema: " + lexema.toString();
        return String.format("%-21s Lexema: '%s'", tipo, lexema);
    }
}
