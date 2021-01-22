package com.javalexer;

import com.javalexer.controller.ProcessController;
import com.javalexer.parsing.BinaryNode;
import com.javalexer.parsing.ExperimentalParser;
import com.javalexer.parsing.ExperimentalTree;
import com.javalexer.parsing.ExpressionTree;

import java.util.Scanner;

public class FromConsoleMainDemo {

    private static Scanner sc = new Scanner(System.in);
    private static ProcessController pc = new ProcessController();

    public static void main(String[] args) throws Exception {
//        while (true) {
//            System.out.print("> ");
//            String input = sc.nextLine();
//            if (input.equals("/exit")) {
//                System.exit(0);
//            }
//            pc.runJavaLexer(input);
//            System.out.println(input);
//        }

        //pc.runJavaLexer("1+2-3*4-6/3");

        ExperimentalTree et = new ExperimentalTree();
        //char[] charArray = postfix.toCharArray();
        BinaryNode root = et.build("(1 + 2 - 3 * 4 * 5)");
        //et.inorder(root);
        System.out.println(et.evaluate(root));
    }

}
