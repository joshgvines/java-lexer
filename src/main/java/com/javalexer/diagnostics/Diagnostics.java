package com.javalexer.diagnostics;

import javax.tools.DiagnosticCollector;
import java.util.ArrayList;
import java.util.List;

public class Diagnostics {

    // TODO: move over to the javax Diagnostic collection API instead of String List.
    //DiagnosticCollector lexicalDiagnostics = new DiagnosticCollector();
    private static List<String> lexicalDiagnostics = new ArrayList<>();
    private static List<String> parsingDiagnostics = new ArrayList<>();

    public static void addLexicalDiagnostic(String value) {
        lexicalDiagnostics.add(value);
    }

    public static void addParsingDiagnostic(String value) {
        parsingDiagnostics.add(value);
    }

    /**
     * Temporary implementation of diagnostic output.
     */
    public static void printDiagnostics() {
        if (!lexicalDiagnostics.isEmpty()) {
            System.out.println("\nLexer_Out:");
            for (String diagnostic : lexicalDiagnostics) {
                System.out.println("syntax error: " + diagnostic);
            }
        }
        if (!parsingDiagnostics.isEmpty()) {
            System.out.println("\nParser_Out:");
            for (String diagnostic : parsingDiagnostics) {
                System.out.println("syntax error: " + diagnostic);
            }
        }
    }

    // TODO: close this util manager properly
    public static void clear() {
        lexicalDiagnostics = new ArrayList<>();
        parsingDiagnostics = new ArrayList<>();
    }

}
