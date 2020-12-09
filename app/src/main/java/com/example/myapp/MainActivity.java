package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }

    private void jsonParse() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Retrieving posts");
        progressDialog.show();

            System.out.println("!!!posts!!!");
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                    "https://jsonplaceholder.typicode.com/posts", null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.dismiss();

                            try {
                                JSONArray jsonArray = response.getJSONArray("posts");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject posts = jsonArray.getJSONObject(i);

                                    int User_id = posts.getInt("userId");
                                    int id = posts.getInt("id");
                                    String title = posts.getString("title");
                                    String body = posts.getString("body");

                                    mTextViewResult.append(String.valueOf(User_id) + ", " + String.valueOf(id) + ", " + title + ", " + body + "\n\n");
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("volleyError error" + error.getMessage());
                            Toast.makeText(getApplicationContext(), "Poor network connection.", Toast.LENGTH_LONG).show();
                        }
                    });

            mQueue.add(request);
        }
    }

