package com.example.yoser.remo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class message extends AppCompatActivity {

    Button button;
    EditText editText,editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        if (ContextCompat.checkSelfPermission(message.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(message.this,
                    Manifest.permission.SEND_SMS)) {

                ActivityCompat.requestPermissions(message.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            } else {

                ActivityCompat.requestPermissions(message.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }

        } else {
            // rien faire

        }

        button = (Button) findViewById(R.id.envoyer);
        editText = (EditText)findViewById(R.id.numer);
        editText2 = (EditText)findViewById(R.id.message);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View view) {

                String number =editText.getText().toString();
                String sms =editText2.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number,null,sms,null,null);
                    Toast.makeText(message.this ,"envoye!", Toast.LENGTH_LONG).show();

                }catch (Exception e){

                    Toast.makeText(message.this ,"echec!", Toast.LENGTH_LONG).show();
                }

            }

    });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(message.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "No permission granted!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}