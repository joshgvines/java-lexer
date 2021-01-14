package com.javalexer;

public class TestRead {

    public String hello;
    private String hi = "hello";

    public void hello() {
        System.out.println("Hello}");
        System.out.println('}');
    }

    private String getAValue() {
        return "Value{";
    }

    private int bloop = 4;

    private String getAValueIf() {
        if (true) {
            if (true) {
                return "Value";
            }
        }
        return "Value";
    }

}
