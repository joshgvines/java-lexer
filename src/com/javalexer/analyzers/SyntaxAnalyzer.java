package com.javalexer.analyzers;

public class SyntaxAnalyzer {

    public void parseToTree(String[] splitFileArray) {
        int level = 0;
        // Node Builder Loop
        for (String codeString : splitFileArray) {
            if (codeString.contains("{")) {
                level++;
            } else if (codeString.contains("}")) {
                level--;
            }
        }
    }

}
