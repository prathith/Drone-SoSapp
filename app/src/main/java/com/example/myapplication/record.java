package com.example.myapplication;

public class record {
    private String age;
    private String blood_group;
    private String blood_pressure;
    private String conditions;
    private String diabetes;
    private String height;
    private  String weight;

    public record(){
        this.age="nil";
        this.blood_group="nil";
        this.blood_pressure="nil";
        this.conditions="nil";
        this.diabetes="nil";
        this.height="nil";
        this.weight="nil";
    }
    public record(String ag,String bgg,String bp,String cond,String dia,String hei,String wei){
        this.age=ag;
        this.blood_group=bgg;
        this.blood_pressure=bp;
        this.conditions=cond;
        this.diabetes=dia;
        this.height=hei;
        this.weight=wei;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }



}

