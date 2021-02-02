package com.javalexer.diagnostics;

import java.util.ArrayList;
import java.util.List;

public class Diagnostics {

    private static List<String> lexicalDiagnostics = new ArrayList<>();
    private static List<String> parsingDiagnostics = new ArrayList<>();

    public static void addLexicalDiagnostic(String value) {
        lexicalDiagnostics.add(value);
    }

    public static void addParsingDiagnostic(String value) {
        lexicalDiagnostics.add(value);
    }

    public static void printDiagnostics() {
        System.out.println("Lexer:");
        for (String diagnostic : lexicalDiagnostics) {
            System.out.println(diagnostic);
        }
        System.out.println("Parser:");
        for (String diagnostic : parsingDiagnostics) {
            System.out.println(diagnostic);
        }
    }

}
