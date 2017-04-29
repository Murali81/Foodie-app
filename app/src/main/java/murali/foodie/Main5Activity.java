package murali.foodie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

    }

    public void updatepswd(View view) {
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        EditText usrname = (EditText) findViewById(R.id.editText);
        EditText email = (EditText) findViewById(R.id.editText4);
        EditText phoneno = (EditText) findViewById(R.id.editText5);
        EditText newpw = (EditText) findViewById(R.id.editText6);
        EditText cnnewpw = (EditText) findViewById(R.id.editText7);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        try {
            String usrnam = usrname.getText().toString();
            //Toast.makeText(this, "Entered name is "+usrnam, Toast.LENGTH_SHORT).show();
            String emal = email.getText().toString();
            String phnno = phoneno.getText().toString();
            String newpsw = newpw.getText().toString();
            String cnewpw = cnnewpw.getText().toString();
            String combostring = loginDataBaseAdapter.getSinlgeEntry(usrnam+"");

            if (!newpsw.equals(cnewpw)) {
                Toast.makeText(this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
                return;
            }

            if (combostring.equals("No")) {
                Toast.makeText(this, "User Name or Password does not match", Toast.LENGTH_SHORT).show();
                return;
            }
            String[] combi = combostring.split(",");
            String storeduser = combi[1];
            String storedphone = combi[2];
            String storedemail = combi[3];
            String nameuser = combi[4];
            if (emal.equals(storedemail) && phnno.equals(storedphone)) {
                try {
                    loginDataBaseAdapter.updateEntry(storeduser, newpsw, storedphone, storedemail, nameuser);
                    Toast.makeText(this, "Password Updated", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this, "Error is " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error is "+ e, Toast.LENGTH_SHORT).show();
        }

    }

}