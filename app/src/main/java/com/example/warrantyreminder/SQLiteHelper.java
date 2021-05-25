package com.example.warrantyreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.LinkedList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TABLE_WARRANTY = "warranty";

    private static final String KEY_ID = "id";
    private static final String KEY_PRODUCT = "product";
    private static final String KEY_STORE = "store";
    private static final String KEY_PURCHASE_DATE = "purchaseDate";
    private static final String KEY_WARRANTY_EXPIRE_DATE = "warrantyExpireDate";

    private static final String[] COLUMNS = {KEY_ID, KEY_PRODUCT, KEY_STORE, KEY_PURCHASE_DATE, KEY_WARRANTY_EXPIRE_DATE};

    private static final int DATABSE_VERSION = 1;

    private static final String DATABSE_NAME = "WarrantyDB";

    public SQLiteHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_WARRANTY + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_PRODUCT + " TEXT, " + KEY_STORE + " TEXT, " + KEY_PURCHASE_DATE + " INTEGER, " + KEY_WARRANTY_EXPIRE_DATE + " INTEGER" +
                " )";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_IF_EXIST = "DROP TABLE IF EXISTS " + TABLE_WARRANTY;
        db.execSQL(DROP_IF_EXIST);
        this.onCreate(db);
    }

    public void addProduct(WarrantyEntry we) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT, we.getProduct());
        values.put(KEY_STORE, we.getStore());
        values.put(KEY_PURCHASE_DATE, we.getPurchaseDate());
        values.put(KEY_WARRANTY_EXPIRE_DATE, we.getWarrantyExpireDate());

        db.insert(TABLE_WARRANTY, null, values);
        db.close();
    }

    public WarrantyEntry getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WARRANTY, COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null) cursor.moveToFirst();

        WarrantyEntry we = new WarrantyEntry();
        we.setId(Integer.parseInt(cursor.getString(0)));
        we.setProduct(cursor.getString(1));
        we.setStore(cursor.getString(2));
        we.setPurchaseDate(Long.parseLong(cursor.getString(3)));
        we.setWarrantyExpireDate(Long.parseLong(cursor.getString(4)));
        // TODO: Maybe DB Connection schließen
        return we;
    }

    public List<WarrantyEntry> getAllProducts() {
        List<WarrantyEntry> wes = new LinkedList<>();
        String query = "SELECT * FROM " + TABLE_WARRANTY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        WarrantyEntry we;
        if(cursor.moveToFirst()) {
            do {
                we = new WarrantyEntry();
                we.setId(Integer.parseInt(cursor.getString(0)));
                we.setProduct(cursor.getString(1));
                we.setStore(cursor.getString(2));
                we.setPurchaseDate(Long.parseLong(cursor.getString(3)));
                we.setWarrantyExpireDate(Long.parseLong(cursor.getString(4)));

                wes.add(we);
            } while(cursor.moveToNext());
        }
        return wes;
    }

    public int updateProduct(WarrantyEntry we) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PRODUCT, we.getProduct());
        values.put(KEY_STORE, we.getStore());
        values.put(KEY_PURCHASE_DATE, we.getPurchaseDate());
        values.put(KEY_WARRANTY_EXPIRE_DATE, we.getWarrantyExpireDate());

        int i = db.update(TABLE_WARRANTY, values, KEY_ID + " = ?", new String[]{String.valueOf(we.getId())});
        db.close();
        return i;
    }

    public void deleteProduct(WarrantyEntry we) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WARRANTY, KEY_ID + " = ?", new String[]{String.valueOf(we.getId())});
    }
}
