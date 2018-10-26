package thatteidlipudina.com.vheal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/*
Thatte Idli Pudina Chutney
Coded by members of ThatteIdliPudina Chutney for CodeFundo : Oct 26, 2018
 */
public class doyouhaveapres extends AppCompatActivity {

    Button buttonReport, buttonnoReport;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doyouhavepres);
        buttonReport =(Button) findViewById(R.id.submitReport1);
        buttonnoReport= (Button) findViewById(R.id.noReport);
        buttonnoReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=  new Intent(doyouhaveapres.this, Credit.class);
                startActivity(i);
            }
        });
        buttonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(doyouhaveapres.this, UploadActivity.class);
                startActivity(i);
            }
        });
    }
}
