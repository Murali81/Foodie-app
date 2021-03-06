package murali.foodie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText editTextUserName,editTextPassword,editTextConfirmPassword;
    Button btnCreateAccount;
     EditText emailid;
    EditText phoneno;
    EditText fullname;

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

// Get Refferences of Views
        editTextUserName=(EditText)findViewById(R.id.editTextUserName);
        emailid=(EditText)findViewById(R.id.emailet);
        phoneno=(EditText)findViewById(R.id.phoneet);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);
   fullname=(EditText)findViewById(R.id.fullname);
    }
    public void signup(View view)
    {

        String userName=editTextUserName.getText().toString();
        String password=editTextPassword.getText().toString();
        String confirmPassword=editTextConfirmPassword.getText().toString();
        String phno=phoneno.getText().toString();
        String emil=emailid.getText().toString();
        String nameuser=fullname.getText().toString();
// check if any of the fields are vaccant
        if(userName.equals("")||password.equals("")||confirmPassword.equals("")||emil.equals("")||nameuser.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
            return;
        }
// check if both password matches
        if(!password.equals(confirmPassword))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
// Save the Data in Database
            loginDataBaseAdapter.insertEntry(userName,password,phno,emil,nameuser);
             Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onDestroy() {
// TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
    }
