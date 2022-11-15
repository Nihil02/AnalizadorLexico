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
        assertEquals(dicval, token.getTipo());
    }

    @Test
    public void intMatch() throws IOException{
        for (int i = 0; i < 10; i++) {
            genericMatch(String.valueOf(i), Diccionario.T_INT_LITERAL);
        }
    }

    @Test
    public void decMatch() throws IOException{
        genericMatch("1.1", Diccionario.T_DECIMAL_LITERAL);
    }

    @Test
    public void boolMatch() throws IOException {
        Reader stringReader= new StringReader("true");
        Lexer lexer = new Lexer(stringReader);
        Token token = lexer.yylex();
        assertEquals(true, token.getLexema());
    }
}
