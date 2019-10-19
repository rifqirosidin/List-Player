package com.sisca.recyclearview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private TextView tvName, tvBirthday, tvLocation, tvNationality, tvDescription;
    private ImageView imageView;
    private String id, name, birtday, location, nationality, imgUrl;
    protected ArrayList<Player> players;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvName = findViewById(R.id.tv_nama);
        tvBirthday = findViewById(R.id.tv_birthday);
        tvLocation = findViewById(R.id.tv_location);
        tvNationality = findViewById(R.id.tv_national);
        tvDescription = findViewById(R.id.tv_decription);
        imageView = findViewById(R.id.img_player);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://www.thesportsdb.com/api/v1/json/1/lookupplayer.php?id=" + id;
        Log.d("url", url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                gson = new Gson();
                PlayerResult result = gson.fromJson(response, PlayerResult.class);
                players = result.getPlayers();
                Player player = result.getPlayers().get(0);

                tvName.setText(player.getName());
                tvBirthday.setText(player.getBirthDate());
                tvNationality.setText(player.getNationality());
                tvLocation.setText(player.getBirthPlace());
                tvDescription.setText(player.getDescription());


                Picasso.get()
                        .load(player.getImage())
                        .fit()
                        .into(imageView);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue.add(stringRequest);


    }
}
