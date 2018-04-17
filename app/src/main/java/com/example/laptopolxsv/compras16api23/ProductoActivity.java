package com.example.laptopolxsv.compras16api23;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;

public class ProductoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText nombre, precio;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nombre=(EditText)findViewById(R.id.nombre);
        precio=(EditText)findViewById(R.id.precio);
        lista=(ListView)findViewById(R.id.lista);

        llenarLista();
    }

    private void llenarLista() {
        Collections.sort(MainActivity.listaProductos);
        ArrayAdapter<Productos> adapter=new ArrayAdapter<Productos>(this, android.R.layout.simple_list_item_1,MainActivity.listaProductos);
        lista.setAdapter(adapter);
    }



    public void agregar(View view) {
        if(nombre.getText().toString().equals("") || precio.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Los campos no deben estar vacios.", Toast.LENGTH_SHORT).show();
        }
        else {
            boolean verdad=false;
            for (int i = 0; i < MainActivity.listaProductos.size(); i++) {
                if(nombre.getText().toString().equals(MainActivity.listaProductos.get(i).getNombre()))
                    verdad=true;
            }

            try {
                 if(!verdad){
                    MainActivity.listaProductos.add(new Productos(nombre.getText().toString(), Float.parseFloat(precio.getText().toString())));
                    Toast.makeText(getBaseContext(), "Agregado.", Toast.LENGTH_SHORT).show();
                 }
                 else
                     Toast.makeText(getBaseContext(), "Ya existe ese producto.", Toast.LENGTH_SHORT).show();
                llenarLista();
                limpiar();
            }
            catch (Exception e) {
                Toast.makeText(getBaseContext(), "Precio no valido.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void limpiar() {
        nombre.setText("");
        precio.setText("");
        nombre.requestFocus();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.producto, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_compra) {
            Intent x=new Intent(this,MainActivity.class);
            setResult(RESULT_OK,x);
            finish();
        }
        else if (id == R.id.nav_producto) {
            Intent x=new Intent(this, ProductoActivity.class);
            startActivityForResult(x,1);
        }
        else if (id == R.id.nav_lista) {

        }
        else if (id == R.id.nav_salir) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
