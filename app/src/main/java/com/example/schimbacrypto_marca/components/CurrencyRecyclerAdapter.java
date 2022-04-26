package com.example.schimbacrypto_marca.components;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schimbacrypto_marca.ChooseCurrencyActivity;
import com.example.schimbacrypto_marca.GrafVolatilityActivity;
import com.example.schimbacrypto_marca.R;
import com.example.schimbacrypto_marca.service.AppService;
import com.example.schimbacrypto_marca.utils.VolleyJSONObjectResponseListener;
import com.jjoe64.graphview.GraphView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRecyclerAdapter extends RecyclerView.Adapter<CurrencyRecyclerAdapter.MyViewHolder> {

    private List<Currency> currencyList;
    private Context context;
    private String baseCode;
    private AppService appService;

    public CurrencyRecyclerAdapter(List<Currency> currencyList, Context context, String baseCode) {
        this.currencyList = currencyList;
        this.context = context;
        this.baseCode = baseCode;
        this.appService = new AppService(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_entry, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String code_3=currencyList.get(position).getCode_3();
        holder.currecy_log.setImageDrawable(
                context.getResources().getDrawable(currencyList.get(position).getLogo())
        );
        holder.currecy_code.setText(code_3);
        holder.currency_rate.setText(String.valueOf(
                currencyList.get(position).getRapport_with_base_currency()));
        holder.other_currecy_code.setText(baseCode);

        holder.currecy_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("response_content", currencyList.get(position));
                ((Activity)context).setResult(Activity.RESULT_OK, intent);
                ((Activity)context).finish();
            }
        });
        holder.volatility_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Volatility volatility_obj = new Volatility();
                appService.getVolatility(baseCode, code_3, new VolleyJSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            volatility_obj.setMean(response.getDouble("mean"));
                            volatility_obj.setScale(response.getLong("scale"));
                            volatility_obj.setVolatility_list(response.getJSONArray("volatility"));
                            volatility_obj.setMin(response.getDouble("min"));
                            volatility_obj.setMax(response.getDouble("max"));

                        }catch (JSONException jsonException){
                            jsonException.printStackTrace();
                        }
                        Intent next_intent = new Intent(context, GrafVolatilityActivity.class);
                        next_intent.putExtra("volatility", volatility_obj);
                        next_intent.putExtra("title", baseCode+"/"+code_3+" Volatility");
                        ((Activity)context).startActivity(next_intent);
                    }

                    @Override
                    public void onError(String errors) {
                        System.out.println(errors);
                    }
                });
            }
        });



    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView currecy_log;
        TextView currecy_code, other_currecy_code, currency_rate;
        Button volatility_btn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            currecy_log = itemView.findViewById(R.id.img_currency_logo);
            currecy_code = itemView.findViewById(R.id.tv_currecy_code);
            other_currecy_code = itemView.findViewById(R.id.tv_other_currency_code);
            currency_rate = itemView.findViewById(R.id.tv_currecy_rate);
            volatility_btn = itemView.findViewById(R.id.btn_volatility);
        }
    }
}