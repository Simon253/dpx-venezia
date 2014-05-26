package com.spottechindustrial.carpool.android.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spottechindustrial.carpool.android.R;
import com.spottechindustrial.carpool.android.model.NavDrawerItem;

public class NavDrawerListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
            viewHolder.imageIcon = (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.textTitle = (TextView) convertView.findViewById(R.id.title);
            viewHolder.textCount = (TextView) convertView.findViewById(R.id.counter);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageIcon.setImageResource(navDrawerItems.get(position).getIcon());
        viewHolder.textTitle.setText(navDrawerItems.get(position).getTitle());

        // displaying count
        // check whether it set visible or not
        if(navDrawerItems.get(position).getCounterVisibility()){
            viewHolder.textCount.setText(navDrawerItems.get(position).getCount());
        }else{
            // hide the counter view
            viewHolder.textCount.setVisibility(View.GONE);
        }
        return convertView;
    }

    private class ViewHolder {
        private ImageView imageIcon;
        private TextView textTitle;
        private TextView textCount;
    }
}
