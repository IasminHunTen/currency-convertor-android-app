package com.example.schimbacrypto_marca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.schimbacrypto_marca.components.Volatility;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class GrafVolatilityActivity extends AppCompatActivity {
    private GraphView graph;
    private Volatility volatility;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graf_volatility);
        intent = getIntent();
        graphSetup();
        LineGraphSeries<DataPoint> series = getDataPointLineGraphSeries();
        viewportSetup();
        seriesSetup(series);
    }
    private void seriesSetup(LineGraphSeries<DataPoint> series) {
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(16);
        series.setThickness(8);

        series.setTitle("Scale: 1/"+ String.valueOf(volatility.getScale())+"\n"+
                        "Mean: "+String.valueOf(volatility.getMean()));
        series.setColor(Color.BLACK);

        LegendRenderer legendRenderer = graph.getLegendRenderer();
        legendRenderer.setVisible(true);
        legendRenderer.setAlign(LegendRenderer.LegendAlign.TOP);
    }

    @NonNull
    private LineGraphSeries<DataPoint> getDataPointLineGraphSeries() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        volatility = (Volatility) intent.getSerializableExtra("volatility");
        int len = volatility.getVolatility_list().size();
        for(int i=0;i<len;i++){
            series.appendData(new DataPoint(i, volatility.getVolatility_list().get(i)), true, len);
        }
        graph.addSeries(series);
        return series;
    }

    private void graphSetup() {
        graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle(intent.getStringExtra("title"));
        graph.setTitleTextSize(90);
        graph.setTitleColor(Color.BLUE);
    }

    private void viewportSetup() {
        Viewport viewport = graph.getViewport();
        viewport.setXAxisBoundsManual(true);
        viewport.setMinX(0);
        viewport.setMaxX(9);

        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(my_round(volatility.getMin()));
        viewport.setMaxY(my_round(volatility.getMax()));
        viewport.setScrollable(true);
    }

    private long my_round(Double value){
        Double temp;
        if(value<0)
            temp = Math.floor(value);
        else
            temp = Math.ceil(value);
        return Math.round(temp);
    }
}