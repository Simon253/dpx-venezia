package com.spottechindustrial.carpool.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.spottechindustrial.carpool.android.model.Pool;

public class VeneziaListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Pool> mPool;

    private LayoutInflater mInflater;

    public VeneziaListViewAdapter(final Context context, final List<Pool> pool) {
        mContext = context;
        mPool = pool;

        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (null == mPool) {
            return 0;
        } else {
            return mPool.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (null == mPool) {
            return null;
        } else {
            return mPool.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        return null;
    }

}
