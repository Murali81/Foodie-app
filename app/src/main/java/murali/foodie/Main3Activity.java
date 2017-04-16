package murali.foodie;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    List<EditText> allEds = new ArrayList<EditText>();
    List<Button> allplus = new ArrayList<Button>();
    List<Button> allminus = new ArrayList<Button>();
    List<View> allviews=new ArrayList<View>();
//    View newview;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        LinearLayout l1 = (LinearLayout)findViewById(R.id.activity_main3);
        DBHandler db = new DBHandler(this);
        LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Log.d("Reading: ", "Reading all shops..");
        List<Shop> shops = db.getAll();
        String log = "";
        for (Shop shop : shops) {
            // log =log+"Id:"+shop.getItemId() + " ,Name: " + shop.getItem() + " ,Address: " + shop.getPrice();
            // Writing shops to log
            log = "Id:" + shop.getItemId() + " ,Name: " + shop.getItem() + " ,Address: " + shop.getPrice();
            Log.d("Shop: : ", log);
//            TextView tv = new TextView(this);
//            tv.setText(log);
//            tv.setId(count + 1);
//
//            EditText et = new EditText(this);
//            et.setHint("Quantity");
//            et.setId(count + 2);
//            Button button = new Button(this);
//            button.setText("ADD");
//            button.setId(count + 3);
//            count = count + 1;
//            l1.addView(tv);
//            l1.addView(et);
//            l1.addView(button);
           final View myView = linflater.inflate(R.layout.idle, null); //here item is the the layout you want to inflate
            //myView.setId(count);
            allviews.add(myView);
/*
   You can change TextView text and ImageView images here e.g.
   TextView tv = (TextView)myView.findViewById(R.id.title_N1);
   tv.setText(pos);

*/
            TextView tv = (TextView)myView.findViewById(R.id.price);
            EditText et = (EditText) myView.findViewById(R.id.qty);
            Button plus1 = (Button) myView.findViewById(R.id.plus);
            Button minus1 = (Button)myView.findViewById(R.id.minus);
            tv.setText(shop.getPrice()+".");
            //tv.setId(count+1);
            //et.setId(count+2);
            allEds.add(et);
            //plus1.setId(count+3);
            //minus1.setId(count+4);
            allplus.add(plus1);
            allminus.add(minus1);
            allplus.get(count).setId(count+1);
            allminus.get(count).setId(count+2);
            allEds.get(count).setId(count+3);

//            minus1.setId(count+4);
            View newview=allviews.get(count);
            allplus.get(count).setOnClickListener(new newview.OnClickListener() {
                @Override
                public void onClick(newview view) {
                    int val=Integer.parseInt(allEds.get(count).getText().toString());
                    val=val+1;
                    allEds.get(count).setText(val+"");
                }
            });
            allminus.get(count).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int val=Integer.parseInt(allEds.get(count).getText().toString());
                    if(val==0) val=0;
                    else
                        val=val-1;
                    allEds.get(count).setText(val+"");
                }
            });
            Toast.makeText(this, "Hete"+count, Toast.LENGTH_SHORT).show();
            l1.addView(myView);
        count=count+1;
        }
        Button cart=new Button(this);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] strings = new String[allEds.size()];

                for(int i=0; i < allEds.size(); i++){
                    strings[i] = allEds.get(i).getText().toString();
                    Toast.makeText(Main3Activity.this, strings[i], Toast.LENGTH_SHORT).show();
                }
            }
        });
    l1.addView(cart);


    }
//    View.OnClickListener setbt(final int cnt,final int val) {
//        return new View.OnClickListener() {
//            public void onClick(View v) {
//                int res=val+1;
//                allEds.get(cnt).setText(res+"");
//                Toast.makeText(Main3Activity.this,"Updated value is"+ allEds.get(cnt).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        };
//    }
//    View.OnClickListener setat(final int cnt,final int val) {
//        return new View.OnClickListener() {
//            public void onClick(View v) {
//                int res;
//                if(val==0)res=0;
//                    else res=val-1;
//                allEds.get(cnt).setText(res+"");
//                Toast.makeText(Main3Activity.this,"Updated value is"+ allEds.get(cnt).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        };
//    }


}
