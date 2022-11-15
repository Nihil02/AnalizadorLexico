package org.LengAuto.AnalizadorLexico.Modelo;

public enum Diccionario {
    T_EOF,
    T_IDENTIFIER,
    T_INT_LITERAL,
    T_DECIMAL_LITERAL,
    T_BOOL_LITERAL,
    T_ENCAPSULATED_STRING,

    //Operadores
    T_ASSIGN,
    T_EQUALS,
    T_DIFFER,
    T_ADD,
    T_SUBTRACTION,
    T_MULTIPLICATION,
    T_DIVISION,
    T_BPARENTHESIS,
    T_EPARENTHESIS,

    //Reservadas
    T_BUCLE,
    T_CONDITIONAL,
    T_TYPE_INT,
    T_TYPE_DECIMAL,
    T_TYPE_STRING,
    T_TYPE_BOOLEAN
}
