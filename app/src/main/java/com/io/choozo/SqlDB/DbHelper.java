package com.io.choozo.SqlDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;

public class DbHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "Choozo.db";
    private final static int DATABASE_VERSION =1;
    private final static String TABLE_NAME="cart";
    private final static String ID="Id";
    private final static String PRODUCT_Id="P_ID";
    private final static String PRODUCT_NAME="Name";
    private final static String PRODUCT_IMAGE="Image";
    private final static String PRODUCT_QTY="Quantity";
    private final static String PRODUCT_PRICE="Price";

    private  SQLiteDatabase database;

    /* ---------------------------------------------------------- create table for cart ----------------------------------------------*/

    String CREATE_TABLE = "create table "+TABLE_NAME+"("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            PRODUCT_NAME + " TEXT,"+ PRODUCT_IMAGE +" TEXT,"+ PRODUCT_QTY +" TEXT,"+ PRODUCT_PRICE +" TEXT,"+ PRODUCT_Id +" TEXT);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    /*---------------------------------------------  drop table if alerady exist -----------------------------------------------------*/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /* ----------------------------------------------------insert data in table -----------------------------------------------------*/

    public boolean insertData(String name, String image, String qty, String price,String pid){
        database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name",name);
        values.put("Image",image);
        values.put("Quantity",qty);
        values.put("Price",price);
        values.put("P_ID",pid);
        long result = database.insert(TABLE_NAME,null,values);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    /* ---------------------------------------------- Get all data from data base ------------------------------------------------*/

    public Cursor getData(){
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

    /*------------------------------------- get partcular product data from table ------------------------------------------------*/
    public Cursor getDataq(String pid){
        database = this.getWritableDatabase();
        //Cursor cursor = database.rawQuery("select * from "+TABLE_NAME WHERE PRODUCT_Id ,null);
        Cursor cursor = database.rawQuery("SELECT * FROM cart WHERE P_ID = '"+pid+"'", null);
        return cursor;
    }

    /* ---------------------------------------------------- update data from table ----------------------------------------------*/

    public boolean updateData(String name, String image, String qty, String price,String pid)
    {
        database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name",name);
        values.put("Image",image);
        values.put("Quantity",qty);
        values.put("Price",price);
        values.put("P_ID",pid);
        long result = database.update(TABLE_NAME, values, PRODUCT_Id + "=?", new String[]{pid});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /* ---------------------------------------------------  Delete Data from table  -------------------------------------------------*/

    public boolean deleteData(String pid)
    {
        database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("P_ID",pid);
        long d=database.delete(TABLE_NAME,PRODUCT_Id+ "=?",new String[]{pid});
        if(d==-1){
            return false;
        }else
        {
            return true;
        }
    }


    /* ----------------------------------------------------delete all after logout--------------------------------------------------*/
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

}
