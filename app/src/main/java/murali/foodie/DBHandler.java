package murali.foodie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 11-04-2017.
 */

public class DBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="hotelinfo";
    private static final String table_name="items";
    private static final String key_itemid="itemid";
    private static final String key_item="item";
    private static final String key_price="price";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_table="CREATE TABLE "+table_name+"( "+key_itemid+" INTEGER PRIMARY KEY AUTOINCREMENT,"+key_item+" TEXT UNIQUE,"+key_price+" INTEGER NOT NULL);";
        db.execSQL(Create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(db);
    }
    public void additems(Shop shop)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
       // contentValues.put(key_itemid,shop.getItemId());
        contentValues.put(key_item,shop.getItem());
        contentValues.put(key_price,shop.getPrice());
        db.insert(table_name,null,contentValues);db.close();
    }
    public void deleteitem(Shop shop)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(table_name,key_item+"=?",new String[]{String.valueOf(shop.getItem())});
        db.close();
    }
    public void dltitem(String str)
    {
        SQLiteDatabase db=this.getWritableDatabase();
    //    db.delete(table_name,key_item+"=?",new String[]{String.valueOf(shop.getItem())});
        db.delete(table_name,key_item+"=?",new String[]{str});
        db.close();
    }
    public List<Shop> getAll()
    {
        List<Shop> itemlist=new ArrayList<Shop>();
        String selectquery="SELECT * FROM "+table_name;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectquery,null);
        if (cursor.moveToFirst()) {
            do {
                Shop shop = new Shop();
                shop.setItemid(Integer.parseInt(cursor.getString(0)));
                shop.setItem(cursor.getString(1));
                shop.setPrice(Integer.parseInt(cursor.getString(2)));
// Adding contact to list
                itemlist.add(shop);
            } while (cursor.moveToNext());
        }

// return contact list
        return itemlist;
    }
    public Shop getShop(String itemname) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table_name, new String[]{key_itemid,
                key_item, key_price}, key_item + "=?",
        new String[]{String.valueOf(itemname)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Shop contact = new Shop(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
// return shop
        return contact;
    }

    public void updateShop(String itemname,int prize) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table_name, new String[]{key_itemid,
                key_item, key_price}, key_item + "=?",
        new String[]{String.valueOf(itemname)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Shop shop = new Shop(Integer.parseInt(cursor.getString(0)),cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        db.close();
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_item, shop.getItem());
        values.put(key_price, String.valueOf(prize));
// updating row
       db1.update(table_name, values,key_itemid+ " = ?",new String[]{String.valueOf(shop.getItemId())});

        db1.close();
    }
}
