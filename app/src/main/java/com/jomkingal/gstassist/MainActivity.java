package com.jomkingal.gstassist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button scanBtn;
    private TextView contentTxt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = (Button)findViewById(R.id.scan_button);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
    }
    public void onClick(View v){
//respond to clicks
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
//scan
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
//we have a result
            //String scanContent = scanningResult.getContents();
            String vin = scanningResult.getContents();
            //contentTxt.setText("CONTENT: " + scanContent);
            //my variables are Model Year, Make, Model, Engine, Drivetrain, Trim
            //you have to divide VIN first. you have WMI 1-3, VDS 4-8, Check Digit 9, VIS 10-17
            //https://www.carfax.com/blog/vin-decoding https://en.wikipedia.org/wiki/Vehicle_identification_number
            String wmi = vin.substring(0,2);
            String vds = vin.substring(3,7);
            String vis = vin.substring(9,16);

            contentTxt.setText("VIN: " + vin);


        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
