package thatteidlipudina.com.vheal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import thatteidlipudina.com.vheal.R;

/*
Thatte Idli Pudina Chutney
Coded by members of ThatteIdliPudina Chutney for CodeFundo : Oct 26, 2018
 */

public class MainActivity1 extends AppCompatActivity {

    private static final String TAG = "MainActivity1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        TextView mText = (TextView) findViewById(R.id.textView1);
        mText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity1.this, RegisterActivity.class);
                startActivity(intent);
            }


        });
        this.init();

    }

    private void init() {
        Button mLogin, mRegister;
        TextView mText;
        mLogin = (Button) (findViewById(R.id.Login));
        mRegister = (Button) (findViewById(R.id.Reg));

        Log.d(TAG, "onCreate: init is entered");
        mLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity1.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity1.this, RegisterActivity.class);
                startActivity(intent);
            }

        });


    }
}