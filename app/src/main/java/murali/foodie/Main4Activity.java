package murali.foodie;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        final String umail=getIntent().getStringExtra("umail");
        final String uname=getIntent().getStringExtra("uname");
        final String uphone=getIntent().getStringExtra("uphone");
        LinearLayout l1=(LinearLayout)findViewById(R.id.act4);
        ArrayList<String> results=getIntent().getStringArrayListExtra("results");
        LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        String res[]=new String[3];
        int total=0;
        String order="";
        View myw = linflater.inflate(R.layout.cartview, null);
        TextView tv9 = (TextView)myw.findViewById(R.id.item);
        TextView tv10 = (TextView)myw.findViewById(R.id.price);
        TextView tv11 = (TextView)myw.findViewById(R.id.qty);
        tv9.setText("Item");tv10.setText("Price");tv11.setText("Qty.");
        l1.addView(myw);
        TextView tv90=new TextView(this);
        tv90.setText("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        tv90.setBackgroundColor(Color.parseColor("#f2f0f0"));
       // tv90.setTextColor(R.color.colorPrimary);
        tv90.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50));
        l1.addView(tv90);
        for(int k=0;k<results.size();k++)
        {
            res=results.get(k).split(",");
            final View myView = linflater.inflate(R.layout.cartview, null);
            TextView tv1 = (TextView)myView.findViewById(R.id.item);
            TextView tv2 = (TextView)myView.findViewById(R.id.price);
            TextView tv3 = (TextView)myView.findViewById(R.id.qty);
            tv1.setText(" "+res[0]);
            tv2.setText(res[1]+" ₹");
            tv3.setText(res[2]);
            order=order+res[0]+"-"+res[2]+"\n";
//            TextView tv=new TextView(this);
//            tv.setText("\t\t\t\t\t\t\t\t"+res[0]+"\t\t\t\t\t\t\t\t\t\t\t\t"+res[1]+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+res[2]);
//            tv.setTextSize(30);
            total=total+Integer.parseInt(res[1])*Integer.parseInt(res[2]);
            l1.addView(myView);
        }
        order=order+"\nTotal-"+total+" ₹";
        TextView tv4=new TextView(this);
        tv4.setText("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        tv4.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50));
        tv4.setBackgroundColor(Color.parseColor("#f2f0f0"));
        l1.addView(tv4);
        tv90.setBackgroundColor(Color.parseColor("#f2f0f0"));
        if(total==0)
        {
            TextView tv7=new TextView(this);
            tv7.setText("\t\t\t\t\t\t\t\t\t\t\t\t"+"Your cart seems empty");
            tv7.setTextSize(30);
            l1.addView(tv7);
            tv7.setBackgroundColor(Color.parseColor("#f2f0f0"));
        }
        else {
            TextView tv5 = new TextView(this);
            tv5.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "Total :" + total+" ₹");
            tv5.setTextSize(30);
            l1.addView(tv5);
            tv5.setBackgroundColor(Color.parseColor("#f2f0f0"));
        }
        Button bt=new Button(this);
        bt.setText("Finish Payment");
        bt.setBackgroundColor(Color.parseColor("#f29d13"));

        final String finalOrder = order;
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {

                    public void run() {

                        try {

                            GMailSender sender = new GMailSender(

                                    "foodie.bmu@gmail.com",

                                    "foodieapp1");




                            sender.sendMail("Order Details","Dear Customer,\n "+uname+" - "+uphone+"\nYour final Order is\n"+finalOrder,"k.suhaas7@gmail.com","kondragunta.chowdary.15cse@bml.edu.in");
                       //     sender.sendMail("Order Details", finalOrder,"foodie.bmu@gmail.com","kondragunta.chowdary.15cse@bml.edu.in");
                          // sender.sendMail("Order Details","Dear Customer,\nYour final Order is\n"+finalOrder,"foodie.bmu@gmail.com",umail);
                        } catch (Exception e) {

                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();



                        }

                    }

                }).start();
                Intent intent=new Intent(getApplicationContext(),updatepass.class);
                intent.putExtra("umail",umail);
                intent.putExtra("bill",finalOrder);
                intent.putExtra("uname",uname);
                intent.putExtra("uphone",uphone);
                startActivity(intent);

            }
        });
    l1.addView(bt);
    }

}
