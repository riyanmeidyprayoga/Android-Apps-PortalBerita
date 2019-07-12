package com.riyanmeidyprayoga.www.uas2019v2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.riyanmeidyprayoga.www.uas2019v2.Fragment.BeritaFragment;
import com.riyanmeidyprayoga.www.uas2019v2.Fragment.HomeFragment;
import com.riyanmeidyprayoga.www.uas2019v2.Fragment.PengaduanFragment;
import com.riyanmeidyprayoga.www.uas2019v2.Fragment.TentangFragment;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //load pertama fragment
        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Home");
        fragmentTransaction.commit();
    }

    //PERINGATAN KELUAR
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Doland Tegal");
        alert.setMessage("Anda Yakin Ingin Keluar ?");
        alert.setIcon(R.drawable.ic_logo);
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HomeActivity.this.finish();
            }
        });
        alert.setNegativeButton("no", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int
                            i) {
                    }
                });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayselectedScrean(item.getItemId());
        return true;
    }


    public void displayselectedScrean(int itemId) {
        Fragment fragment = null;
        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                getSupportActionBar().setTitle("Home");
                break;
//
            case R.id.nav_berita:
                fragment = new BeritaFragment();
                getSupportActionBar().setTitle("Berita");
                break;
//                Intent b = new Intent(HomeActivity.this, BeritaActivity.class);
//                getSupportActionBar().setTitle("Berita");
//                startActivity(b);
//                break;
            case R.id.nav_pengaduan:
                fragment = new PengaduanFragment();
                getSupportActionBar().setTitle("Pengaduan");
                break;
            case R.id.nav_tentang:
                fragment = new TentangFragment();
                getSupportActionBar().setTitle("Tentang");
                break;
        }
        //REPLACE FRAGMENTNYA
        if (fragment !=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,fragment);
            ft.commit();
        }
        DrawerLayout drawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
