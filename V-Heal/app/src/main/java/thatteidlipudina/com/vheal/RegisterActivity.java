package thatteidlipudina.com.vheal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.Socket;

import thatteidlipudina.com.vheal.R;

/*
Thatte Idli Pudina Chutney
Coded by members of ThatteIdliPudina Chutney for CodeFundo : Oct 26, 2018
 */
public class RegisterActivity extends AppCompatActivity {
    static String namestatic;
    EditText mEmail, mName, mPassword, mPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button mRegister = (Button) findViewById(R.id.Done);
        mEmail= (EditText) findViewById(R.id.textemail);
        mName= (EditText) findViewById(R.id.textname);
        mPassword= (EditText) findViewById(R.id.editText);
        mPhone= (EditText) findViewById(R.id.textphone);

            mRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String type= "register";
                    String username= mEmail.getText().toString();
                    String name= mName.getText().toString();
                    String phone= mPhone.getText().toString();
                    String pass= mPassword.getText().toString();
                    namestatic=name;
                    Toast.makeText(RegisterActivity.this,username, Toast.LENGTH_SHORT).show();
                    Backgroundworker b= new Backgroundworker(RegisterActivity.this);
                    b.execute(type, username, pass, name, phone);

                }
            });
        }
    }


