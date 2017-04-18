package murali.foodie;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        LinearLayout l1=(LinearLayout)findViewById(R.id.act4);
        ArrayList<String> results=getIntent().getStringArrayListExtra("results");
        LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        String res[]=new String[3];
        int total=0;
        for(int k=0;k<results.size();k++)
        {
            res=results.get(k).split(",");
            final View myView = linflater.inflate(R.layout.cartview, null);
            TextView tv1 = (TextView)myView.findViewById(R.id.item);
            TextView tv2 = (TextView)myView.findViewById(R.id.price);
            TextView tv3 = (TextView)myView.findViewById(R.id.qty);
            tv1.setText(res[0]);
            tv2.setText(res[1]);
            tv3.setText(res[2]);
//            TextView tv=new TextView(this);
//            tv.setText("\t\t\t\t\t\t\t\t"+res[0]+"\t\t\t\t\t\t\t\t\t\t\t\t"+res[1]+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+res[2]);
//            tv.setTextSize(30);
            total=total+Integer.parseInt(res[1])*Integer.parseInt(res[2]);
            l1.addView(myView);
        }
        TextView tv4=new TextView(this);
        tv4.setText("-----------------------------------------------------------------------------------------------------------------");
        tv4.setLayoutParams(new LinearLayout.LayoutParams(1200,50));
        l1.addView(tv4);
        if(total==0)
        {
            TextView tv7=new TextView(this);
            tv7.setText("\t\t\t\t\t\t\t\t\t\t\t\t"+"Your cart seems empty");
            tv7.setTextSize(30);
            l1.addView(tv7);
        }
        else {
            TextView tv5 = new TextView(this);
            tv5.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "Total :" + total);
            tv5.setTextSize(30);
            l1.addView(tv5);
        }

    }
}
