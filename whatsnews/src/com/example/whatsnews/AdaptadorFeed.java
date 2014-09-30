package com.example.whatsnews;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorFeed extends ArrayAdapter<Feed>{

	private Context ctx;
	private ArrayList<Feed> feeds;
	
	public AdaptadorFeed(Context context, ArrayList<Feed> feeds) {
		super(context, R.layout.detalle_lista_portada, feeds);
		// TODO Auto-generated constructor stub
		this.ctx = context;
		this.feeds = feeds;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub

			view = feeds.get(position).getView(ctx, view);
		
		
		
		
		return view;
	}
	
}
