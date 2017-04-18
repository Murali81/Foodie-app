package murali.foodie;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void add(View view)
    {
        try
        {
        EditText e1=(EditText)findViewById(R.id.editText2);
        EditText e2=(EditText)findViewById(R.id.editText3);
        String itemname=e1.getText().toString();
        int price=Integer.parseInt(e2.getText().toString());
        DBHandler db = new DBHandler(this);
        try {
               db.additems(new Shop(itemname,price));
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error is "+e+" ", Toast.LENGTH_SHORT).show();
            Log.d("eror","Error is "+e);
        }
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();}
        catch (Exception e)
        {
            Toast.makeText(this, "Enter Valid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void disp(View view) {
        TextView tv = (TextView) findViewById(R.id.textView2);

        DBHandler db = new DBHandler(this);
        Log.d("Reading: ", "Reading all shops..");
        List<Shop> shops = db.getAll();
        String log="";
        for (Shop shop : shops) {
            log =log+"Id:"+shop.getItemId() + " ,Name: " + shop.getItem() + " ,Address: " + shop.getPrice()+"\n";
            // Writing shops to log
            Log.d("Shop: : ", log);

        }
        tv.setText(log);
    }
    public void del(View view) {
        try {
            EditText e7 = (EditText) findViewById(R.id.itemdel);
            DBHandler db = new DBHandler(this);
            try {
                String reslt = e7.getText().toString();
                db.dltitem(reslt);
            }
            catch (Exception e)
            {
                Toast.makeText(this, "Requested Item Not Present", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Requested Item Not Present", Toast.LENGTH_SHORT).show();
        }
    }
    public void updatitem(View view)
    {
        try{
        EditText e9=(EditText)findViewById(R.id.updat);
        String []resu=new String[2];
        resu=e9.getText().toString().split(" ");
        DBHandler db=new DBHandler(this);
        db.updateShop(resu[0],Integer.parseInt(resu[1]));
    }
        catch (Exception e)
        {
            Toast.makeText(this, "Input must be \'item\' space \'price\' ", Toast.LENGTH_SHORT).show();
        }
}
}