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
    private JButton btnBorrar;
    private JButton btnGuardarArchivo;
    private final JFileChooser jfcSeleccionar = new JFileChooser();

    public FrameMain() throws HeadlessException {
        //Inicializar frame
        setContentPane(pnlMain);
        setTitle("AnalizadorLexico");
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);

        //Focus al editor
        txtEditor.requestFocus();

        //Inicializar JFileChooser
        String ruta = System.getProperty("user.dir");
        jfcSeleccionar.setCurrentDirectory(new File(ruta));

        //Action Listeners
        btnAnalizar.addActionListener(e -> analizar());
        btnBorrar.addActionListener(e-> borrar());
        btnAbrirArchivo.addActionListener(e -> abrirArchivo());
        btnGuardarArchivo.addActionListener(e -> guardarArchivo());
    }

    private void borrar() {
        txtEditor.setText("");
        txtResultado.setText("");
    }

    private void guardarArchivo() {
        int returnVal = jfcSeleccionar.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION){
            try {
                PrintWriter printWriter = new PrintWriter(jfcSeleccionar.getSelectedFile());
                printWriter.print(txtEditor.getText());
                printWriter.close();
            } catch (FileNotFoundException e) {System.out.println(e+"");}
        }
    }

    private void analizar() {
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
        catch (IOException e){txtResultado.setText(e.toString());}
    }

    private void abrirArchivo(){
        FileNameExtensionFilter filtro =
                new FileNameExtensionFilter("Archivos de texto", "txt");
        jfcSeleccionar.setFileFilter(filtro);

        int returnVal = jfcSeleccionar.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try{
                File archivo = jfcSeleccionar.getSelectedFile();
                int i;
                StringBuilder aux = new StringBuilder();
                BufferedReader buffer = new BufferedReader(new FileReader(archivo));

                while((i = buffer.read()) != -1){aux.append((char)i);}

                txtEditor.setText(aux.toString());
            }
            catch (Exception e){txtResultado.setText(e.toString());}
        }
    }
}
