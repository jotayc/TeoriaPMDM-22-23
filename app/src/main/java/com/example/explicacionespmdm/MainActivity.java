package com.example.explicacionespmdm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.util.Output;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Permission;

/**
 * Actividad Cámara con permisos para nuevas versiones
 * @author JC
 * @version 0.1
 *
 * Consideraciones previas:
 * 1. Permisos especiales según las versiones de Android de los dispositivos
 * 2. Según las versiones la misma acción se debe hacer de forma diferente.
 *
 */
public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_PERMISSION_CAMERA = 100;
    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 101;
    private Button btnCamera;
    private Button btnSave;
    private ImageView imgView;

    private Bitmap bitmap;

    public static final int CODE_TAKE_PICTURE = 1;
    private static final int CODE_SAVE_PICTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = findViewById(R.id.btnCam);
        btnSave = findViewById(R.id.btnSave);
        imgView = findViewById(R.id.imageView);


        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               checkPermissionCamera();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissionStorage();
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CAMERA:
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                }else{
                    Toast.makeText(this, "No tienes acceso a la cámara!!", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_PERMISSION_WRITE_STORAGE:
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    savePicture();
                }else{
                    Toast.makeText(this, "No tienes acceso de escritura en disco!!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void checkPermissionCamera() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                takePicture();
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
            }
        }else{
            takePicture();
        }
    }

    private void checkPermissionStorage() {
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.R)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    savePicture();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE_STORAGE);
                }
            } else {
                savePicture();
            }

    }

    public void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, CODE_TAKE_PICTURE);
        }
    }


    public void savePicture(){
        OutputStream fos = null;
        File file = null;


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            ContentResolver resolver = getContentResolver();
            ContentValues values = new ContentValues();

            String fileName = System.currentTimeMillis()+"image_example";

            values.put(MediaStore.Images.Media.DISPLAY_NAME,fileName);
            values.put(MediaStore.Images.Media.MIME_TYPE,"image/jpg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH,"Picture/MyApp");
            values.put(MediaStore.Images.Media.IS_PENDING,1);

            Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
            Uri imageUri = resolver.insert(collection,values);

            try {
                fos = resolver.openOutputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            values.clear();
            values.put(MediaStore.Images.Media.IS_PENDING,0);
            resolver.update(imageUri,values,null,null);

        }else{
            String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            String fileName = System.currentTimeMillis() + ".jpg";

            file = new File(imageDir, fileName);

            try {
                fos = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        boolean saved = bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);


        if(saved){
            Toast.makeText(this, "Foto guardada!!", Toast.LENGTH_SHORT).show();
        }

        if(fos != null){

            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(file != null){
            MediaScannerConnection.scanFile(this,new String[]{file.toString()},null,null);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_TAKE_PICTURE:
                if (data != null && resultCode == RESULT_OK) {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    imgView.setImageBitmap(bitmap);
                }
                break;
        }
    }
}