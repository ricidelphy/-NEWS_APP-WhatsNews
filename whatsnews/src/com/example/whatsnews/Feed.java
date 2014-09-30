package com.example.whatsnews;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Feed implements Comparable<Feed>{
	private String title;
	private String link;
	private String description;
	private String pubDate;
	private String content;
	private String img;
	private String source;
	
	public Feed() {
		this(null,null,null,null,null,null);
	}

	public Feed(String title, String link, String description, String pubDate,
			String content, String source) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
		this.content = content;
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "Feed [title=" + title + ", link=" + link + ", description="
				+ description + ", pubDate=" + pubDate + ", content=" + content
				+ "]";
	}
	
	public View getView(Context ctx, View v){
		
		LayoutInflater i = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(v == null){
			v = i.inflate(R.layout.detalle_lista_portada, null);
		}
		
		TextView tvDate = (TextView)v.findViewById(R.id.tvFecha);
		TextView tvTitle = (TextView)v.findViewById(R.id.tvTitulo);
		TextView tvDescription = (TextView)v.findViewById(R.id.tvDescripcion);
		TextView tvSource = (TextView)v.findViewById(R.id.tvFuente);
		ImageView img = (ImageView)v.findViewById(R.id.imgLista);
		
		tvDate.setText(this.getPubDate());
		tvTitle.setText(this.getTitle());
		tvDescription.setText(this.getDescription());
		tvSource.setText(ctx.getString(R.string.source) + " " + this.getSource());
		if(this.img!=null){
			Picasso.with(ctx).load(this.img).into(img);
		}else{
			Picasso.with(ctx).load(R.drawable.image_not_found).into(img);
		}
		
		
		return v;
	}

	@Override
	public int compareTo(Feed o) {
		// TODO Auto-generated method stub
		SimpleDateFormat simple = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
        
        try {
            Date date1 = simple.parse(o.getPubDate());
            Date date = simple.parse(this.getPubDate());
            
            
            return date1.compareTo(date);
            
        } catch (Exception ex) {
            Log.v("Error del parser Date", ex.getMessage());
        }
        
        
    
    return -1;
	}
	
	
	
}
