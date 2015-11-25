package com.agh.faustyna.mobile;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    HashMap<MenuGroupItem, List<String>> menuChildItems;
    private ArrayList<MenuGroupItem> menuGroupItems;

    private String[] menuGroupTitles;
    private TypedArray menuGroupIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuGroupTitles = getResources().getStringArray(R.array.title_group_items);
        menuGroupIcons = getResources().obtainTypedArray(R.array.title_icons);

        expListView = (ExpandableListView) findViewById(R.id.list_view);
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, menuGroupItems, menuChildItems);

        expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d("groupPosition -- id", "" + groupPosition + " " + id);
                boolean internetState = checkInternetConnection();
                Log.d("internetState", "" + internetState);

                switch (groupPosition) {
                    case 0:
                        try {
                            startNewActivity("Aktualnosci");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            startNewActivity("DzienniczekSwFaustyny");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        try {
                            startNewActivity("Galeria");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 7:
                        sendSMS(getString(R.string.koronka_phone_number), getString(R.string.sms_prompt));

                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.koronka_toast), Toast.LENGTH_LONG);
                        toast.show();
                        break;
                    case 8:
                        if (internetState){
                            openWebPage(getString(R.string.kontakt_wydawnictwo_sklep));
                        } else {
                            Log.d("Internet", "nie ma INTERNETOW");

                            DialogFragment newFragment = new NoInternetConnectionDialogFragment();
                            newFragment.show(getFragmentManager(),"dialog");
                        }
                        break;
                    case 9:
                        try {
                            startNewActivity("KwartalnikOredzieMilosierdzia");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 10:
                        if (internetState){
                            openWebPage(getString(R.string.url_facebook));
                        } else {
                            Log.d("Internet", "nie ma INTERNETOW");

                            DialogFragment newFragment = new NoInternetConnectionDialogFragment();
                            newFragment.show(getFragmentManager(),"dialog");
                        }
                        break;
                    case 11:
                        if (internetState){
                            openWebPage(getString(R.string.url_sdm));
                        } else {
                            Log.d("Internet", "nie ma INTERNETOW");

                            DialogFragment newFragment = new NoInternetConnectionDialogFragment();
                            newFragment.show(getFragmentManager(),"dialog");
                        }
                        break;
                    case 12:
                        if (internetState){
                            openWebPage(getString(R.string.url_yt));
                        } else {
                            Log.d("Internet", "nie ma INTERNETOW");

                            DialogFragment newFragment = new NoInternetConnectionDialogFragment();
                            newFragment.show(getFragmentManager(),"dialog");
                        }
                        break;
                    case 14:
                        if (internetState){
                            openWebPage(getString(R.string.url_faustyna));
                        } else {
                            Log.d("Internet", "nie ma INTERNETOW");

                            DialogFragment newFragment = new NoInternetConnectionDialogFragment();
                            newFragment.show(getFragmentManager(),"dialog");
                        }
                        break;
                }
                return false;
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                if (groupPosition == 2)
                    switch (childPosition) {
                        case 0:
                            try {
                                startNewActivity("Istota");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 1:
                            try {
                                startNewActivity("Obraz");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                startNewActivity("SwietoMilosierdzia");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                startNewActivity("KoronkaDoMilosierdziaBozego");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            try {
                                startNewActivity("GodzinaMilosierdzia");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 5:
                            try {
                                startNewActivity("SzerzenieCzciMilosierdzia");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                else if (groupPosition == 3)
                    switch (childPosition) {
                        case 0:
                            try {
                                startNewActivity("Biografia");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 1:
                            try {
                                startNewActivity("MisjaGloszeniaOredziaMilosierdzia");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                startNewActivity("SzkolaDuchowosci");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                startNewActivity("NoweZgromadzenie");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            try {
                                startNewActivity("LitaniaDoSwSiostryFaustyny");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 5:
                            try {
                                startNewActivity("ModlitwaPrzezPrzyczyneSwFaustyny");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                else if (groupPosition == 4)
                    switch (childPosition) {
                        case 0:
                            try {
                                startNewActivity("CudownyObraz");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 1:
                            try {
                                startNewActivity("Bazylika");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                startNewActivity("Kaplica");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                startNewActivity("PorzadekNabozenstw");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            try {
                                startNewActivity("OprowadzaniePielgrzymow");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 5:
                            try {
                                startNewActivity("Noclegi");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 6:
                            try {
                                startNewActivity("Kontakt");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 7:
                            try {
                                startNewActivity("Dojazd");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                else if (groupPosition == 6)
                    switch (childPosition) {
                        case 0:
                            try {
                                startNewActivity("OdsluchajKoronke");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 1:
                            try {
                                startNewActivity("Video");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                else if (groupPosition == 13)
                    switch (childPosition) {
                        case 0:
                            try {
                                startNewActivity("NieustannaKoronka");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 1:
                            try {
                                startNewActivity("RozbudowaSanktuarium");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                return false;
            }
        });
    }

    public boolean checkInternetConnection(){
        ConnectivityManager cm = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public void openWebPage(String url){
        Uri webPageAddress = Uri.parse(url);
        Intent openWebPageIntent = new Intent(Intent.ACTION_VIEW, webPageAddress);
        if (openWebPageIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(openWebPageIntent);
        }
    }

    public void sendSMS(String phoneNumber, String message){
        Intent sendSMSIntent = new Intent(Intent.ACTION_SENDTO);
        sendSMSIntent.setData(Uri.parse("smsto:" + phoneNumber));
        sendSMSIntent.putExtra("sms_body", message);
        if (sendSMSIntent.resolveActivity(getPackageManager()) != null){
            startActivity(sendSMSIntent);
        }
    }

    public void startNewActivity(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Log.d("startNewActivity", className);
        Class classTemp = Class.forName(getPackageName()+"."+className);
        Log.d("Klasy", className);
        Intent intent = new Intent(this, classTemp);
        startActivity(intent);
    }

    private void prepareListData() {
        menuGroupItems = new ArrayList<MenuGroupItem>();
        menuChildItems = new HashMap<MenuGroupItem, List<String>>();
        
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[0], menuGroupIcons.getResourceId(0, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[1], menuGroupIcons.getResourceId(1, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[2], menuGroupIcons.getResourceId(2, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[3], menuGroupIcons.getResourceId(3, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[4], menuGroupIcons.getResourceId(4, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[5], menuGroupIcons.getResourceId(5, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[6], menuGroupIcons.getResourceId(6, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[7], menuGroupIcons.getResourceId(7, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[8], menuGroupIcons.getResourceId(8, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[9], menuGroupIcons.getResourceId(9, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[10], menuGroupIcons.getResourceId(10, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[11], menuGroupIcons.getResourceId(11, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[12], menuGroupIcons.getResourceId(12, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[13], menuGroupIcons.getResourceId(13, -1)));
        menuGroupItems.add(new MenuGroupItem(menuGroupTitles[14], menuGroupIcons.getResourceId(14, -1)));

        menuGroupIcons.recycle();

        List<String> titleAktualnosci = new ArrayList<String>();
        List<String> titleDzienniczek = new ArrayList<String>();

        List<String> titleNabozenstwo = new ArrayList<String>();
        titleNabozenstwo.add(getString(R.string.title_nabozenstwo_istota));
        titleNabozenstwo.add(getString(R.string.title_nabozenstwo_obraz));
        titleNabozenstwo.add(getString(R.string.title_nabozenstwo_swieto));
        titleNabozenstwo.add(getString(R.string.title_nabozenstwo_koronka));
        titleNabozenstwo.add(getString(R.string.title_nabozenstwo_godzina));
        titleNabozenstwo.add(getString(R.string.title_nabozenstwo_szerzenie));

        List<String> titleFaustyna = new ArrayList<String>();
        titleFaustyna.add(getString(R.string.title_faustyna_biografia));
        titleFaustyna.add(getString(R.string.title_faustyna_misja));
        titleFaustyna.add(getString(R.string.title_faustyna_szkola));
        titleFaustyna.add(getString(R.string.title_faustyna_zgromadzenie));
        titleFaustyna.add(getString(R.string.title_faustyna_litania));
        titleFaustyna.add(getString(R.string.title_faustyna_modlitwa));

        List<String> titleSanktuarium = new ArrayList<String>();
        titleSanktuarium.add(getString(R.string.title_sanktuarium_obraz));
        titleSanktuarium.add(getString(R.string.title_sanktuarium_bazylika));
        titleSanktuarium.add(getString(R.string.title_sanktuarium_kaplica));
        titleSanktuarium.add(getString(R.string.title_sanktuarium_porzadek));
        titleSanktuarium.add(getString(R.string.title_sanktuarium_oprowadzanie));
        titleSanktuarium.add(getString(R.string.title_sanktuarium_noclegi));
        titleSanktuarium.add(getString(R.string.title_sanktuarium_kontakt));
        titleSanktuarium.add(getString(R.string.title_sanktuarium_dojazd));

        List<String> titleGaleria = new ArrayList<String>();

        List<String> titleMultimedia = new ArrayList<String>();
        titleMultimedia.add(getString(R.string.title_multimedia_koronka));
        titleMultimedia.add(getString(R.string.title_multimedia_video));

        List<String> titleKoronka = new ArrayList<String>();
        List<String> titleSklep = new ArrayList<String>();
        List<String> titleKwartalnik = new ArrayList<String>();
        List<String> titleFacebook = new ArrayList<String>();
        List<String> titleSDM = new ArrayList<String>();
        List<String> titleYt = new ArrayList<String>();

        List<String> titleWsparcie = new ArrayList<String>();
        titleWsparcie.add(getString(R.string.title_wsparcie_koronka));
        titleWsparcie.add(getString(R.string.title_wsparcie_rozbudowa));

        List<String> titleWiecej = new ArrayList<String>();

        menuChildItems.put(menuGroupItems.get(0), titleAktualnosci);
        menuChildItems.put(menuGroupItems.get(1), titleDzienniczek);
        menuChildItems.put(menuGroupItems.get(2), titleNabozenstwo);
        menuChildItems.put(menuGroupItems.get(3), titleFaustyna);
        menuChildItems.put(menuGroupItems.get(4), titleSanktuarium);
        menuChildItems.put(menuGroupItems.get(5), titleGaleria);
        menuChildItems.put(menuGroupItems.get(6), titleMultimedia);
        menuChildItems.put(menuGroupItems.get(7), titleKoronka);
        menuChildItems.put(menuGroupItems.get(8), titleSklep);
        menuChildItems.put(menuGroupItems.get(9), titleKwartalnik);
        menuChildItems.put(menuGroupItems.get(10), titleFacebook);
        menuChildItems.put(menuGroupItems.get(11), titleSDM);
        menuChildItems.put(menuGroupItems.get(12), titleYt);
        menuChildItems.put(menuGroupItems.get(13), titleWsparcie);
        menuChildItems.put(menuGroupItems.get(14), titleWiecej);
    }
}
