package com.jackzc.www.jackimagegallery;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageAdapter = new ImageAdapter(this, R.layout.content_imagegallery_item_layout, getData());
        gridView = (GridView) findViewById(R.id.gv_images);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageItem item = (ImageItem) adapterView.getItemAtPosition(i);
                Bitmap image = item.getImage();
                String path = getImageUri(getApplicationContext(),image);

                Intent intent = new Intent(MainActivity.this, details.class);
                intent.putExtra("Title", item.getTitle());
                intent.putExtra("Image", path);
                intent.putExtra("ImagePosition", i);
                startActivity(intent);
                finish();
            }
        });

    }

    private ArrayList<ImageItem> getData() {

        final ArrayList<ImageItem> items = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        int image_count = imgs.length();

        for (int i = 0; i < image_count; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            items.add(new ImageItem(bitmap, "Image#" + i));
        }

        return items;
    }

    public String getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return path;
    }

}
