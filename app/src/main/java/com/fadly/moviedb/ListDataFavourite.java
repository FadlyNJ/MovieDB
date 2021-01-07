package com.fadly.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListDataFavourite extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    TextView tvnodata;
    RecyclerView recyclerView;
    DataAdapterFavourite adapter;
    List<ModelMovieRealm> DataArrayList; //kit add kan ke adapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        getSupportActionBar().hide();
        tvnodata = (TextView) findViewById(R.id.tvnodata);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        DataArrayList = new ArrayList<>();
        // Setup Realm
        Realm.init(com.fadly.moviedb.ListDataFavourite.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        DataArrayList = realmHelper.getAllMovie();
        if (DataArrayList.size() == 0){
            tvnodata.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            tvnodata.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new DataAdapterFavourite(DataArrayList, new DataAdapterFavourite.Callback() {
                @Override
                public void onClick(int position) {

                    ModelMovieRealm movie = DataArrayList.get(position);
                    Intent intent = new Intent(getApplicationContext(), DetailMovieFavourite.class);
                    intent.putExtra("id",movie.id);
                    intent.putExtra("judul",movie.judul);
                    intent.putExtra("date",movie.releaseDate);
                    intent.putExtra("deskripsi",movie.desc);
                    intent.putExtra("path",movie.path);
                    startActivity(intent);
                    Toast.makeText(com.fadly.moviedb.ListDataFavourite.this, ""+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(com.fadly.moviedb.ListDataFavourite.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    }
}