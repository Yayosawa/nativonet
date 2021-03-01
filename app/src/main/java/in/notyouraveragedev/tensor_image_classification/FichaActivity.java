package in.notyouraveragedev.tensor_image_classification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import in.notyouraveragedev.tensor_image_classification.ClasesSQLite.Especie;
import in.notyouraveragedev.tensor_image_classification.Mantenedores.MantenedorEspecie;

public class FichaActivity extends AppCompatActivity {
    ArrayList<String> labelList;
    ArrayList<Float> probList;
    CircleImageView imgViewPred;
    CircleImageView imgViewUser;

    Integer id;
    String uri;

    TextView nomCient;
    TextView nomComun;
    TextView estbioGeo;
    TextView estConservacion;
    TextView distribucion;
    TextView descripcion;
    Button auxBtoConfirmar;
    TextView auxNext1;
    TextView auxNext2;
    Spinner auxSpinner;
    ScrollView auxScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);

        auxSpinner = findViewById(R.id.spinnerEspeciesFichaAct);
        // Lista con categorias para mostrar en combobox/spinner
        MantenedorEspecie auxMantenedorEspecie = new MantenedorEspecie(this);
        List<String> opciones = auxMantenedorEspecie.consultaEspecies();
        //
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        auxSpinner.setAdapter(adapter);

        labelList = (ArrayList<String>) getIntent().getSerializableExtra("labelList");
        probList = (ArrayList<Float>) getIntent().getSerializableExtra("probList");
        imgViewPred = findViewById(R.id.imgViewFichaAct);
        imgViewUser = findViewById(R.id.imgViewUsarFichaAct); //
        auxBtoConfirmar = findViewById(R.id.btoConfirmaEspeciePred);
        auxNext1 = findViewById(R.id.txtViewNext1FichaAct);
        auxNext2 = findViewById(R.id.txtViewNext2FichaAct);
        auxScrollView = findViewById(R.id.scrollView2FichaAct);
        id = 0;
        nomCient = findViewById(R.id.textViewNomCient);
        nomComun = findViewById(R.id.textViewNomComun);
        estbioGeo = findViewById(R.id.textViewEstBioGeo);
        estConservacion = findViewById(R.id.textViewConservacion);
        distribucion = findViewById(R.id.textViewDistribucion);
        descripcion = findViewById(R.id.textViewDesc);
        String uniqueID = UUID.randomUUID().toString();
        System.out.println("dddddddddddddddddddddddddddddddddddddddddd");
        System.out.println(uniqueID);
        System.out.println("dddddddddddddddddddddddddddddddddddddddddd");
//        // mostrar imagen
        //imgView.setImageResource(R.drawable.id_5);
        uri = "@drawable/id_"+ labelList.get(id);
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        imgViewPred.setImageDrawable(imagen);
        //imgViewUser.setImageDrawable(imagen); // esta tiene que ser la que el usuario captura
        Bitmap bitmap = new ImageSaver(this).
                setFileName("myImagegal.png").
                setDirectoryName("images").
                load();
        imgViewUser.setImageBitmap(bitmap);
//
//        // mostrar en listview datos de especie
//        ArrayAdapter<String> adaptRespuesta = buscarEspecie(id);//
//
        buscarEspecie(id);




    } // Fin onCreate



    public void mensaje(String auxMensaje) {
        Toast.makeText(this, auxMensaje, Toast.LENGTH_LONG).show();
    }

    public void volverMainActivity(View view) {
        Intent auxMainAct = new Intent(this, MainActivity.class);
        startActivity(auxMainAct);
    }

    public void buscarEspecie(int id) {
        try {

            MantenedorEspecie auxMantenedorEspecie = new MantenedorEspecie(this);
            Especie auxEspecieRespuesta = auxMantenedorEspecie.buscaEspecie(labelList.get(id));

            //this.mensaje("Se ha encontrado la especie !");

            String auxNomCient = auxEspecieRespuesta.getNomCientEspecie();
            String auxNomComun = auxEspecieRespuesta.getNomComunEspecie();
            String auxIdEstadoBio = auxEspecieRespuesta.getIdEstadoBio();
            String auxIdConservacion = auxEspecieRespuesta.getIdConservacion();
            String auxDistribucion = auxEspecieRespuesta.getDistribucionEspecie();
            String auxDesc = auxEspecieRespuesta.getDescEspecie();

            DecimalFormat formato1 = new DecimalFormat("#.0");
            String auxProb = formato1.format((probList.get(id))*100);

            nomCient.setText(auxNomCient + "(" + auxProb + "%)");
            nomComun.setText("Nombre común: " + auxNomComun + "\n");
            estbioGeo.setText("Tipo: " + auxIdEstadoBio + " en Chile\n");
            estConservacion.setText("Estado de conservación: " + auxIdConservacion + "\n");
            distribucion.setText("Distribución:\n " + auxDistribucion + "\n");
            descripcion.setText("Descripción:\n " + auxDesc + "\n");

        }

        catch(Exception ex){
            this.mensaje("No se ha encontrado la especie" + ex.getMessage());
        }
    }

    public void cambiarFicha(View view){
        if(id<2){ id++; }
        else{ id = 0; }
        buscarEspecie(id);//
        //listView.setAdapter(adaptRespuesta);

        uri = "@drawable/id_"+ labelList.get(id);
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        imgViewPred.setImageDrawable(imagen);
    } // Fin cambiarFicha


    public void zoomActWithImageUser(View view) {

        Intent zoomAct = new Intent(this, ImageZoomActivity.class);
        zoomAct.putExtra("tipo", (Serializable) "0");
        zoomAct.putExtra("path", (Serializable) "myImagegal.png"); // crear variable imagenUser
        startActivity(zoomAct);
    }

    public void zoomActWithImagePred(View view) {

        Intent zoomAct = new Intent(this, ImageZoomActivity.class);
        zoomAct.putExtra("tipo", (Serializable) "1");
        zoomAct.putExtra("path", (Serializable) uri); // crear variable imagenUser
        startActivity(zoomAct);
    }

    public void confirmaEspecie(View view) {
        auxBtoConfirmar.setVisibility(View.GONE);
        imgViewUser.setVisibility(View.INVISIBLE);
        auxNext1.setVisibility(View.INVISIBLE);
        auxNext2.setVisibility(View.INVISIBLE);
        mensaje("Se ha confirmado la especie, muchas gracias !");
    }

    public void mostrarEleccionUsuario(View view) {
        auxBtoConfirmar.setVisibility(View.GONE);
        imgViewUser.setVisibility(View.INVISIBLE);
        auxNext1.setVisibility(View.INVISIBLE);
        auxNext2.setVisibility(View.INVISIBLE);
        auxScrollView.setVisibility(View.INVISIBLE);
        TextView txtEspUser = findViewById(R.id.txtViewEspUser);
        txtEspUser.setText(auxSpinner.getSelectedItem().toString());
        txtEspUser.setVisibility(View.VISIBLE);
        Bitmap bitmap = new ImageSaver(this).
                setFileName("myImagegal.png").
                setDirectoryName("images").
                load();
        imgViewPred.setImageBitmap(bitmap);
        mensaje("Se ha confirmado la especie, muchas gracias !");
    }
}