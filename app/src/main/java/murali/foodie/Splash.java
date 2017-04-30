package murali.foodie;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        Typeface face= Typeface.createFromAsset(getAssets(),"fonts.ttf");
//        Typeface face=Typeface.create("Harlow Solid Italic Italic",3);
//        TextView txtV=(TextView)findViewById(R.id.textView4);
//        txtV.setTypeface(face);
        setContentView(R.layout.splash);

//set content view AFTER ABOVE sequence (to avoid crash)
        //this.setContentView(R.layout.splash);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 2000);
    }
}
