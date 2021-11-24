package com.example.mycryptonow.ui.bienvenidausuario;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mycryptonow.R;
import com.firebase.ui.auth.data.model.User;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class BienvenidaFragment extends Fragment {

    //Variables globales
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private BienvenidaViewModel mViewModel;
    private PieChart pie;
    private List<PieEntry> list;
    private TextView tvNombreUsuario;

    public static BienvenidaFragment newInstance() {
        return new BienvenidaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bienvenida, container, false);

        //Inicializar
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        iniciarComponentes(root);

        pie = root.findViewById(R.id.prueba);
        list=new ArrayList<>();

        list.add(new PieEntry(56,"masculino"));
        list.add(new PieEntry(44,"hembra"));

        PieDataSet pieDataSet=new PieDataSet(list,"");
        PieData pieData=new PieData(pieDataSet);
        pie.setData(pieData);


        //pie.setBackgroundColor(Color.GRAY);

        // Establecer el color de cada dato
        pieDataSet.setColors(Color.RED,Color.BLUE);
        // El radio del círculo hueco del sector de la entidad se establece en 0, es un círculo en lugar de un anillo
        pie.setHoleRadius(60);
        // El radio del círculo blanco semitransparente en el medio se oculta cuando se establece en 0
        pie.setTransparentCircleRadius(10);
        // Establecer el color del círculo central
        //pie.setHoleColor(Color.CYAN);
        // Establecer la palabra en la parte central (generalmente se establece cuando el círculo blanco en el medio no está oculto)
        pie.setCenterText("Proporción hombre a mujer");
        // Establecer el color de fuente de la palabra central
        pie.setCenterTextColor(Color.RED);
        // Establece el tamaño de fuente de la palabra central
        pie.setCenterTextSize(16);
        // Establece el tamaño de fuente de la descripción (hombre mujer en la imagen)
        pie.setEntryLabelTextSize(10);
        // Establece el tamaño de fuente de los datos (44 56 en la figura)
        pieDataSet.setValueTextSize(10);
        // Establecer la ubicación de la descripción
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLinePart1Length(0.1f);// Establecer la longitud de la línea de conexión de descripción
        // Establecer la ubicación de los datos
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLinePart2Length(0.6f);// Establecer la longitud de la línea de conexión de datos
        // Establecer el color de las dos líneas de conexión
        pieDataSet.setValueLineColor(Color.WHITE);

        // Para el funcionamiento de una cadena de letras en la esquina inferior derecha
        pie.getDescription().setEnabled(true);                                    // Si mostrar la descripción en la esquina inferior derecha
        pie.getDescription().setText("Esta es la forma de modificar esa cadena de inglés");        // Modificar la visualización de las letras en la esquina inferior derecha
        pie.getDescription().setTextSize(20);                                        //tamaño de fuente
        pie.getDescription().setTextColor(Color.RED);                          //color de fuente

        //Leyenda
        Legend legend=pie.getLegend();
        legend.setEnabled(true);        // Si mostrar la leyenda
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);        // Posición de la leyenda


        //Actualización de datos
        pie.notifyDataSetChanged();
        pie.invalidate();

        // Animación (si usa animación, puede omitir el paso de actualización de datos)
        pie.animateY(3000);  // El parámetro de animación en el eje Y es el tiempo de ejecución de la animación en milisegundos
//        line.animateX(2000);  // Animación del eje X
//        line.animateXY(2000,2000);// Animación mixta de dos ejes XY

        return root;
    }

    private void iniciarComponentes( View root) {
        //Inicializacion
        tvNombreUsuario = root.findViewById(R.id.tvNombreUsuarioHome);

        //Agregar valores
        tvNombreUsuario.setText(user.getDisplayName());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BienvenidaViewModel.class);
    }

}

