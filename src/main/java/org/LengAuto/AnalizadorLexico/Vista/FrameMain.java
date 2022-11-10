package org.LengAuto.AnalizadorLexico.Vista;

import org.LengAuto.AnalizadorLexico.Modelo.Diccionario;
import org.LengAuto.AnalizadorLexico.Modelo.Lexer;
import org.LengAuto.AnalizadorLexico.Modelo.Token;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FrameMain extends JFrame {
    private JPanel pnlMain;
    private JEditorPane txtEditor;
    private JEditorPane txtResultado;
    private JScrollPane pnlEditor;
    private JScrollPane pnlResultado;
    private JButton btnAnalizar;
    private JButton btnAbrirArchivo;

    public FrameMain() throws HeadlessException {
        setContentPane(pnlMain);
        setTitle("AnalizadorLexico");
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);

        btnAnalizar.addActionListener(e -> analizar());
    }

    void analizar() {
        String ruta = System.getProperty("user.dir") + "\\archivo.txt";
        File archivo = new File(ruta);
        PrintWriter printWriter;

        try {
            printWriter = new PrintWriter(archivo);
            printWriter.print(txtEditor.getText());
            printWriter.close();
        } catch (FileNotFoundException e) {System.out.println(e+"");}

        try{
            BufferedReader buffer = new BufferedReader(new FileReader(archivo));
            Lexer lexer = new Lexer(buffer);
            Token tokens;
            String aux;
            txtResultado.setText("");

            do{
                tokens = lexer.yylex();
                aux = txtResultado.getText();
                txtResultado.setText(aux + tokens.toString() + "\n");
            }while(tokens.getDiccionario() != Diccionario.EOF);
        }
        catch (IOException e){System.out.println(e+"");}
    }

}
