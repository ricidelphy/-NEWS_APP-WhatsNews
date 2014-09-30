package com.example.whatsnews;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.widget.ListView;
import android.widget.TextView;


public class FeedFactory {
	

	
	public static ArrayList<Feed> createFeeds(String urlFeed){
		
		ArrayList<Feed> feeds = new ArrayList<Feed>();
		
		XmlPullParser lectorxml = Xml.newPullParser();
		
		
		
		try {
			URL url = new URL(urlFeed);
			
			String source = "";
			
			lectorxml.setInput(new InputStreamReader(url.openStream()));
			
			Document jj = Jsoup.parse(url, 3000);
			
			Elements ele = jj.getElementsByTag("title");
			source = html2text(ele.get(0).text());
			
			
			
			
			
			int evento = lectorxml.getEventType();
			
			Feed temp = new Feed(); //feed temporal, para recoger datos
			
			
			while(evento!=XmlPullParser.END_DOCUMENT){
				

				
				if(evento==XmlPullParser.START_TAG && lectorxml.getName().compareTo("item")==0){
					temp = new Feed();
				}
				
				
				if(evento==XmlPullParser.START_TAG && lectorxml.getName().compareTo("title")==0){
					
					temp.setTitle(html2text(lectorxml.nextText()));
					
				}
				if(evento==XmlPullParser.START_TAG && lectorxml.getName().compareTo("link")==0){
					temp.setLink(lectorxml.nextText());
				}
				if(evento==XmlPullParser.START_TAG && lectorxml.getName().compareTo("description")==0){
					temp.setDescription(html2text(lectorxml.nextText()));
				}
				if(evento==XmlPullParser.START_TAG && lectorxml.getName().compareTo("pubDate")==0){
					temp.setPubDate(lectorxml.nextText());
				}
				
				if(evento==XmlPullParser.START_TAG && lectorxml.getName().contains("enclosure")){
					if(!lectorxml.getAttributeValue(null,"url").isEmpty()){
						temp.setImg(lectorxml.getAttributeValue(null,"url"));
					}
					
				}
				if(evento==XmlPullParser.START_TAG && lectorxml.getName().contains("content")){
					if(!lectorxml.getAttributeValue(null,"url").isEmpty()){
						temp.setImg(lectorxml.getAttributeValue(null,"url"));
					}
					
				}
				
			
				
				if(evento==XmlPullParser.END_TAG && lectorxml.getName().compareTo("item")==0){
					temp.setSource(source);
					feeds.add(temp);
				}
				
				
				
				evento = lectorxml.next();
			}
			
			
			
		} catch (Exception e) {
			
			//Log.v("Error de XML", e.getMessage());
		}

		
		
		return feeds;
	}
	
	public static String html2text(String html) {
	    return Jsoup.parse(html).text();
	}
	
	
	 
}
