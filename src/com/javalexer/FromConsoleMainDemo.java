package com.javalexer;

import com.javalexer.controller.ProcessController;

import java.util.Scanner;

public class FromConsoleMainDemo {

    private static Scanner sc = new Scanner(System.in);
    private static ProcessController pc = new ProcessController();

    public static void main(String[] args) throws Exception {
        while (true) {
            String input = sc.nextLine();
            if (input.equals("/exit")) {
                System.exit(0);
            }
            pc.runJavaLexer(input);
            System.out.println(input);
        }
    }

}
