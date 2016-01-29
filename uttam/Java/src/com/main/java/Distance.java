package com.main.java;

import java.util.function.BiFunction;

public class Distance {

    private Node a;
    private Node b;

    public Distance(Node a, Node b) {
        this.a = a;
        this.b = b;
    }

    public double getDistance() {
        BiFunction<Node, Node, Double> distance = (a, b) -> {
            return Math.sqrt(Math.pow(a.getXCordinate() - b.getXCordinate(), 2)
                    + Math.pow(a.getYCordinate() - b.getYCordinate(), 2));
        };
        return distance.apply(a, b);
    }

    public Node firstNode() {
        return a;
    }

    public Node secondNode() {
        return b;
    }
    
    @Override
    public String toString() {
        return "{" + a.toString() + "," + b.toString() + "}";
    }
}
