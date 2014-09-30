package com.example.whatsnews;


import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ListFeeds extends Activity {

	// navigation drawer-----
		private String[] menu;
		private DrawerLayout mDrawerLayout;
		private ListView mListView;
		private ActionBarDrawerToggle mDrawerToggle;
		private CharSequence mDrawerTitle;
		private CharSequence mTitle;
		
		private ListView lista;
		private ArrayList<Rss> misRss;
		private EditText edLink, edNombre;
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_feeds);
		
		lista = (ListView) findViewById(R.id.listaPortada);
		edLink = (EditText)findViewById(R.id.edLink);
		edNombre = (EditText)findViewById(R.id.edNombre);
		misRss = new ArrayList<Rss>();
		
		// Obtener lista de menu
		menu = getResources().getStringArray(R.array.nMenu);
		// Obtener layout principal
		mDrawerLayout = (DrawerLayout) findViewById(R.id.main_layout);
		// Obtener lista
		mListView = (ListView) findViewById(R.id.left_drawer);

		// Aplicar sombra al menu
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// llenamos la lista con nuestro array
		mListView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, menu));
		mListView.setOnItemClickListener(new DrawerItemClickListener());

		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.main_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);

				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		// Escuchador del boton menu
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		if (savedInstanceState == null) {
			selectItem(2);
		}
	}
	
	
	
	// fin oncreate-----------------------------------------------
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...



		return super.onOptionsItemSelected(item);
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		// update the main content by replacing fragments

		switch (position) {
		case 0:

			break;

		case 1:

			break;

		}

		// remplaza el fragmento si existe por el nuevo
		// FragmentManager fragmentManager = getFragmentManager();
		// fragmentManager.beginTransaction().replace(R.id.pager,
		// fragment).commit();

		// update selected item and title, then close the drawer
		mListView.setItemChecked(position, true);
		setTitle(menu[position]);
		mDrawerLayout.closeDrawer(mListView);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_feeds, menu);
		return true;
	}
	
	public void addRss(View v){
		//misRss.add(new Rss(,edLink.getText().toString()));
	}

}
