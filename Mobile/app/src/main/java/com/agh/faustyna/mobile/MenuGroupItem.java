package com.agh.faustyna.mobile;

/**
 * Created by Karolina on 2015-11-25.
 */
public class MenuGroupItem {

    private String title;
    private int icon;

    public MenuGroupItem(){}

    public MenuGroupItem(String title, int icon){
        this.title = title;
        this.icon = icon;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIcon(){
        return this.icon;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }
}
