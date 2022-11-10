package org.LengAuto.AnalizadorLexico;

import org.LengAuto.AnalizadorLexico.Modelo.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {
    public void genericMatch(String strTest, Diccionario dicval) throws IOException {
        Reader stringReader= new StringReader(strTest);
        Lexer lexer = new Lexer(stringReader);
        Token token = lexer.yylex();
        assertEquals(dicval, token.getDiccionario());
    }

    @Test
    public void errorMatch() throws IOException{
        genericMatch("@", Diccionario.ERROR);
    }

    @Test
    public void intMatch() throws IOException{
        genericMatch("21", Diccionario.INT);
    }

    @Test
    public void decMatch() throws IOException{
        genericMatch("1.1", Diccionario.DEC);
    }

    @Test
    public void comMatch() throws IOException{
        genericMatch("", null);
    }

    @Test
    public void eolMatch() throws IOException{
        genericMatch("\n", Diccionario.FINLINEA);
    }
}
