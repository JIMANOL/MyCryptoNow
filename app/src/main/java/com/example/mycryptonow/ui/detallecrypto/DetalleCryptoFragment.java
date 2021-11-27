package com.example.mycryptonow.ui.detallecrypto;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.mycryptonow.R;
import com.example.mycryptonow.models.Datum;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class DetalleCryptoFragment extends Fragment {

    private DetalleCryptoViewModel mViewModel;
    private LineChart lcGrafica;
    private ArrayList<Datum> cryptos;

    public static DetalleCryptoFragment newInstance() {
        return new DetalleCryptoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detalle_crypto, container, false);

        cryptos = (ArrayList<Datum>) getArguments().getSerializable("historial");

        iniciarComponente(root);
        initData();

        return root;
    }

    private void iniciarComponente(View root) {

        lcGrafica = (LineChart) root.findViewById(R.id.lcGraficaDetalle);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleCryptoViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initData() {
        lcGrafica.setBackgroundColor(Color.parseColor("#0f0f0f"));
        setDescription("Grafica de precio"); // Descripción del conjunto
        setYAxis(); // Establecer eje Y
        setXAxis(); // Establecer el eje X
        setChartData(); // Establecer datos de icono
    }



    private void setDescription(String descriptionStr) {
        // Descripción del ajuste
        Description description = new Description();
        description.setText(descriptionStr);
        description.setTextColor(Color.WHITE);

        // Calcular la posición de descripción
        WindowManager wm = (WindowManager) getActivity().getSystemService(getActivity().WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        Paint paint = new Paint();
        paint.setTextSize(19f);
        paint.setColor(Color.WHITE);
        float x = outMetrics.widthPixels - Utils.convertDpToPixel(200);
        float y =  Utils.calcTextHeight(paint, descriptionStr) + Utils.convertDpToPixel(12);
        description.setPosition (x, y); // Establece la posición de descripción
        lcGrafica.setDescription(description);
    }

    private void setYAxis() {
        // eje Y izquierdo
        final YAxis yAxisLeft = lcGrafica.getAxisLeft();
        yAxisLeft.setGranularity (2f); // Establecer el tamaño del intervalo
        yAxisLeft.setTextSize (2f); // El tamaño del texto es 12dp
        yAxisLeft.setTextColor (Color.WHITE); // El color del texto es gris
    }

    private void setXAxis() {
        // eje X
        XAxis xAxis = lcGrafica.getXAxis();
        xAxis.setPosition (XAxis.XAxisPosition.BOTTOM); // en la parte inferior
        xAxis.setDrawGridLines (false); // No dibuje líneas de cuadrícula+
        xAxis.setTextColor (Color.WHITE); // El color del texto es gris
        xAxis.setTextSize (12f); // El tamaño del texto es 12dp
        xAxis.setGranularity (3f); // Establecer el tamaño del intervalo
    }

    public void setChartData() {

        // 1. Obtener los datos de uno o más conjuntos de objetos de entrada
        // Datos analógicos 1
        List<Entry> lineaInfo = new ArrayList<>();
        int i =0;


        for (Datum crypto : cryptos) {
            double info = crypto.getQuote().getMxn().getPrice();
            float num = (float) info;
            lineaInfo.add(new Entry(i ,num));
            i+=1;
            if(i >= 16){
                break;
            }
        }

        // 2. Cree por separado un conjunto de datos de polilínea a partir de los datos de cada grupo de colecciones de objetos de entrada
        LineDataSet lineDataSet1 = new LineDataSet(lineaInfo, "Precio");
        // 3 Agregar cada conjunto de conjuntos de datos de polilínea a datos de polilínea
        LineData lineData = new LineData(
                lineDataSet1
        );
        // o
        //List<ILineDataSet> dataSets = new ArrayList<>();
        // dataSets.add(lineDataSet1);
        // dataSets.add(lineDataSet2);
        // LineData lineData = new LineData(dataSets);
        // 4. Establecer los datos de polilínea en el gráfico
        lineDataSet1.setDrawCircleHole (false); // No dibuje un agujero, es un punto sólido
        lineDataSet1.setColor (Color.WHITE); // Establecer en rojo
        lineDataSet1.setMode (LineDataSet.Mode.CUBIC_BEZIER); // Establecer en curva Bezier
        lineDataSet1.setCubicIntensity (0.15f); // intensidad
        lineDataSet1.setCircleColor (Color.WHITE); // Establezca el punto en color
        lineDataSet1.setCircleRadius(5f);
        lineDataSet1.setLineWidth (2f);
        lineDataSet1.setDrawFilled (true); // habilitar el llenado
        lineDataSet1.setFillColor (Color.WHITE); // Relleno blanco
        lineDataSet1.setFillAlpha (65); // Transparencia
        // Establezca el ancho de línea en 2
        lcGrafica.setData(lineData);
    }

}