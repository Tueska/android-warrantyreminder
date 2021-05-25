package com.example.warrantyreminder;

public class WarrantyEntry {
    private int id;
    protected String product;
    protected String store;
    protected int purchaseDate;
    protected int warrantyExpireDate;

    public WarrantyEntry() {

    }

    public WarrantyEntry(String product, String store, int purchaseDate, int warrantyExpireDate) {
        this.product = product;
        this.store = store;
        this.purchaseDate = purchaseDate;
        this.warrantyExpireDate = warrantyExpireDate;
    }

    public int getId() {
        return id;
    }

    public WarrantyEntry setId(int id) {
        this.id = id;
        return this;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public int getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(int purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getWarrantyExpireDate() {
        return warrantyExpireDate;
    }

    public void setWarrantyExpireDate(int warrantyExpireDate) {
        this.warrantyExpireDate = warrantyExpireDate;
    }
}
