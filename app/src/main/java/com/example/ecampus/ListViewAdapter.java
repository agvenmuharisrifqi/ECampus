package com.example.ecampus;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    public ArrayList<Model> biodataList;
    Activity activity;

    public ListViewAdapter(Activity activity, ArrayList<Model> biodataList) {
        super();
        this.activity = activity;
        this.biodataList = biodataList;
    }

    @Override
    public int getCount() {
        return biodataList.size();
    }

    @Override
    public Object getItem(int position) {
        return biodataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView mNim;
        TextView mNama;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view, null);
            holder = new ViewHolder();
            holder.mNim = (TextView) convertView.findViewById(R.id.mNim);
            holder.mNama = (TextView) convertView.findViewById(R.id.mNama);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Model item = biodataList.get(position);
        holder.mNim.setText(item.getNim().toString());
        holder.mNama.setText(item.getNama().toString());

        return convertView;
    }
}
