package com.example.warrantyreminder;

import androidx.annotation.NonNull;

public class WarrantyEntry {
    private int id;
    protected String product;
    protected String store;
    protected long purchaseDate;
    protected long warrantyExpireDate;

    public WarrantyEntry() {

    }

    public WarrantyEntry(String product, String store, long purchaseDate, long warrantyExpireDate) {
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

    public long getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(long purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public long getWarrantyExpireDate() {
        return warrantyExpireDate;
    }

    public void setWarrantyExpireDate(long warrantyExpireDate) {
        this.warrantyExpireDate = warrantyExpireDate;
    }

    @NonNull
    @Override
    public String toString() {
        return "[ID:" + this.getId() + ", PRODUCT: " + this.getProduct() + ", STORE: " + this.getStore() + ", PURCHASEDATE: " + this.getPurchaseDate() + ", EXPIREDATE: " + this.getWarrantyExpireDate() + "]";
    }
}
