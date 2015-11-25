package com.agh.faustyna.mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Karolina on 2015-11-05.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<MenuGroupItem> menuGroupItems;
    private HashMap<MenuGroupItem, List<String>> menuChildItems;

    public ExpandableListAdapter(Context context, ArrayList<MenuGroupItem> menuGroupItems,
                                 HashMap<MenuGroupItem, List<String>> menuChildItems) {
        this.context = context;
        this.menuGroupItems = menuGroupItems;
        this.menuChildItems = menuChildItems;
    }

    @Override
    public int getGroupCount() {
        return this.menuGroupItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.menuChildItems.get(this.menuGroupItems.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.menuGroupItems.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.menuChildItems.get(this.menuGroupItems.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layInflater.inflate(R.layout.list_group, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

        imgIcon.setImageResource(menuGroupItems.get(groupPosition).getIcon());
        txtTitle.setText(menuGroupItems.get(groupPosition).getTitle());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater layInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.childItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
