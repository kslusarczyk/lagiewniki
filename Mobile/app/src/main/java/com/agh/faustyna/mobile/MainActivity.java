package com.agh.faustyna.mobile;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
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

    private String[] nabozenstwoClasses;
    private String[] faustynaClasses;
    private String[] sanktuariumClasses;
    private String[] multimediaClasses;
    private String[] wsparcieClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuGroupTitles = getResources().getStringArray(R.array.title_group_items);
        menuGroupIcons = getResources().obtainTypedArray(R.array.title_icons);

        nabozenstwoClasses = getResources().getStringArray(R.array.nabozenstwo_classes);
        faustynaClasses = getResources().getStringArray(R.array.faustyna_classes);
        sanktuariumClasses = getResources().getStringArray(R.array.sanktuarium_classes);
        multimediaClasses = getResources().getStringArray(R.array.multimedia_classes);
        wsparcieClasses = getResources().getStringArray(R.array.wsparcie_classes);

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
                            newFragment.show(getFragmentManager(), "dialog");
                        }
                        break;
                    case 9:
                        if (internetState){
                            openWebPage(getString(R.string.url_facebook));
                        } else {
                            Log.d("Internet", "nie ma INTERNETOW");

                            DialogFragment newFragment = new NoInternetConnectionDialogFragment();
                            newFragment.show(getFragmentManager(),"dialog");
                        }
                        break;
                    case 10:
                        if (internetState){
                            openWebPage(getString(R.string.url_sdm));
                        } else {
                            Log.d("Internet", "nie ma INTERNETOW");

                            DialogFragment newFragment = new NoInternetConnectionDialogFragment();
                            newFragment.show(getFragmentManager(),"dialog");
                        }
                        break;
                    case 11:
                        if (internetState){
                            openWebPage(getString(R.string.url_yt));
                        } else {
                            Log.d("Internet", "nie ma INTERNETOW");

                            DialogFragment newFragment = new NoInternetConnectionDialogFragment();
                            newFragment.show(getFragmentManager(),"dialog");
                        }
                        break;
                    case 13:
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
            boolean internetState = checkInternetConnection();

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                if (groupPosition == 2)
                    try {
                        startNewActivity(nabozenstwoClasses[childPosition]);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                else if (groupPosition == 3)
                    try {
                        startNewActivity(faustynaClasses[childPosition]);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                else if (groupPosition == 4)
                    if (childPosition == 7) {
                        try {
                            final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                                  DialogFragment newFragment = new NoGpsSourceDialogFragment();
//                                  newFragment.show(getFragmentManager(), "dialog");
                                buildAlertMsgNoGps();
                            } else {
                                startNewActivity("Dojazd");
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else try {
                        startNewActivity(sanktuariumClasses[childPosition]);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                else if (groupPosition == 6)
                    try {
                        startNewActivity(multimediaClasses[childPosition]);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                else if (groupPosition == 12)
                    try {
                        startNewActivity(wsparcieClasses[childPosition]);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
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
        Class classTemp = Class.forName(getPackageName() + "." + className);
        Log.d("Klasy", className);
        Intent intent = new Intent(this, classTemp);
        startActivity(intent);
    }

    private void buildAlertMsgNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.gps_dialog_message))
                .setTitle(getString(R.string.gps_dialog_title))
                .setPositiveButton(getString(R.string.dialog_positive_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openLocationSettings();
                    }
                })

                .setNegativeButton(getString(R.string.dialog_negative_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("setNegativeButton", "ignoruj");
                        try {
                            startNewActivity("Dojazd");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void openLocationSettings(){
        Intent openLocationSettingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        if (openLocationSettingsIntent.resolveActivity(this.getPackageManager()) != null){
            startActivity(openLocationSettingsIntent);
        }
    }
    private void prepareListData() {
        menuGroupItems = new ArrayList<MenuGroupItem>();
        menuChildItems = new HashMap<MenuGroupItem, List<String>>();

        for (int i=0; i<menuGroupTitles.length; i++) {
            menuGroupItems.add(new MenuGroupItem(menuGroupTitles[i], menuGroupIcons.getResourceId(i, -1)));
        }

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
        List<String> titleFacebook = new ArrayList<String>();
        List<String> titleSDM = new ArrayList<String>();
        List<String> titleYt = new ArrayList<String>();

        List<String> titleWsparcie = new ArrayList<String>();
        titleWsparcie.add(getString(R.string.title_wsparcie_dary_na_rzecz_milosierdzia));
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
        menuChildItems.put(menuGroupItems.get(9), titleFacebook);
        menuChildItems.put(menuGroupItems.get(10), titleSDM);
        menuChildItems.put(menuGroupItems.get(11), titleYt);
        menuChildItems.put(menuGroupItems.get(12), titleWsparcie);
        menuChildItems.put(menuGroupItems.get(13), titleWiecej);
    }
}
