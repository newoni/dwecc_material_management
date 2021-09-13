package com.example.dwecc_material_management_application.model;

public class BomSearchRequest {
    private String product;
    private String model;
    private String spec;

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

    public void setSpec(String sepc) {
        this.spec = sepc;
    }
}
