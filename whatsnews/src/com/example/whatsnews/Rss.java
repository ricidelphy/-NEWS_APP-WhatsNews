package com.example.whatsnews;

public class Rss {
	private String name;
	private String link;
	public Rss(String name, String link) {
		super();
		this.name = name;
		this.link = link;
	}
	public Rss() {
		this(null,null);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "Rss [name=" + name + ", link=" + link + "]";
	}
	
	
}
