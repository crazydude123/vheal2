

package thatteidlipudina.com.vheal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.Toast;

import thatteidlipudina.com.vheal.R;

import static thatteidlipudina.com.vheal.LoginActivity.usernamestatic;
import static thatteidlipudina.com.vheal.MapActivity.pincodestatic;

public class Ailments_or_Report extends AppCompatActivity {
    int page;
    static String patientNamestatic, diseaseStaticAilments;
    private EditText patientname, patientphone, diseasetext, patientdoctor;
    String doctoryes = "no", patientage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ailment_or_report);

        NumberPicker np = findViewById(R.id.np);
        patientname= (EditText) findViewById(R.id.patientname);
        patientphone= (EditText) findViewById(R.id.phone);
        diseasetext= (EditText) findViewById(R.id.diseasetext);
        np.setMinValue(0);
        np.setMaxValue(90);

        np.setOnValueChangedListener(onValueChangeListener);




        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);


        toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //int page;
                if (isChecked) {
                    page =1;
                } else {
                    page =0;
                }
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClickFloat(View view) {
                if(page==1){
                    Intent intent = new Intent(getApplicationContext(), Upload.class);
                    startActivity(intent);

                }
            }
        });*/


    }
    public void onClickFloat(View view) {
        if(page==1){
                doctoryes = "yes";
//            Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
//            startActivity(intent);

        }
        else if(page==0){
                doctoryes = "no";
//            Intent intent = new Intent(getApplicationContext(), Credit.class);
//            startActivity(intent);
        }
        patientNamestatic=patientname.getText().toString();
        String patientPhone= patientphone.getText().toString();
        String diseaseText= diseasetext.getText().toString();
        diseaseStaticAilments= diseaseText;
        System.out.println(diseaseStaticAilments);
        Backgroundworker b = new Backgroundworker(Ailments_or_Report.this);
        b.execute("patient update", patientPhone, patientNamestatic, patientage, pincodestatic, doctoryes, diseaseText, usernamestatic);
        Toast.makeText(this, usernamestatic, Toast.LENGTH_SHORT).show();
    }



    NumberPicker.OnValueChangeListener onValueChangeListener =
            new NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(Ailments_or_Report.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);
                    patientage= Integer.toString(numberPicker.getValue());
                }
            };
}
