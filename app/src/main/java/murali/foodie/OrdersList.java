package murali.foodie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class OrdersList extends AppCompatActivity {

    int[] IMAGES = {R.mipmap.biryani,R.mipmap.naan,R.mipmap.panner};

    String[] names = {"Vegetable Biryani", "Butter Naan", "Panner Butter Masala"};
    String[] cost = {"350","20","180"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);
        DBHandler db = new DBHandler(this);
        //Log.d("Reading: ", "Reading all shops..");
        List<Shop> shops = db.getAll();
        String log[]=new String[40];int k=0;
        for (Shop shop : shops) {
            log[k] =shop.getItemId() +","+shop.getItem() + "," + shop.getPrice();
            // Writing shops to log
            k=k+1;
            //Log.d("Shop: : ", log);
        }
        for(int c=0;c<k;c++)
        {
            String arr[];
            arr=log[c].split(",");
            names[c]=arr[1];
            cost[c]=arr[2];

        }
        ListView listView = (ListView)findViewById(R.id.ListView);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textView_name = (TextView)view.findViewById(R.id.textView_name);
            TextView textView_cost = (TextView)view.findViewById(R.id.textView_cost);

            imageView.setImageResource(IMAGES[i]);
            textView_name.setText(names[i]);
            textView_cost.setText(cost[i]);
            return view;
        }
    }
}
