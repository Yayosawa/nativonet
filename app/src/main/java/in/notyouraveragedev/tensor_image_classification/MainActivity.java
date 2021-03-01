package in.notyouraveragedev.tensor_image_classification;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import in.notyouraveragedev.tensor_image_classification.classifier.ImageClassifier;

/**
 * The Main Activity Class
 * <p>
 * Created by A Anand on 11-05-2020
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Requests Codes to identify camera and permission requests
     */
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1000;
    private static final int CAMERA_REQEUST_CODE = 10001;
    Uri imageuri;
    private Bitmap bitmap;

    /**
     * UI Elements
     */
    private ImageView imageView;
    private ListView listView;
    private ImageClassifier imageClassifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initalizing ui elements
        initializeUIElements();
    }

/*    public void iniciarFichaActivity(View view) {
        Intent auxFichaAct = new Intent(this, FichaActivity.class);
        startActivity(auxFichaAct);
    }*/

    /**
     * Method to initalize UI Elements. this method adds the on click
     */
    private void initializeUIElements() {
        //imageView = findViewById(R.id.iv_capture);
        //listView = findViewById(R.id.lv_probabilities);
        Button takepicture = findViewById(R.id.bt_take_picture);
        Button btoGaleria = findViewById(R.id.btoSelectImg);

        /*
         * Creating an instance of our tensor image classifier
         */
        try {
            imageClassifier = new ImageClassifier(this);
        } catch (IOException e) {
            Log.e("Image Classifier Error", "ERROR: " + e);
        }

        ///////////
        btoGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),12);
            }
        });
        //////////

        // adding on click listener to button
        takepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking whether camera permissions are available.
                // if permission is avaialble then we open camera intent to get picture
                // otherwise reqeusts for permissions
                if (hasPermission()) {
                    openCamera();
                } else {
                    requestPermission();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // if this is the result of our camera image request
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 12 && resultCode == RESULT_OK && data != null) {

                imageuri = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
                new ImageSaver(this).
                        setFileName("myImagegal.png").
                        setDirectoryName("images").
                        save(bitmap);


                // pass this bitmap to classifier to make prediction
                List<ImageClassifier.Recognition> predicitons = imageClassifier.recognizeImageBagging(
                        bitmap, 0);

                //////////////////////////////
                List<String> predLabelIntList = new ArrayList<>();
                for (ImageClassifier.Recognition rec : predicitons) {
                    predLabelIntList.add(rec.getName());
                }

                List<Float> predProbList = new ArrayList<>();
                for (ImageClassifier.Recognition rec2 : predicitons) {
                    predProbList.add(rec2.getConfidence());
                }

                /////////////////////////////


                //Enviar ArrayList de id(clase/especie) y probabilidad a activity ficha
                Intent fichaAct = new Intent(this, FichaActivity.class);
                fichaAct.putExtra("labelList", (Serializable) predLabelIntList);
                fichaAct.putExtra("probList", (Serializable) predProbList);
                startActivity(fichaAct);

            }
            if (requestCode == CAMERA_REQEUST_CODE) {
                // getting bitmap of the image

                Bitmap photo = (Bitmap) Objects.requireNonNull(Objects.requireNonNull(data).getExtras()).get("data");

                new ImageSaver(this).
                        setFileName("myImagegal.png").
                        setDirectoryName("images").
                        save(photo);

                // displaying this bitmap in imageview
                //imageView.setImageBitmap(photo);

                // pass this bitmap to classifier to make prediction
                List<ImageClassifier.Recognition> predicitons = imageClassifier.recognizeImageBagging(
                        photo, 0);

                // creating a list of string to display in list view
                //final List<String> predicitonsList = new ArrayList<>();
                //for (ImageClassifier.Recognition recog : predicitons) {
                //    predicitonsList.add(recog.getName() + ":  " + recog.getConfidence());
                //}

                // creating an array adapter to display the classification result in list view
                //ArrayAdapter<String> predictionsAdapter = new ArrayAdapter<>(
                //       this, R.layout.support_simple_spinner_dropdown_item, predicitonsList);

                //////////////////////////////
                List<String> predLabelIntList = new ArrayList<>();
                for (ImageClassifier.Recognition rec : predicitons) {
                    predLabelIntList.add(rec.getName());
                }

                List<Float> predProbList = new ArrayList<>();
                for (ImageClassifier.Recognition rec2 : predicitons) {
                    predProbList.add(rec2.getConfidence());
                }

                /////////////////////////////
                //listView.setAdapter(predictionsAdapter);

                //Enviar ArrayList de id(clase/especie) y probabilidad a activity ficha
                Intent fichaAct = new Intent(this, FichaActivity.class);
                fichaAct.putExtra("labelList", (Serializable) predLabelIntList);
                fichaAct.putExtra("probList", (Serializable) predProbList);
                startActivity(fichaAct);

            }
            super.onActivityResult(requestCode, resultCode, data);
        }
        catch(Exception ex){mensaje(ex.getMessage());}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // if this is the result of our camera permission request
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (hasAllPermissions(grantResults)) {
                openCamera();
            } else {
                requestPermission();
            }
        }
    }

    /**
     * checks whether all the needed permissions have been granted or not
     *
     * @param grantResults the permission grant results
     * @return true if all the reqested permission has been granted,
     * otherwise returns false
     */
    private boolean hasAllPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED)
                return false;
        }
        return true;
    }

    /**
     * Method requests for permission if the android version is marshmallow or above
     */
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // whether permission can be requested or on not
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Camera Permission Required", Toast.LENGTH_SHORT).show();
            }
            // request the camera permission permission
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * creates and starts an intent to get a picture from camera
     */
    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQEUST_CODE);
    }

    /**
     * checks whether camera permission is available or not
     *
     * @return true if android version is less than marshmallo,
     * otherwise returns whether camera permission has been granted or not
     */
    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    public void mensaje(String auxMensaje){
        Toast.makeText(this, auxMensaje, Toast.LENGTH_LONG).show();
    }

    private void saveImage(Bitmap bitmap, Context context, String folderName) throws FileNotFoundException {
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + folderName);
            values.put(MediaStore.Images.Media.IS_PENDING, true);
            // RELATIVE_PATH and IS_PENDING are introduced in API 29.

            Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            if (uri != null) {
                saveImageToStream(bitmap, context.getContentResolver().openOutputStream(uri));
                values.put(MediaStore.Images.Media.IS_PENDING, false);
                context.getContentResolver().update(uri, values, null, null);
            }
        } else {
            File dir = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),"");
            // getExternalStorageDirectory is deprecated in API 29

            if (!dir.exists()) {
                dir.mkdirs();
            }

            java.util.Date date = new java.util.Date();
            File imageFile = new File(dir.getAbsolutePath()
                    + File.separator
                    + new Timestamp(date.getTime()).toString()
                    + "Image.jpg");

            imageFile = new File(dir.getAbsolutePath()
                    + File.separator
                    + new Timestamp(date.getTime()).toString()
                    + "Image.jpg");
            saveImageToStream(bitmap, new FileOutputStream(imageFile));
            if (imageFile.getAbsolutePath() != null) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, imageFile.getAbsolutePath());
                // .DATA is deprecated in API 29
                context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            }
        }
    }

    private ContentValues contentValues() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        return values;
    }

    private void saveImageToStream(Bitmap bitmap, OutputStream outputStream) {
        if (outputStream != null) {
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
