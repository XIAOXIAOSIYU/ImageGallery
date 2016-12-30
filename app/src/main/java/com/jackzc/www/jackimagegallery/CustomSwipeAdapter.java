package com.jackzc.www.jackimagegallery;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomSwipeAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private TypedArray image_resource;

    public CustomSwipeAdapter(Context _context) {
        this.context = _context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.image_resource = context.getResources().obtainTypedArray(R.array.image_ids);
    }

    @Override
    public int getCount() {
        return image_resource.length();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (FrameLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View item_view = inflater.inflate(R.layout.swipe_layout, container, false);
        ImageView image = (ImageView) item_view.findViewById(R.id.image_view);
        TextView text = (TextView) item_view.findViewById(R.id.image_count);

        image.setImageResource(image_resource.getResourceId(position, 0));
        text.setText("Image : " + position + " / "+ image_resource.length());

        container.addView(item_view);
        return item_view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
