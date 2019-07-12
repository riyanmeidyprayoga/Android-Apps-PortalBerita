package com.riyanmeidyprayoga.www.uas2019v2.pengaduan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.riyanmeidyprayoga.www.uas2019v2.R;

public class DetailPengaduanActivity extends AppCompatActivity {

    EditText edNama, edNik, edIsi;
    Button btnUpdate, btnDelete;

    public static final String KEY_ID = "key_id";
    RealmHelper realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengaduan);

        realm = new RealmHelper(DetailPengaduanActivity.this);

        final int dataID = getIntent().getIntExtra(KEY_ID, 0);

        edNama = findViewById(R.id.ed_nama);
        edNik = findViewById(R.id.ed_nik);
        edIsi = findViewById(R.id.ed_isi);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        CatatanModul data = realm.showOneData(dataID);

        edNama.setText(data.getNama());
        edNik.setText(data.getNik());
        edIsi.setText(data.getIsi());



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatatanModul catatan = new CatatanModul();
                catatan.setId(dataID);
                catatan.setNama(edNama.getText().toString());
                catatan.setNik(edNik.getText().toString());
                catatan.setIsi(edIsi.getText().toString());

                realm.updateData(catatan);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.deleteData(dataID);
                finish();
            }
        });
    }
}

