package com.riyanmeidyprayoga.www.uas2019v2.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.riyanmeidyprayoga.www.uas2019v2.R;

/**
 * A simple {@link Fragment} subclass.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.arlib.floatingsearchview.FloatingSearchView;
import com.riyanmeidyprayoga.www.uas2019v2.pengaduan.CatatanAdapter;
import com.riyanmeidyprayoga.www.uas2019v2.pengaduan.CatatanModul;
import com.riyanmeidyprayoga.www.uas2019v2.pengaduan.RealmHelper;
import com.riyanmeidyprayoga.www.uas2019v2.pengaduan.TambahPengaduanActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PengaduanFragment extends Fragment {

    List<CatatanModul> dataCatatn = new ArrayList<>();
    RecyclerView recycler;
    RealmHelper realm;
    FloatingSearchView searchView;
    public PengaduanFragment() {
        // Required empty public constructor
    }

    // Method onCreateView dipanggil saat Fragment harus menampilkan layoutnya      // dengan membuat layout tersebut secara manual lewat objek View atau dengan     // membaca file XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengaduan, parent, false);
        // Layout tampilan untuk fragment ini
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TambahPengaduanActivity.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });


        realm = new RealmHelper(getActivity());

        //1.membuat layout per item
        //2.membuat data model
//        CatatanModul catatan1 = new CatatanModul();
//        catatan1.setId(1);
//        catatan1.setJudul("Hutang ke A");
//        catatan1.setJumlahhutang("20000");
//        catatan1.setTangggal("12-12-2012");
//
//        for (int i = 0; i <20; i++){
//            dataCatatn.add(catatan1);
//        }

        //get data realm
        dataCatatn = realm.showData();
        //3.adapter
        recycler = (RecyclerView) view.findViewById(R.id.recyclerView);
        recycler.setAdapter(new CatatanAdapter(getActivity(), dataCatatn));

        //4.layout manager
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(), 1));

        return view;
    }

    private List<CatatanModul> filterData(List<CatatanModul> dataCatatn, String newQuery) {
        String lowercasequery = newQuery.toLowerCase();
        List<CatatanModul> filterData = new ArrayList<>();
        for (int i = 0; i < dataCatatn.size(); i++) {
            String text = dataCatatn.get(i).getNama().toLowerCase();
            if (text.contains(lowercasequery)) {
                filterData.add(dataCatatn.get(i));
            }
        }
        return filterData;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        dataCatatn = realm.showData();
        recycler.setAdapter(new CatatanAdapter(getActivity(), dataCatatn));

    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau          // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

}
