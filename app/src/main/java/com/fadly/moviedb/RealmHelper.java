package com.fadly.moviedb;


import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final com.fadly.moviedb.ModelMovieRealm movieModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(com.fadly.moviedb.ModelMovieRealm.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    movieModel.setId(nextId);
                    com.fadly.moviedb.ModelMovieRealm model = realm.copyToRealm(movieModel);
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<com.fadly.moviedb.ModelMovieRealm> getAllMovie(){
        RealmResults<com.fadly.moviedb.ModelMovieRealm> results = realm.where(com.fadly.moviedb.ModelMovieRealm.class).findAll();
        return results;
    }

    public void delete(Integer id){
        final RealmResults<com.fadly.moviedb.ModelMovieRealm> model = realm.where(com.fadly.moviedb.ModelMovieRealm.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}