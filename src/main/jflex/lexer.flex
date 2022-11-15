package org.LengAuto.AnalizadorLexico.Modelo;

%%
%public
%unicode
%class Lexer
%line
%column
%type Token

%{
    private StringBuilder string = new StringBuilder();

    private Token symbol(Diccionario tipo) {
        return symbol(tipo, null);
    }

    private Token symbol(Diccionario tipo, Object lexema) {
        return new Token(tipo, yyline, yycolumn, lexema);
    }

    private Token integer(String text, int base) {
        if (base != 10) {
            // remover "0x", "0b", "0o"
            text = text.substring(2);
        }

        long value = Long.parseLong(text, base);
        return symbol(Diccionario.T_INT_LITERAL, value);
    }

    private Token decimal(String text) {
        double value = Double.parseDouble(text);
        return symbol(Diccionario.T_DECIMAL_LITERAL, value);
    }

    private Token booleano(boolean value) {
            return symbol(Diccionario.T_BOOL_LITERAL, value);
    }
%}

%eofval{
    return symbol(Diccionario.T_EOF);
%eofval}
    // Reglas básicas
    NuevaLinea         = [\r|\n|\r\n]
    EspacioEnBlanco    = {NuevaLinea} | [ \t\f]
    Identificador      = [:jletter:][:jletterdigit:]*
    LiteralEntero      = 0 | [1-9][0-9]*
    LiteralDecimal     = ({LiteralEntero}?"."{LiteralEntero})|({LiteralEntero}"."{LiteralEntero}?)
    LiteralExponencial = (({LiteralEntero}|{LiteralDecimal})[eE][+-]?{LiteralEntero})
    LiteralHexadecimal = "0x"[0-9a-fA-F]+
    LiteralOctal       = "0o"[0-7]+
    LiteralBinario     = "0b"[01]+

%state CADENA_DE_TEXTO
%%

<YYINITIAL> {
    /*Reservadas*/
    "SI"                           { return symbol(Diccionario.T_CONDITIONAL, yytext()); }
    "MIENTRAS"                     { return symbol(Diccionario.T_BUCLE, yytext()); }
    "true"                         { return booleano(true); }
    "false"                        { return booleano(false); }

    /*Tipo de dato*/
    "ENTERO"                       { return symbol(Diccionario.T_TYPE_INT, yytext()); }
    "DECIMAL"                      { return symbol(Diccionario.T_TYPE_DECIMAL, yytext()); }
    "BOOLEANO"                     { return symbol(Diccionario.T_TYPE_BOOLEAN, yytext()); }
    "TEXTO"                        { return symbol(Diccionario.T_TYPE_STRING, yytext()); }

    /* identificadores */
    {Identificador}                { return symbol(Diccionario.T_IDENTIFIER, yytext()); }

    /* literales */
    {LiteralEntero}                { return integer(yytext(), 10); }
    {LiteralDecimal}|
    {LiteralExponencial}           { return decimal(yytext()); }
    {LiteralHexadecimal}           { return integer(yytext(), 16); }
    {LiteralOctal}                 { return integer(yytext(), 8); }
    {LiteralBinario}               { return integer(yytext(), 2); }
    \"                             { string.setLength(0); yybegin(CADENA_DE_TEXTO); }

    /* operators */
    "="                            { return symbol(Diccionario.T_ASSIGN, yytext()); }
    "=="                           { return symbol(Diccionario.T_EQUALS, yytext()); }
    "<>"                           { return symbol(Diccionario.T_DIFFER, yytext()); }
    "+"                            { return symbol(Diccionario.T_ADD, yytext()); }
    "-"                            { return symbol(Diccionario.T_SUBTRACTION, yytext()); }
    "*"                            { return symbol(Diccionario.T_MULTIPLICATION, yytext()); }
    "/"                            { return symbol(Diccionario.T_DIVISION, yytext()); }
    "("                            { return symbol(Diccionario.T_BPARENTHESIS, yytext()); }
    ")"                            { return symbol(Diccionario.T_EPARENTHESIS, yytext()); }

    /* espacios */
    {EspacioEnBlanco}              { /* ignorar */ }
}

<CADENA_DE_TEXTO> {
    \"                             {
        yybegin(YYINITIAL);
        return symbol(Diccionario.T_ENCAPSULATED_STRING, string.toString());
    }

    [^\n\r\"\\]+                   { string.append(yytext() ); }
    \\t                            { string.append('\t'); }
    \\n                            { string.append('\n'); }
    \\r                            { string.append('\r'); }
    \\\"                           { string.append('\"'); }
    \\                             { string.append('\\'); }
}

/* error */
[^]                                { throw new Error("Illegal character <" +yytext() + ">"); }
