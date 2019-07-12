package com.riyanmeidyprayoga.www.uas2019v2.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.riyanmeidyprayoga.www.uas2019v2.AdapterBerita;
import com.riyanmeidyprayoga.www.uas2019v2.R;
import com.riyanmeidyprayoga.www.uas2019v2.network.ApiServices;
import com.riyanmeidyprayoga.www.uas2019v2.network.InitRetrofit;
import com.riyanmeidyprayoga.www.uas2019v2.response.BeritaItem;
import com.riyanmeidyprayoga.www.uas2019v2.response.ResponseBerita;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BeritaFragment extends Fragment {

    private RecyclerView recyclerView;

    public BeritaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_berita, container, false);
        // Inisialisasi Widget
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvListBerita);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Eksekusi method
        tampilBerita();
        return rootView;
    }
    private void tampilBerita() {
        ApiServices api = InitRetrofit.getInstance();
        // Siapkan request
        Call<ResponseBerita> beritaCall = api.request_show_all_berita();
        // Kirim request
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<BeritaItem> data_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    if (status){
                        // Buat Adapter untuk recycler view
                        AdapterBerita adapter = new AdapterBerita(getActivity(), data_berita);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(getActivity(), "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
    }
}


