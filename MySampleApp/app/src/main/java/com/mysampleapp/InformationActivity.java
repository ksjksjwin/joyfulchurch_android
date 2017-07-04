package com.mysampleapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;


public class InformationActivity extends AppCompatActivity implements View.OnClickListener{
    Button button;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        ab.setTitle(Html.fromHtml("<font color='#FFFFFF'>교회 소개</font>"));

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        button = (Button) findViewById(R.id.button_map);
        button.setOnClickListener(InformationActivity.this);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(InformationActivity.this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        String uri = "http://maps.google.com/maps?saddr=" + "&daddr=" + 37.695989 + "," + -121.972717;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

}
