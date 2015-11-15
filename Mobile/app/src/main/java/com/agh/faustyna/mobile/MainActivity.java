package com.agh.faustyna.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String groupName;
                String preparedGroupString;

                groupName = listDataHeader.get(groupPosition);
                preparedGroupString = prepareToCreateClass(groupName);

                try {
                    startNewActivity(preparedGroupString);
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

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String sectionName;
                String preparedSectionString;

                // only for testing
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();

                sectionName = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                preparedSectionString = prepareToCreateClass(sectionName);

                try {
                    startNewActivity(preparedSectionString);
                } catch (ClassNotFoundException e) {
                    Log.d("karolina", "ClassNotFoundException");
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

    public String prepareToCreateClass(String labelName) {
        Log.d("prepareToBefore", labelName);

        labelName = labelName.replaceAll("[Ąą]", "a");
        labelName = labelName.replaceAll("[Ćć]", "c");
        labelName = labelName.replaceAll("[Ęę]", "e");
        labelName = labelName.replaceAll("[Łł]", "l");
        labelName = labelName.replaceAll("[Ńń]", "n");
        labelName = labelName.replaceAll("[Śś]", "s");
        labelName = labelName.replaceAll("[Óó]", "o");
        labelName = labelName.replaceAll("[ŹŻźż]", "z");
        labelName = labelName.replaceAll("\\-", " ");
        labelName = labelName.replaceAll("\\.", "");
        labelName = WordUtils.capitalizeFully(labelName);
        labelName = labelName.replaceAll("\\s+", "");
        Log.d("prepareToAfter", labelName);
        return labelName;
    }

    public void startNewActivity(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Log.d("startNewActivity", className);
        Class classTemp = Class.forName(getPackageName()+"."+className);
        Log.d("Klasy", className);
        Intent intent = new Intent(this, classTemp);
        startActivity(intent);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add(getString(R.string.titleAktualnosci));
        listDataHeader.add(getString(R.string.titleDzienniczek));
        listDataHeader.add(getString(R.string.titleNabozenstwo));
        listDataHeader.add(getString(R.string.titleFaustyna));
        listDataHeader.add(getString(R.string.titleSanktuarium));
        listDataHeader.add(getString(R.string.titleGaleria));
        listDataHeader.add(getString(R.string.titleTransmisja));
        listDataHeader.add(getString(R.string.titleKoronka));
        listDataHeader.add(getString(R.string.titleSklep));
        listDataHeader.add(getString(R.string.titleKwartalnik));
        listDataHeader.add(getString(R.string.titleFacebook));
        listDataHeader.add(getString(R.string.titleSDM));
        listDataHeader.add(getString(R.string.titleYt));
        listDataHeader.add(getString(R.string.titleWsparcie));
        listDataHeader.add(getString(R.string.titleWiecej));

        // Adding child data
        List<String> titleAktualnosci = new ArrayList<String>();

        List<String> titleDzienniczek = new ArrayList<String>();

        List<String> titleNabozenstwo = new ArrayList<String>();
        titleNabozenstwo.add(getString(R.string.titleNabozenstwoIstota));
        titleNabozenstwo.add(getString(R.string.titleNabozenstwoObraz));
        titleNabozenstwo.add(getString(R.string.titleNabozenstwoSwieto));
        titleNabozenstwo.add(getString(R.string.titleNabozenstwoKoronka));
        titleNabozenstwo.add(getString(R.string.titleNabozenstwoGodzina));
        titleNabozenstwo.add(getString(R.string.titleNabozenstwoSzerzenie));

        List<String> titleFaustyna = new ArrayList<String>();
        titleFaustyna.add(getString(R.string.titleFaustynaBiografia));
        titleFaustyna.add(getString(R.string.titleFaustynaMisja));
        titleFaustyna.add(getString(R.string.titleFaustynaSzkola));
        titleFaustyna.add(getString(R.string.titleFaustynaZgromadzenie));
        titleFaustyna.add(getString(R.string.titleFaustynaLitania));
        titleFaustyna.add(getString(R.string.titleFaustynaModlitwa));

        List<String> titleSanktuarium = new ArrayList<String>();
        titleSanktuarium.add(getString(R.string.titleSanktuariumObraz));
        titleSanktuarium.add(getString(R.string.titleSanktuariumBazylika));
        titleSanktuarium.add(getString(R.string.titleSanktuariumKaplica));
        titleSanktuarium.add(getString(R.string.titleSanktuariumPorzadek));
        titleSanktuarium.add(getString(R.string.titleSanktuariumOprowadzanie));
        titleSanktuarium.add(getString(R.string.titleSanktuariumNoclegi));
        titleSanktuarium.add(getString(R.string.titleSanktuariumKontakt));
        titleSanktuarium.add(getString(R.string.titleSanktuariumDojazd));

        List<String> titleGaleria = new ArrayList<String>();

        List<String> titleTransmisja = new ArrayList<String>();
        titleTransmisja.add(getString(R.string.titleTransmisjaAudio));
        titleTransmisja.add(getString(R.string.titleTransmisjaVideo));

        List<String> titleKoronka = new ArrayList<String>();
        List<String> titleSklep = new ArrayList<String>();
        List<String> titleKwartalnik = new ArrayList<String>();
        List<String> titleFacebook = new ArrayList<String>();
        List<String> titleSDM = new ArrayList<String>();
        List<String> titleYt = new ArrayList<String>();

        List<String> titleWsparcie = new ArrayList<String>();
        titleWsparcie.add(getString(R.string.titleWsparcieKoronka));
        titleWsparcie.add(getString(R.string.titleWsparcieRozbudowa));

        List<String> titleWiecej = new ArrayList<String>();

        listDataChild.put(listDataHeader.get(0), titleAktualnosci); // Header, Child data
        listDataChild.put(listDataHeader.get(1), titleDzienniczek);
        listDataChild.put(listDataHeader.get(2), titleNabozenstwo);
        listDataChild.put(listDataHeader.get(3), titleFaustyna);
        listDataChild.put(listDataHeader.get(4), titleSanktuarium);
        listDataChild.put(listDataHeader.get(5), titleGaleria);
        listDataChild.put(listDataHeader.get(6), titleTransmisja);
        listDataChild.put(listDataHeader.get(7), titleKoronka);
        listDataChild.put(listDataHeader.get(8), titleSklep);
        listDataChild.put(listDataHeader.get(9), titleKwartalnik);
        listDataChild.put(listDataHeader.get(10), titleFacebook);
        listDataChild.put(listDataHeader.get(11), titleSDM);
        listDataChild.put(listDataHeader.get(12), titleYt);
        listDataChild.put(listDataHeader.get(13), titleWsparcie);
        listDataChild.put(listDataHeader.get(14), titleWiecej);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
