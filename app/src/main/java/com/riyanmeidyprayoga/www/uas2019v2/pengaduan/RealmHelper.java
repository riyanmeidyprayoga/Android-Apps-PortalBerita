package com.riyanmeidyprayoga.www.uas2019v2.pengaduan;


import android.content.Context;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class RealmHelper {

    private Context context;
    private Realm realm;

    public RealmHelper(Context context){
        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    //insert
    public void insertData(CatatanModul catatan){
        realm.beginTransaction();
        realm.copyToRealm(catatan);
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, "DAta berhasil ditambahkan", Toast.LENGTH_SHORT).show();

            }
        });
        realm.close();

    }

    //next id
    public long getNextId(){
        if (realm.where(CatatanModul.class).count() != 0){
            long id = realm.where(CatatanModul.class).max("id").longValue();
            return id +1;
        } else {
            return 1;
        }
    }

    //menampilkan data
    public List<CatatanModul> showData() {
        return realm.where(CatatanModul.class).findAll();
    }

    //menampilkan satu data
    public CatatanModul showOneData(Integer id){
        CatatanModul data = realm.where(CatatanModul.class).equalTo("id", id).findFirst();
        return data;
    }

    //update
    public void updateData(CatatanModul catatan){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(catatan);
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, " Data berhasil di update", Toast.LENGTH_SHORT).show();
            }
        });
        realm.close();
    }
    //delete OneData
    public void deleteData(Integer id){
        realm.beginTransaction();
        CatatanModul catatan = realm.where(CatatanModul.class).equalTo("id", id).findFirst();
        catatan.deleteFromRealm();
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
