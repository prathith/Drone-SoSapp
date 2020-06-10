package com.example.myapplication;

public class contact {
    public String one;
    private String two;
    private String three;

    public contact() {
        this.one = "NIL";
        this.two = "NIL";
        this.three = "NIL";
    }

    public contact(String one, String two, String three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }
}
