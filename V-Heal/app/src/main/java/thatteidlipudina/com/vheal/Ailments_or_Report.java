

package thatteidlipudina.com.vheal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.Toast;

import thatteidlipudina.com.vheal.R;

import static thatteidlipudina.com.vheal.LoginActivity.usernamestatic;
import static thatteidlipudina.com.vheal.MapActivity.pincodestatic;

/*
Thatte Idli Pudina Chutney: Oct 26, 2018
This class if for the Report collection
 */

public class Ailments_or_Report extends AppCompatActivity {
    int page=0,consult=0;
    static String patientNamestatic="", diseaseStaticAilments="";
    private EditText patientname, patientphone, diseasetext, patientdoctor;
    String doctoryes = "no", patientage = "0";
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

    }
    public void onClickFloat(View view) {
        if(consult==1 && page==0){
                doctoryes = "yes";
        }
        else if(consult==0 && page==0){
                doctoryes = "no";
        }
        else{}
        patientNamestatic=patientname.getText().toString();
        String patientPhone= patientphone.getText().toString();
        String diseaseText= diseasetext.getText().toString();
        System.out.println(patientPhone + "hi");
        diseaseStaticAilments= diseaseText;


        if((patientNamestatic!=null && !patientNamestatic.equals("")) && (patientPhone!=null && !patientPhone.equals(""))  && (diseaseText!=null && !diseaseText.equals(""))){
            Backgroundworker b = new Backgroundworker(Ailments_or_Report.this);
            b.execute("patient update", patientPhone, patientNamestatic, patientage, pincodestatic, doctoryes, diseaseText, usernamestatic);
            Toast.makeText(this, usernamestatic, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Enter all details please", Toast.LENGTH_SHORT).show();
        }
        }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio1:
                if (checked)
                    consult=1;
                    break;
            case R.id.radio2:
                if (checked)
                    consult=2;
                    break;
        }
    }


    NumberPicker.OnValueChangeListener onValueChangeListener =
            new NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                        Toast.makeText(Ailments_or_Report.this,
                                "selected number " + numberPicker.getValue(), Toast.LENGTH_SHORT);
                        patientage = Integer.toString(numberPicker.getValue());

                    }
            };
}
