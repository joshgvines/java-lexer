package main.com.javalexer;

import main.com.javalexer.controller.ProcessController;

import java.util.Scanner;

public class FromConsoleMainDemo {

    private static Scanner sc = new Scanner(System.in);
    private static ProcessController pc = new ProcessController();

    public static void main(String[] args) {
        boolean readTokens = false;
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            if (input.equals("/exit")) {
                System.exit(0);
            } else if (input.equals("/readtokens")) {
                readTokens = !readTokens;
            } else {
                runPrototypeProcess(input, readTokens);
            }
        }
    }

    private static void runPrototypeProcess(String input, boolean readTokens) {
        try {
            if (input != null && !input.isEmpty()) {
                pc.runJavaLexer(input, readTokens);
                System.out.println(input);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Invalid input, please input an expression or '/exit'.\n");
        }
    }

}
