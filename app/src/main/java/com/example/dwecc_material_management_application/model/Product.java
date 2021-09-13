package com.example.dwecc_material_management_application.model;

import java.time.LocalDate;

public class Product {
//    source: https://ygosu.com/community/?bid=computer&idx=32906
//    String에 null 값이 default일 경우, E/memtrack: Couldn't load memtrack module 에러 발생
    private String product="";
    private String model="";
    private String spec="";

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
