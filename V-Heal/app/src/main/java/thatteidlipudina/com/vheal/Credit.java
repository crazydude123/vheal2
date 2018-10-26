

package thatteidlipudina.com.vheal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.animation.*;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import thatteidlipudina.com.vheal.R;

/*
Thatte Idli Pudina Chutney
Coded by members of ThatteIdliPudina Chutney for CodeFundo : Oct 26, 2018
 */
public class Credit extends AppCompatActivity {
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        Animation animation;
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom);
        ImageView im= findViewById(R.id.imageView);
        im.startAnimation(animation);

        mButton= (Button) findViewById(R.id.back);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Credit.this, Search.class);
                startActivity(intent);
            }
        });

    }
    public void homeClick(View view){
        Intent intent = new Intent(getApplicationContext(), Search.class);
        startActivity(intent);

    }
}
