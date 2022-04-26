package com.example.schimbacrypto_marca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.schimbacrypto_marca.components.Currency;
import com.example.schimbacrypto_marca.components.CurrencyRecyclerAdapter;

import java.util.ArrayList;

public class ChooseCurrencyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Intent intent;
    private CurrencyRecyclerAdapter currencyRecyclerAdapter;
    private Currency baseCurrency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrtivity_ryclelist_choose_currency);
        recyclerView = findViewById(R.id.currency_recyclerView);
        intent = getIntent();
        baseCurrency = (Currency) intent.getSerializableExtra("baseCurrency");

        ((ImageView)findViewById(R.id.iv_base_logo)).setImageDrawable(
                getResources().getDrawable(baseCurrency.getLogo())
        );
        ((TextView)findViewById(R.id.tv_base_code)).setText(baseCurrency.getCode_3());
        ArrayList<Currency> currencyList = (ArrayList<Currency>) intent.getSerializableExtra("currencyList");
        currencyList.remove(baseCurrency);
        currencyRecyclerAdapter = new CurrencyRecyclerAdapter(currencyList, this, baseCurrency.getCode_3());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(currencyRecyclerAdapter);
    }


}