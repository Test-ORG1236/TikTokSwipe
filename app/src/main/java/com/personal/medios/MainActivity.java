package com.personal.medios;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText etApiUrl;
    Button btnComenzarAventura;
    TextView tvErrorUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etApiUrl = findViewById(R.id.url_api);
        tvErrorUrl = findViewById(R.id.error_url);
        btnComenzarAventura = findViewById(R.id.btn_comenzar);

        etApiUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String ApiUrl =  charSequence.toString();
                boolean changed = false;
                if (ApiUrl.contains(" ")){
                    ApiUrl = ApiUrl.replaceAll("\\s","");
                    changed = true;
                }
                if (ApiUrl.contains("\n")){
                    ApiUrl = ApiUrl.replaceAll("\n","");
                    changed = true;
                }

                if (changed) etApiUrl.setText(ApiUrl);
                else checkApiUrl();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        btnComenzarAventura.setOnClickListener( view -> {

            if (!checkApiUrl()) {
                Log.i("URL", "URL no válida");
                return;
            }
            String ApiUrl = etApiUrl.getText().toString();
            Log.i("URL", "URL válida " + ApiUrl);
            Intent i = new Intent(this, VideoActivity.class);
            i.putExtra("API_URL", ApiUrl);
            startActivity(i);

        });


    }

    private String formatUrl(String url){
        return url.endsWith("/")? url : url+"/";
    }

    private boolean isValidURL(String url){
        String[] cases = {"http://", "https://"};
        for (String c : cases){
            if (url.startsWith(c) && url.length()>c.length()) return true;
        }
        return false;
    }

    private boolean checkApiUrl(){
        String ApiUrl = etApiUrl.getText().toString();
        if (!isValidURL(ApiUrl)){
            tvErrorUrl.setVisibility(View.VISIBLE);
            Log.i("URL", "Error en url:" + tvErrorUrl.getText().toString());
            return false;
        }
        Log.i("URL", "Error en url:" + tvErrorUrl.getText().toString());
        tvErrorUrl.setVisibility(View.GONE);
        return true;
    }
}