package in.notyouraveragedev.tensor_image_classification;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.Serializable;

public class ImageZoomActivity extends AppCompatActivity {
    String rutaImagen;
    String tipoFoto;
    ImageView auxImgViewZoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_zoom);

        rutaImagen =  (String) getIntent().getSerializableExtra("path");
        tipoFoto = (String) getIntent().getSerializableExtra("tipo");

        auxImgViewZoom = findViewById(R.id.imgViewZoom);
        System.out.println("fffffffffffffffffffffffffffffffffffffffff");
        System.out.println(rutaImagen);
        System.out.println(tipoFoto);

        if(Integer.valueOf(tipoFoto) == 0){
            Bitmap bitmap = new ImageSaver(this).
                    setFileName(rutaImagen).
                    setDirectoryName("images").
                    load();
            auxImgViewZoom.setImageBitmap(bitmap);
        }//if
        else{
            int imageResource = getResources().getIdentifier(rutaImagen, null, getPackageName());
            Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
            auxImgViewZoom.setImageDrawable(imagen);
        }
    }

    public void mensaje(String auxMensaje) {
        Toast.makeText(this, auxMensaje, Toast.LENGTH_LONG).show();
    }
}