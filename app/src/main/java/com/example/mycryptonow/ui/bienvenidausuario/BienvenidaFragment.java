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
        // El radio del c??rculo hueco del sector de la entidad se establece en 0, es un c??rculo en lugar de un anillo
        pie.setHoleRadius(60);
        // El radio del c??rculo blanco semitransparente en el medio se oculta cuando se establece en 0
        pie.setTransparentCircleRadius(10);
        // Establecer el color del c??rculo central
        //pie.setHoleColor(Color.CYAN);
        // Establecer la palabra en la parte central (generalmente se establece cuando el c??rculo blanco en el medio no est?? oculto)
        pie.setCenterText("Proporci??n hombre a mujer");
        // Establecer el color de fuente de la palabra central
        pie.setCenterTextColor(Color.RED);
        // Establece el tama??o de fuente de la palabra central
        pie.setCenterTextSize(16);
        // Establece el tama??o de fuente de la descripci??n (hombre mujer en la imagen)
        pie.setEntryLabelTextSize(10);
        // Establece el tama??o de fuente de los datos (44 56 en la figura)
        pieDataSet.setValueTextSize(10);
        // Establecer la ubicaci??n de la descripci??n
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLinePart1Length(0.1f);// Establecer la longitud de la l??nea de conexi??n de descripci??n
        // Establecer la ubicaci??n de los datos
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLinePart2Length(0.6f);// Establecer la longitud de la l??nea de conexi??n de datos
        // Establecer el color de las dos l??neas de conexi??n
        pieDataSet.setValueLineColor(Color.WHITE);

        // Para el funcionamiento de una cadena de letras en la esquina inferior derecha
        pie.getDescription().setEnabled(true);                                    // Si mostrar la descripci??n en la esquina inferior derecha
        pie.getDescription().setText("Esta es la forma de modificar esa cadena de ingl??s");        // Modificar la visualizaci??n de las letras en la esquina inferior derecha
        pie.getDescription().setTextSize(20);                                        //tama??o de fuente
        pie.getDescription().setTextColor(Color.RED);                          //color de fuente

        //Leyenda
        Legend legend=pie.getLegend();
        legend.setEnabled(true);        // Si mostrar la leyenda
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);        // Posici??n de la leyenda


        //Actualizaci??n de datos
        pie.notifyDataSetChanged();
        pie.invalidate();

        // Animaci??n (si usa animaci??n, puede omitir el paso de actualizaci??n de datos)
        pie.animateY(3000);  // El par??metro de animaci??n en el eje Y es el tiempo de ejecuci??n de la animaci??n en milisegundos
//        line.animateX(2000);  // Animaci??n del eje X
//        line.animateXY(2000,2000);// Animaci??n mixta de dos ejes XY

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

