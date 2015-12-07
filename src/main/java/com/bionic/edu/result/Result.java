package com.bionic.edu.result;


public class Result {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    private String name;
    private double sum;
    public Result(){   }
    public Result(String name, double sum){
        this.name = name;
        this.sum = sum;
    }

}
