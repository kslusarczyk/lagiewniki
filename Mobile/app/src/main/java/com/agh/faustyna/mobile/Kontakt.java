package com.agh.faustyna.mobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import static com.agh.faustyna.mobile.R.id.*;

public class Kontakt extends AppCompatActivity implements View.OnLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kontakt_zgromadzenie);

        View kontaktFurtaTel = findViewById(kontakt_furta_tel);
        kontaktFurtaTel.setOnLongClickListener(this);

        View kontaktFurtaMail1 = findViewById(kontakt_furta_mail_1);
        kontaktFurtaMail1.setOnLongClickListener(this);

        View kontaktFurtaMail2 = findViewById(kontakt_furta_mail_2);
        kontaktFurtaMail2.setOnLongClickListener(this);

        View kontaktMlodziezowyOsrodekTel1 = findViewById(kontakt_mlodziezowy_osrodek_tel_1);
        kontaktMlodziezowyOsrodekTel1.setOnLongClickListener(this);

        View kontaktMlodziezowyOsrodekTel2 = findViewById(kontakt_mlodziezowy_osrodek_tel_2);
        kontaktMlodziezowyOsrodekTel2.setOnLongClickListener(this);

        View kontaktMlodziezowyOsrodekTel3 = findViewById(kontakt_mlodziezowy_osrodek_tel_3);
        kontaktMlodziezowyOsrodekTel3.setOnLongClickListener(this);

        View kontaktMlodziezowyOsrodekMail = findViewById(kontakt_mlodziezowy_osrodek_mail);
        kontaktMlodziezowyOsrodekMail.setOnLongClickListener(this);

        View kontaktFaustinumTel = findViewById(kontakt_faustinum_tel);
        kontaktFaustinumTel.setOnLongClickListener(this);

        View kontaktFaustinumMail = findViewById(kontakt_faustinum_mail);
        kontaktFaustinumMail.setOnLongClickListener(this);

        View kontaktWydawnictwoTel = findViewById(kontakt_wydawnictwo_tel);
        kontaktWydawnictwoTel.setOnLongClickListener(this);

        View kontaktWydawnictwoMail1 = findViewById(kontakt_wydawnictwo_mail_1);
        kontaktWydawnictwoMail1.setOnLongClickListener(this);

        View kontaktWydawnictwoMail2 = findViewById(kontakt_wydawnictwo_mail_2);
        kontaktWydawnictwoMail2.setOnLongClickListener(this);

        View kontaktWydawnictwoSklep = findViewById(kontakt_wydawnictwo_sklep);
        kontaktWydawnictwoSklep.setOnLongClickListener(this);

        View kontaktDomTel = findViewById(kontakt_dom_tel);
        kontaktDomTel.setOnLongClickListener(this);

        View kontaktDomMail = findViewById(kontakt_dom_mail);
        kontaktDomMail.setOnLongClickListener(this);

        View kontaktRedakcjaTel = findViewById(kontakt_redakcja_tel);
        kontaktRedakcjaTel.setOnLongClickListener(this);

        View kontaktRedakcjaMail = findViewById(kontakt_redakcja_mail);
        kontaktRedakcjaMail.setOnLongClickListener(this);

        View kontaktInformacjaTel1 = findViewById(kontakt_informacja_tel_1);
        kontaktInformacjaTel1.setOnLongClickListener(this);

        View kontaktInformacjaTel2 = findViewById(kontakt_informacja_tel_2);
        kontaktInformacjaTel2.setOnLongClickListener(this);

        View kontaktInformacjaMail1 = findViewById(kontakt_informacja_mail_1);
        kontaktInformacjaMail1.setOnLongClickListener(this);

        View kontaktInformacjaMail2 = findViewById(kontakt_informacja_mail_2);
        kontaktInformacjaMail2.setOnLongClickListener(this);

        View kontaktInformacjaMail3 = findViewById(kontakt_informacja_mail_3);
        kontaktInformacjaMail3.setOnLongClickListener(this);

        View kontaktDomDuszpasterskiTel = findViewById(kontakt_dom_duszpasterski_tel);
        kontaktDomDuszpasterskiTel.setOnLongClickListener(this);

        View kontaktDomDuszpasterskiMail = findViewById(kontakt_dom_duszpasterski_mail);
        kontaktDomDuszpasterskiMail.setOnLongClickListener(this);

        View kontaktPoradniaTel = findViewById(kontakt_poradnia_tel);
        kontaktPoradniaTel.setOnLongClickListener(this);
    }

    public boolean onLongClick(View v) {
        String longClickedItem;
        longClickedItem = getResources().getResourceEntryName(v.getId());
        Log.d("onLongClick", longClickedItem);
        if (longClickedItem.contains("tel")){
            Log.d(longClickedItem, "zawiera TELEFON");
            String phoneNumber = (String) getResources().getText(getResources().getIdentifier(longClickedItem, "string", getPackageName()));
            dialPhoneNumber(phoneNumber);
        }
        if (longClickedItem.contains("mail")){
            Log.d(longClickedItem, "zawiera MAIL");
            String emailAddress = (String) getResources().getText(getResources().getIdentifier(longClickedItem, "string", getPackageName()));
            sendEmail(emailAddress);
        }
        if (longClickedItem.contains("sklep")){
            Log.d(longClickedItem, "zawiera nibyLink do sklepu");
            String urlForWebPage = (String) getResources().getText(getResources().getIdentifier(longClickedItem, "string", getPackageName()));
            openWebPage(urlForWebPage);
        }

        return false;
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL);
        dialPhoneIntent.setData(Uri.parse("tel:" + phoneNumber));
        if (dialPhoneIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(dialPhoneIntent);
        }
    }

    public void sendEmail(String mailAddress){
        Intent sendEmailIntent = new Intent(Intent.ACTION_SENDTO);
        sendEmailIntent.setData(Uri.parse("mailto:" + mailAddress));
        if (sendEmailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendEmailIntent);
        }
    }

    public void openWebPage(String url){
        Uri webPageAddress = Uri.parse(url);
        Intent openWebPageIntent = new Intent(Intent.ACTION_VIEW, webPageAddress);
        if (openWebPageIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(openWebPageIntent);
        }
    }
}
