package murali.foodie;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static murali.foodie.R.id.editTextPassword;
import static murali.foodie.R.id.editTextUserName;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
    // Get The Refference Of Buttons
        btnSignIn=(Button)findViewById(R.id.buttonSignIN);
        btnSignUp=(Button)findViewById(R.id.buttonSignUP);

// Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// TODO Auto-generated method stub

/// Create Intent for SignUpActivity abd Start The Activity
                Intent intentSignUP=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intentSignUP);
            }
        });
    }
    // Methos to handleClick Event of Sign In Button
    public void signIn(View V) {
//        final Dialog dialog = new Dialog(MainActivity.this);
//        dialog.setContentView(R.layout.activity_login);
//        dialog.setTitle("Login");

// get the Refferences of views
        EditText editTextUserName = (EditText) findViewById(R.id.editTextUserNameToLogin);
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPasswordToLogin);

        // Button btnSignIn=(Button)findViewById(R.id.buttonSignIn);

// Set On ClickListener
//        btnSignIn.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
// get The User name and Password
        String userName;
        userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();

// fetch the Password form database for respective user name
        String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

// check if the Stored password matches with Password entered by user
        if (password.equals(storedPassword)) {
            Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
// Close The Database
        loginDataBaseAdapter.close();
    }
    public void vendors(View view)
    {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}

