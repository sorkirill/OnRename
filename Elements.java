package com.example.onrename;


import java.io.Serializable;

public class Elements implements Serializable {
    private String name;
    private String category;
    private String price;

    public Elements(String tovar, String category, String price){
        this.name=tovar;
        this.category = category;
        this.price = price;
    }


    public String getTovar(){
        return this.name;
    }

    public String getCategory(){
        return this.category;
    }

    public String getPrice(){ return this.price; }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;

    }
}
