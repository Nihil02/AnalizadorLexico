package org.LengAuto.AnalizadorLexico;


import org.LengAuto.AnalizadorLexico.Modelo.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            String archivo = System.getProperty("user.dir") + "\\archivo.txt";
            System.out.println(archivo);

            BufferedReader buffer = new BufferedReader(new FileReader(archivo));
            Lexer lexer = new Lexer(buffer);

            Token tokens;

             do{
                 tokens = lexer.yylex();
                 System.out.println(tokens.toString());
            }while(tokens.getDiccionario() != Diccionario.EOF);
        }
        catch (IOException e){System.out.println(e.toString());}
    }
}
