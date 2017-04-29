package murali.foodie;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class updatepass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepass);

    }
    public void mail(View view)
    {
        String uname=getIntent().getStringExtra("uname");
        String uphone=getIntent().getStringExtra("uphone");
        Log.i("Send email", "");
        String umail=getIntent().getStringExtra("umail");
        String bill=getIntent().getStringExtra("bill");
        String[] TO = {"k.suhaas7@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Your Order");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Dear Customer,\n"+uname+" - "+uphone+" \n Here are the order details:\n"+ bill);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
          //  Log.d("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
