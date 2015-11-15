package com.agh.faustyna.mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Karolina on 2015-11-07.
 */
public class KoronkaZaKonajacych extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        sendSMS(getString(R.string.koronka_phone_number), getString(R.string.sms_prompt));

        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.koronka_toast), Toast.LENGTH_LONG);
        toast.show();
    }

    public void sendSMS(String phoneNumber, String message){
        Intent sendSMSIntent = new Intent(Intent.ACTION_SENDTO);
        sendSMSIntent.setData(Uri.parse("smsto:" + phoneNumber));
        sendSMSIntent.putExtra("sms_body", message);
        if (sendSMSIntent.resolveActivity(getPackageManager()) != null){
            startActivity(sendSMSIntent);
        }

    }
}