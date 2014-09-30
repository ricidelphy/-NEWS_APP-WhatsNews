package com.example.whatsnews;

import java.util.ArrayList;
import java.util.Collections;

import android.R.style;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Principal extends Activity {

	// navigation drawer-----
	private String[] menu;
	private DrawerLayout mDrawerLayout;
	private ListView mListView;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	ListView lista;
	HiloLista h;
	AdaptadorFeed ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.estilo_principal);
		lista = (ListView) findViewById(R.id.listaPortada);
		
		
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

			
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			
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
			selectItem(0);
		}

		h = new HiloLista();
		h.execute(new String[] {
				"http://ep00.epimg.net/rss/elpais/portada.xml",
				"http://marca.feedsportal.com/rss/portada.xml" });

	}

	// fin onCreate--------------------------------

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

		switch (item.getItemId()) {
		case R.id.mRefresh: {
			//refreschar noticias
			h = new HiloLista();
			h.execute(new String[] {
					"http://ep00.epimg.net/rss/elpais/portada.xml",
					"http://feeds.weblogssl.com/xataka2" });
			break;
		}
		}

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
			
		case 2:
			Intent i = new Intent(this, ListFeeds.class);
			startActivity(i);
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
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

	private class HiloLista extends AsyncTask<String, Void, ArrayList<Feed>> {

		private Dialogo d = null;

		@Override
		protected ArrayList<Feed> doInBackground(String... params) {
			// TODO Auto-generated method stub

			ArrayList<Feed> temp = new ArrayList<Feed>();

			for (int i = 0; i < params.length; i++) {
				temp.addAll(FeedFactory.createFeeds(params[i]));
			}

			Collections.sort(temp);// ordenar por fecha

			return temp;
		}

		@Override
		protected void onPostExecute(ArrayList<Feed> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
		
			ad = new AdaptadorFeed(Principal.this, result);

			lista.setAdapter(ad);

			d.dismiss();

			lista.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> av, View arg1, int pos,
						long arg3) {
					// TODO Auto-generated method stub
					Feed itemSelected = (Feed) av.getItemAtPosition(pos);
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(itemSelected.getLink()));
					startActivity(browserIntent);

				}
			});

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			if(ad!=null)
				ad.clear();
			
			d = new Dialogo();
			d.setCancelable(false);
			FragmentManager fm = Principal.this.getFragmentManager();
			d.show(fm, "");

			if (!Util.testNet(Principal.this)) { // comprobar si hay conexión
				Toast.makeText(Principal.this, "Sin conexión",
						Toast.LENGTH_SHORT).show();
				d.dismiss();
			}
		}

	}

	public static class Dialogo extends DialogFragment {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
			
			adb.setIcon(R.drawable.h6viz);
			
			
			adb.setMessage("Cargando...").setView(null);
			
			

			return adb.create();
		}
	}

}
