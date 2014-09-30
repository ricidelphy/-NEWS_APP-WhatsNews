package com.example.whatsnews;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.util.Log;
import android.util.Xml;



public class XmlParser {
	public static void guardar(Context ctx, ArrayList<Rss> txt) {
		

		
		try {
			
			
			File archivo = new File(ctx.getExternalFilesDir(null), "cadenas.xml");

			
			FileOutputStream fosxml = new FileOutputStream(archivo);

			XmlSerializer docxml = Xml.newSerializer();
			docxml.setOutput(fosxml, "utf-8");
			docxml.startDocument(null, Boolean.valueOf(true));
			docxml.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
			
			docxml.startTag(null, "links");
			for(int i = 0 ; i < txt.size() ; i++){
				docxml.startTag(null, "rss");
					docxml.attribute(null, "name", txt.get(i).getName());
					docxml.text(txt.get(i).getLink());
					
				docxml.endTag(null, "rss");
			}
			docxml.endTag(null, "links");
			
			docxml.endDocument();
			docxml.flush();
			fosxml.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.v("error de guardado", e.getMessage());
		}
		
	
}

public static void leer(Context con, ArrayList<Rss> textos){

	
	
	XmlPullParser lectorxml = Xml.newPullParser();
	Log.v("etiqueta", "antes del try");
	try {
		lectorxml.setInput(new FileInputStream(new File(
				con.getExternalFilesDir(null),"cadenas.xml")),"utf-8");
		
		int evento = lectorxml.getEventType();
		Log.v("etiqueta", "antes del bucle");
		while(evento!=XmlPullParser.END_DOCUMENT){
			if(evento==XmlPullParser.START_TAG && lectorxml.getName().compareTo("text")==0){
				Log.v("etiqueta", lectorxml.getName());
				Rss temp =new Rss();
				temp.setName(lectorxml.getAttributeValue(null, "name"));
				evento = lectorxml.next();
				if(evento == XmlPullParser.TEXT){
					temp.setLink(lectorxml.getText());
				}
				textos.add(temp);
			}
			
			evento = lectorxml.next();
		}
		
	
		
	} catch (Exception e) {
		
		Log.v("error de lectura", e.getMessage());
	}

	
	
	
}
}
