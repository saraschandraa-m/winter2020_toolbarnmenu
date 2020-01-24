package com.appstone.toolbarnmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.tl_main);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle drawerToggle =
                new ActionBarDrawerToggle(MainActivity.this, drawerLayout, mToolbar,
                        R.string.open, R.string.close);

        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_archive:
                        Toast.makeText(MainActivity.this, "Archive Clicked from menu",
                                Toast.LENGTH_SHORT).show();

                        drawerLayout.closeDrawer(GravityCompat.START, true);
                        Intent bottomNavIntent = new Intent(MainActivity.this, BottomNavigationActivity.class);
                        startActivity(bottomNavIntent);
                        break;

                    case R.id.action_delete:

                        Bundle data = new Bundle();
                        data.putString("FIRST", "Data passed Succesful");
                        data.putInt("SECOND", 10);

                        FirstFragment fragment = new FirstFragment();
                        fragment.setArguments(data);

                        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
                        fm.replace(R.id.fragment_container, fragment);
                        fm.commit();
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.action_archive:
                Toast.makeText(MainActivity.this, "Archive is clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
