package org.LengAuto.AnalizadorLexico.Vista;

import org.LengAuto.AnalizadorLexico.Modelo.Diccionario;
import org.LengAuto.AnalizadorLexico.Modelo.Lexer;
import org.LengAuto.AnalizadorLexico.Modelo.Token;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class FrameMain extends JFrame {
    private JPanel pnlMain;
    private JEditorPane txtEditor;
    private JEditorPane txtResultado;
    private JScrollPane pnlEditor;
    private JScrollPane pnlResultado;
    private JButton btnAnalizar;
    private JButton btnAbrirArchivo;
    private final String ruta = System.getProperty("user.dir") + "\\archivo.txt";

    public FrameMain() throws HeadlessException {
        setContentPane(pnlMain);
        setTitle("AnalizadorLexico");
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);

        btnAnalizar.addActionListener(e -> analizar());
        btnAbrirArchivo.addActionListener(e -> abrirArchivo());
    }

    void analizar() {
        try{
            String textoEditor = txtEditor.getText();
            Lexer lexer = new Lexer(new StringReader(textoEditor));
            Token tokens;
            StringBuilder aux = new StringBuilder();

            do{
                tokens = lexer.yylex();
                aux.append(tokens.toString()).append("\n");
            }while(tokens.getTipo() != Diccionario.T_EOF);

            txtResultado.setText(aux.toString());
        }
        catch (IOException e){System.out.println(e+"");}
    }

    void abrirArchivo(){
        JFileChooser jfcSeleccionar = new JFileChooser();
        FileNameExtensionFilter filtro =
                new FileNameExtensionFilter("Archivos de texto", "txt");
        jfcSeleccionar.setFileFilter(filtro);
        jfcSeleccionar.setCurrentDirectory(new File(ruta));

        int returnVal = jfcSeleccionar.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try{
                File archivo = jfcSeleccionar.getSelectedFile();
                int i;
                StringBuilder aux = new StringBuilder();
                BufferedReader buffer = new BufferedReader(new FileReader(archivo));

                while((i = buffer.read()) != -1){
                    aux.append((char)i);
                }

                txtEditor.setText(aux.toString());
            }
            catch (Exception e){System.out.println(e+"");}
        }
    }
}
