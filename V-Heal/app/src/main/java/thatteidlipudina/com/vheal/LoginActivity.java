package thatteidlipudina.com.vheal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.Socket;
/*
Thatte Idli Pudina Chutney
Coded by members of ThatteIdliPudina Chutney for CodeFundo : Oct 26, 2018
 */
public class LoginActivity extends AppCompatActivity {
    static String usernamestatic;
    private Socket socket;
    private EditText mEmailView;
    private EditText mPasswordView;
    String username="";
    String pass="";

    Button mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView= (EditText) findViewById(R.id.email);
        mPasswordView=(EditText)findViewById(R.id.password);
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);

       mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
                                                  public void onClick(View view) {
                                                      String type= "login";
                                                      username= mEmailView.getText().toString();
                                                      usernamestatic=username;
                                                      pass=mPasswordView.getText().toString();
                                                      if((pass!=null && !pass.equals("")) && (username!=null && !username.equals("null"))) {
                                                          Backgroundworker b = new Backgroundworker(LoginActivity.this);
                                                          b.execute(type, username, pass);
                                                      }
                                                      else
                                                          Toast.makeText(LoginActivity.this, "Enter credentials", Toast.LENGTH_SHORT).show();
                                                  }
                                              }

        );
    }
}


