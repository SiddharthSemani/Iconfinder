package com.example.icon.ui.selectedicon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icon.R;
import com.squareup.picasso.Picasso;

public class SelectedIconActivity extends AppCompatActivity {
    private  String prevUrl,name,price,currency;
    private Boolean isPremium;
    private Bitmap bitmap;
    public static final int RUNTIME_PERMISSION_CODE = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon);

        AndroidRuntimePermission();

        ImageView imageView=findViewById(R.id.imageView);
        ImageView dowImg=findViewById(R.id.download);
        ImageView premiumImg=findViewById(R.id.premium);
        TextView iconName = findViewById(R.id.name);
        TextView priceView=findViewById(R.id.price);
        Bundle extras=getIntent().getExtras();
        if (extras!=null){
            prevUrl = extras.getString("prevUrl");
            name = extras.getString("name");
            price=extras.getString("price");
            isPremium=extras.getBoolean("isPremium");
            currency=extras.getString("currency");
            if (isPremium==false){
                dowImg.setVisibility(View.VISIBLE);
                premiumImg.setVisibility(View.GONE);
                priceView.setVisibility(View.GONE);
            }
            else {
                dowImg.setVisibility(View.GONE);
                priceView.setVisibility(View.VISIBLE);
                premiumImg.setVisibility(View.VISIBLE);
                if (currency.equals("USD")){
                    priceView.setText("$"+price);
                }else {priceView.setText("₹"+price);}
            }
            iconName.setText(name);
            Picasso.get().load(prevUrl).into(imageView);
        }
        dowImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Download();
            }
        });

    }


    public void AndroidRuntimePermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(SelectedIconActivity.this);
                    alert_builder.setMessage("External Storage Permission is Required.");
                    alert_builder.setTitle("Please Grant Permission.");
                    alert_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ActivityCompat.requestPermissions(
                                    SelectedIconActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    RUNTIME_PERMISSION_CODE

                            );
                        }
                    });

                    alert_builder.setNeutralButton("Cancel", null);

                    AlertDialog dialog = alert_builder.create();

                    dialog.show();

                } else {

                    ActivityCompat.requestPermissions(
                            SelectedIconActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            RUNTIME_PERMISSION_CODE
                    );
                }
            } else {

            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(SelectedIconActivity.this);
                    alert_builder.setMessage("External Storage Permission is Required.");
                    alert_builder.setTitle("Please Grant Permission.");
                    alert_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ActivityCompat.requestPermissions(
                                    SelectedIconActivity.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    RUNTIME_PERMISSION_CODE

                            );
                        }
                    });

                    alert_builder.setNeutralButton("Cancel", null);

                    AlertDialog dialog = alert_builder.create();

                    dialog.show();

                } else {

                    ActivityCompat.requestPermissions(
                            SelectedIconActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            RUNTIME_PERMISSION_CODE
                    );
                }
            } else {

            }
        }
    }
     public void Download(){
         DownloadManager.Request request=new DownloadManager.Request(Uri.parse(prevUrl));
         request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI|DownloadManager.Request.NETWORK_MOBILE);
         request.setTitle(name);
         request.setDescription("Downloading...");

         request.allowScanningByMediaScanner();
         request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
         request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis());

         DownloadManager manager= (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
         manager.enqueue(request);
     }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case RUNTIME_PERMISSION_CODE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
            }
        }
    }

}

