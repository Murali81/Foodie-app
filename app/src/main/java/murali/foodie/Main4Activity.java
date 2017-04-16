package murali.foodie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        for(int k=0;k<results.size();k++)
        {
            res=results.get(k).split(",");
            TextView tv=new TextView(this);
            tv.setText(res[0]+"\t\t"+res[1]+"\t\t"+res[2]);
            l1.addView(tv);
        }
    }
}
