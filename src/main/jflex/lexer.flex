package org.LengAuto.AnalizadorLexico.Modelo;

/*ToDo
   Agregar regla para texto (String)
   Corregir fin linea
 */

%%
%public
%unicode
%class Lexer
%type Token

%eofval{
    return new Token(Diccionario.EOF, null);
%eofval}


// Definición ER
    Letra = [a-zA-Z]
    Digito = [0-9]
    FinLinea = [\r|\n|\r\n]
    Vacio = [FinLinea|\t,\s]+
%%

// Definición de reglas
    //Misc
    {FinLinea}   {return new Token(Diccionario.FINLINEA, yytext());}
    {Vacio}      {return new Token(Diccionario.VACIO, yytext());}
    "/*"({Letra}|{Digito}|{Vacio})*"*/" {/**/}

    //Operadores
    "+"    {return new Token(Diccionario.SUMA, yytext());}
    "-"    {return new Token(Diccionario.RESTA, yytext());}
    "*"    {return new Token(Diccionario.MULTI, yytext());}
    "/"    {return new Token(Diccionario.DIV, yytext());}
    "=="   {return new Token(Diccionario.IGUAL, yytext());}
    "!="   {return new Token(Diccionario.NOIGUAL, yytext());}
    "<"    {return new Token(Diccionario.MENOR, yytext());}
    ">"    {return new Token(Diccionario.MAYOR, yytext());}
    "("    {return new Token(Diccionario.PINICIO, yytext());}
    ")"    {return new Token(Diccionario.PFINAL, yytext());}


    //Reservadas
    "SI"        {return new Token(Diccionario.IF, yytext());}
    "MIENTRAS"  {return new Token(Diccionario.WHILE, yytext());}
    "="         {return new Token(Diccionario.ASIGNAR, yytext());}

    //Identificador
    {Letra}({Letra}|{Digito})* {return new Token(Diccionario.ID, yytext());}

    //Numeros
    {Digito}+               {return new Token(Diccionario.INT, yytext());}
    {Digito}+"."({Digito}+) {return new Token(Diccionario.DEC, yytext());}

    //Texto

    //Error
    [^] {return new Token(Diccionario.ERROR, yytext());}