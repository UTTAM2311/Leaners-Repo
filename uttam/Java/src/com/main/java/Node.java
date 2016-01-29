package com.main.java;

public class Node {

    public int y;
    public int x;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getXCordinate() {
        return x;
    }

    public int getYCordinate() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
