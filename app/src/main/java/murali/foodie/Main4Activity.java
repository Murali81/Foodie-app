package murali.foodie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
       String res[]=new String[3];
        int total=0;
        for(int k=0;k<results.size();k++)
        {
            res=results.get(k).split(",");
            TextView tv=new TextView(this);
            tv.setText("\t\t\t\t\t\t\t\t"+res[0]+"\t\t\t\t\t\t\t\t\t\t\t\t"+res[1]+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+res[2]);
            tv.setTextSize(30);
            total=total+Integer.parseInt(res[1])*Integer.parseInt(res[2]);
            l1.addView(tv);
        }
        TextView tv1=new TextView(this);
        tv1.setText("-----------------------------------------------------------------------------------------------------------------");
        tv1.setLayoutParams(new LinearLayout.LayoutParams(1200,50));
        l1.addView(tv1);
        TextView tv2=new TextView(this);
        tv2.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Total :"+total);
        tv2.setTextSize(30);
        l1.addView(tv2);
    }
}
