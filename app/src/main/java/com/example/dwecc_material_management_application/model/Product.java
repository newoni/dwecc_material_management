package com.example.dwecc_material_management_application.model;

import java.time.LocalDate;

public class Product {
    private String searchString; //--check. BomSearchRequest class 도입으로 인해 필요없을 것으로 보임. <21.09.07>
    private int id;
    private int materialInfo;
    private String lot;
    private int seq;
    private LocalDate expDate;
    private int quantity;

//####s
    //--check. BomSearchRequest class 도입으로 인해 필요없을 것으로 보임. <21.09.07>
    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
//####e

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaterialInfo() {
        return materialInfo;
    }

    public void setMaterialInfo(int materialInfo) {
        this.materialInfo = materialInfo;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "searchString='" + searchString + '\'' +
                ", id=" + id +
                ", materialInfo=" + materialInfo +
                ", lot='" + lot + '\'' +
                ", seq=" + seq +
                ", expDate=" + expDate +
                ", quantity=" + quantity +
                '}';
    }
}