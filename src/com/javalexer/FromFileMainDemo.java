package com.javalexer;

import com.javalexer.controller.ProcessController;

import java.io.IOException;

public class FromFileMainDemo {

    public static void main(String[] args) throws Exception {
        ProcessController lc = new ProcessController();
        lc.runJavaFromFileLexer("src/com/javalexer/TestRead.java");
    }

}
