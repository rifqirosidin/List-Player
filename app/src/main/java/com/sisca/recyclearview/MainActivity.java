package com.sisca.recyclearview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView rvPlayer;
    private PlayerAdapter adapter;
    private ArrayList<Player> players;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPlayer = findViewById(R.id.rv_player);
        adapter = new PlayerAdapter(this);
        players = new ArrayList<>();


        populateData();
//        rvPlayer.setLayoutManager(new GridLayoutManager(this, 2));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        DividerItemDecoration divider = new DividerItemDecoration(this, layoutManager.getOrientation());
        rvPlayer.setLayoutManager(layoutManager);
//        rvPlayer.addItemDecoration(divider);
        adapter.setListener(new OnClickListener() {
            @Override
            public void klikView(int position) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("ID", players.get(position).getIdPlayer());

                startActivity(intent);
            }
        });
    }

    public void populateData(){
        //memina request dengan voley
        //jika request berhasil tampilkna kedalam recyclearview via adapter
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://www.thesportsdb.com/api/v1/json/1/searchplayers.php?t=Arsenal";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //ambil data dari response -> json -> arraylist
                 gson = new Gson();
                 PlayerResult result = gson.fromJson(response, PlayerResult.class);
                 players = result.getPlayers();
                 adapter.setPlayers(players);
                 rvPlayer.setAdapter(adapter);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "error di" + error.getMessage());
                Toast.makeText(getApplicationContext(), "That didn't work!", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
