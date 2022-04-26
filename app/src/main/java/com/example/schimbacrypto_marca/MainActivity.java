package com.example.schimbacrypto_marca;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.schimbacrypto_marca.components.Currency;
import com.example.schimbacrypto_marca.components.CurrencyMap;
import com.example.schimbacrypto_marca.components.CurrencyRecyclerAdapter;
import com.example.schimbacrypto_marca.components.Display;
import com.example.schimbacrypto_marca.service.AppHandlers;
import com.example.schimbacrypto_marca.service.AppService;
import com.example.schimbacrypto_marca.utils.UpdateCurrency;
import com.example.schimbacrypto_marca.utils.UpdateScreens;
import com.example.schimbacrypto_marca.utils.VolleyJSONObjectResponseListener;
import com.example.schimbacrypto_marca.utils.VolleyStringResponseListener;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{
    private Handler handler;
    private AppService appService;
    private AppHandlers appHandlers;
    private Currency upCurrency, downCurrency;
    private EditText upScreen, downScreen;
    private ImageView upLogo, downLogo;
    private CurrencyMap currencyMap;
    private Intent intent;
    private String where_we_are;
    private Boolean mandatory_choose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        appService.getLocation(new VolleyStringResponseListener() {
            @Override
            public void onResponse(String response) {
                where_we_are=response;
            }

            @Override
            public void onError(String message) {
                System.out.println(message);
            }
        });
        handler.postDelayed(looking_for_ur_location, 5000);

    }

    private Runnable looking_for_ur_location = new Runnable() {
        @Override
        public void run() {
            initialSetup();
            appHandlers = new AppHandlers(
                    new Display(downCurrency.getRapport_with_base_currency()));
            change_currency_handler();
        }
    };

    private void change_currency_handler() {
        upLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appService.updateCurrenciesRate(downCurrency.getCode_3(), new VolleyJSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        currencyMap.updateCurrenciesRate(response);
                        intent = new Intent(MainActivity.this, ChooseCurrencyActivity.class);
                        intent.putExtra("baseCurrency", downCurrency);
                        intent.putExtra("currencyList", currencyMap.getOnlyTheValues());
                        activityResultLauncher.launch(intent);
                    }

                    @Override
                    public void onError(String errors) {
                        System.out.println(errors);
                    }
                });
            }
        });
    }



    private void initialSetup(){
        upScreen.setText("Click the '?' icon and select the currency that you are interested in");
        upLogo.setImageDrawable(getDrawable(R.drawable.ic_question_mark_svgrepo_com));
        downScreen.setText("If you prefer other base currency, follow the instruction above, " +
                "than swap them");
        System.out.println(where_we_are);
        downCurrency = currencyMap.getCurrencyByKey(where_we_are);
        if(downCurrency==null)
            downCurrency = currencyMap.getCurrencyByKey("EUR");
        downLogo.setImageDrawable(getDrawable(downCurrency.getLogo()));
    }

    private void init(){
        handler = new Handler();
        upScreen = findViewById(R.id.input_screen);
        downScreen = findViewById(R.id.show_screen);
        upLogo = findViewById(R.id.upFlag);
        downLogo = findViewById(R.id.downFlag);
        currencyMap = new CurrencyMap(this);
        appService = new AppService(this);
        downScreen.setText("Looking for ur location... ");
        mandatory_choose = true;
    }


    private void setUpKeyBoard(){
        int btn_code;
        UpdateScreens updateScreens = new UpdateScreens() {
            @Override
            public void updateUpScreen(String value) {
                upScreen.setText(value);
            }

            @Override
            public void updateDownScreen(String value) {
                downScreen.setText(value);
            }
        };
        for(int i=0;i<10;i++){
            String key = String.valueOf(i);
            btn_code = getResources().getIdentifier("btn"+i, "id", getPackageName());
            ((Button) findViewById(btn_code)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appHandlers.charButtonAction(key.charAt(0), updateScreens);
                }
            });
        }

        ((Button)findViewById(R.id.dot)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appHandlers.charButtonAction('.', updateScreens);
            }
        });

        ((Button)findViewById(R.id.undo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appHandlers.UndoButtonAction(updateScreens);
            }
        });

        ((Button)findViewById(R.id.reset)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appHandlers.ResetButtonAction(updateScreens);
            }
        });

        ((Button)findViewById(R.id.swap)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upLogo.setImageDrawable(getResources().getDrawable(downCurrency.getLogo()));
                downLogo.setImageDrawable(getResources().getDrawable(upCurrency.getLogo()));
                Currency temp = upCurrency;
                upCurrency = downCurrency;
                downCurrency = temp;
                appHandlers.SwapButtonAction(updateScreens);
            }
        });
    }

    private void smooth_currency_change(Currency currency){
        appHandlers.changeCurrency(currency, new UpdateCurrency() {
            @Override
            public void updateLogo(Currency currency) {
                upCurrency = currency;
                upLogo.setImageDrawable(getDrawable(currency.getLogo()));
            }

            @Override
            public void updateUpScreen(String value) {
                upScreen.setText(value);
            }

            @Override
            public void updateDownScreen(String value) {
                downScreen.setText(value);
            }
        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent_res = result.getData();
                    int response_code = result.getResultCode();
                    if (response_code==RESULT_OK){
                        if(mandatory_choose){
                            setUpKeyBoard();
                            mandatory_choose = false;
                        }
                        smooth_currency_change((Currency) intent_res.getSerializableExtra("response_content"));
                    }
                }
            }
    );

}