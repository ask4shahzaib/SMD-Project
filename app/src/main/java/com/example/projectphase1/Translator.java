package com.example.projectphase1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Translator extends AppCompatActivity {

    EditText srcText;
    EditText resultText;
    ImageView translate;
    Spinner spinner;
    String url, input, result, src, target;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        src = "en";
        target = "ur";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);
        spinner=findViewById(R.id.spinner2);
        srcText = findViewById(R.id.srcText);

        srcText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //do nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                input= srcText.getText().toString();
            }
        });

        resultText = findViewById(R.id.resultText);
        translate = findViewById(R.id.translate);
        translate .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText("waiting...");
                if(spinner.getSelectedItem().toString().matches("Urdu"))
                {
                    target="ur";
                }
                else if(spinner.getSelectedItem().toString().matches("Arabic"))
                {
                    target="ar";
                }
                else if(spinner.getSelectedItem().toString().matches("Chinese"))
                {
                    target="zh";

                }
                else if(spinner.getSelectedItem().toString().matches("Hindi"))
                {
                    target="hi";
                }


                url = " https://systran-systran-platform-for-language-processing-v1.p.rapidapi.com/translation/text/translate?source="+ src +"&target="+ target +"&input="+input;
                RequestQueue requestQueue = Volley.newRequestQueue(Translator.this);
                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            JSONArray jsonArray = response.getJSONArray("outputs");
                            JSONObject output = jsonArray.getJSONObject(0);
                            result = output.getString("output");
                            resultText.setText(result);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultText.setText(error.getMessage());
                        error.printStackTrace();
                    }
                }
                ){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("X-RapidAPI-Key", "42e7dc452fmsh579f44044179178p1e69bdjsn2559aaecc04b");

                        return params;
                    }
                };
                requestQueue.add(objectRequest);
            }
        });

    }
}