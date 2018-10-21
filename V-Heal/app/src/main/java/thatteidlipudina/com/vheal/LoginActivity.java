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

public class LoginActivity extends AppCompatActivity {
    static String usernamestatic;
    private Socket socket;
    private EditText mEmailView;
    private EditText mPasswordView;

    //
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
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
                                                      String username= mEmailView.getText().toString();
                                                      usernamestatic=username;
                                                      Toast.makeText(LoginActivity.this,username, Toast.LENGTH_SHORT).show();
                                                      String pass=mPasswordView.getText().toString();
                                                      Backgroundworker b= new Backgroundworker(LoginActivity.this);
                                                      b.execute(type, username, pass);

//                                                      Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                                      startActivity(intent);
                                                  }
                                              }

        );
    }
}


