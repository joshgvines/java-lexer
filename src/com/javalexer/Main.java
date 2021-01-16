package com.javalexer;

import com.javalexer.controller.ProcessController;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        ProcessController lc = new ProcessController();
        lc.runJavaLexer();
    }

}
