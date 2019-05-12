package com.app.survey;

import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.app.survey.ManageSurvey.females;
import static com.app.survey.ManageSurvey.males;

public class AnalyticsActivity extends AppCompatActivity {



    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        pieChart = (PieChart) findViewById(R.id.piechart);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(males, 0));
        yvalues.add(new Entry(females, 1));


        PieDataSet dataSet = new PieDataSet(yvalues, "Users");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("MALE");
        xVals.add("FEMALE");


        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);


        PieData data = new PieData(xVals, dataSet);

        data.setValueTextColor(Color.DKGRAY);

        // In percentage Ter


        pieChart.setData(data);

        pieChart.setDescription("Survey users analytics");




    }




}
