package com.javalexer;

import com.javalexer.controller.ProcessController;

import java.util.Scanner;

public class FromConsoleMainDemo {

    private static Scanner sc = new Scanner(System.in);
    private static ProcessController pc = new ProcessController();

    public static void main(String[] args) {
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            if (input.equals("/exit")) {
                System.exit(0);
            }
            runPrototypeProcess(input);
        }
    }

    private static void runPrototypeProcess(String input) {
        try {
            if (input != null && !input.isEmpty()) {
                pc.runJavaLexer(input);
                System.out.println(input);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Invalid input, please input an expression or '/exit'.\n");
        }
    }

}
