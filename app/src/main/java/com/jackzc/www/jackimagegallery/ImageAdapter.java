package com.jackzc.www.jackimagegallery;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ImageItem> data = new ArrayList<>();

    public ImageAdapter(Context _context, int _resource, ArrayList _data) {
        super(_context, _resource, _data);

        this.context = _context;
        this.layoutResourceId = _resource;
        this.data = _data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) view.findViewById(R.id.item_text);
            holder.imageView = (ImageView) view.findViewById(R.id.item_image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ImageItem item = data.get(position);
        holder.imageTitle.setText(item.getTitle());
        holder.imageView.setImageBitmap(item.getImage());

        return view;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView imageView;
    }

}
