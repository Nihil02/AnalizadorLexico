package org.LengAuto.AnalizadorLexico;

import org.LengAuto.AnalizadorLexico.Vista.FrameMain;

public class Main {
    public static void main(String[] args) {
        FrameMain frameMain = new FrameMain();

        /*try{
            String archivo = System.getProperty("user.dir") + "\\archivo.txt";
            System.out.println(archivo);

            BufferedReader buffer = new BufferedReader(new FileReader(archivo));
            Lexer lexer = new Lexer(buffer);

            Token tokens;

             do{
                 tokens = lexer.yylex();
                 System.out.println(tokens.toString());
            }while(tokens.getTipo() != Diccionario.T_EOF);
        }
        catch (IOException e){System.out.println(e.toString());};*/
    }
}
