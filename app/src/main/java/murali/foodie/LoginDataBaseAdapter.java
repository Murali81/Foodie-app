package murali.foodie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Student on 04-04-2017.
 */

public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
// SQL Statement to create a new database.
    public static final String key_phone="MobNo";
    public static final String key_email="Email";
    public static final String key_name="Name";
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME text ,PASSWORD text ,"+key_phone+" text UNIQUE NOT NULL ,"+key_email+" text UNIQUE NOT NULL ,"+key_name+" text NOT NULL);" ;
    // Variable to hold the database instance
    public SQLiteDatabase db;
    String res="";
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String userName,String password,String mobnumber,String email,String nameuser)
    {
        ContentValues newValues = new ContentValues();
// Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);
        newValues.put(key_phone,mobnumber);
        newValues.put(key_email,email);
        newValues.put(key_name,nameuser);

// Insert the row into your table
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
           long status=db.insert("LOGIN", null, newValues);
            if(status>0) Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show();
            else Toast.makeText(context, "Invalid Field Inputs", Toast.LENGTH_SHORT).show();
            try {
                Cursor cursor1 = db.rawQuery("SELECT * FROM LOGIN", null);
                cursor1.moveToFirst();
                do {
                    res = res + "," + cursor1.getString(1);
                }
                while (cursor1.moveToNext());
           //     Toast.makeText(context, "Values are "+res, Toast.LENGTH_SHORT).show();
                cursor1.close();
            }catch (Exception e){
                Toast.makeText(context, "Error is "+e, Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(context, "Username already exists", Toast.LENGTH_SHORT).show();
        }

///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public int deleteEntry(String UserName)
    {
//String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
// Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSinlgeEntry(String userName) {

        Toast.makeText(context, "String is "+userName, Toast.LENGTH_SHORT).show();
        Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "No";
        } else {

            cursor.moveToFirst();
            String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
            String username = cursor.getString(cursor.getColumnIndex("USERNAME"));
            String phoneno = cursor.getString(cursor.getColumnIndex(key_phone));
            String Email = cursor.getString(cursor.getColumnIndex(key_email));
            String nameuser = cursor.getString(cursor.getColumnIndex(key_name));
            cursor.close();
            return password + "," + username + "," + phoneno + "," + Email + "," + nameuser;
        }

    }
    public void updateEntry(String userName,String password,String phone,String email,String name)
    {
// Define the updated row content.
        ContentValues updatedValues = new ContentValues();
// Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);
        updatedValues.put(key_phone,phone);
        updatedValues.put(key_email,email);
        updatedValues.put(key_name,name);
        String where="USERNAME = ?";
        try {
            db.update("LOGIN", updatedValues, where, new String[]{userName});
        }
        catch (Exception e)
        {
            Toast.makeText(context, "Error is WTF "+e, Toast.LENGTH_SHORT).show();
        }
    }
}
