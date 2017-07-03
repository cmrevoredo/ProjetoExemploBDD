package com.cmrevoredo.s2d2.bdd.exemplo.filtros;

import java.io.BufferedReader;
import java.io.FileReader;

public final class FiltroPhishing {
	
	public static String URL_PHISHTANK = "http://checkurl.phishtank.com/checkurl/index.php?url=";
	public static String URL_SAFE_BROWSING = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key=AIzaSyBQkBIN9bLGayfNizR5zlb9wx3u1uhoZYM";

	private String jsonPayLoadString = ""; 
	
	public static FiltroPhishing instance;
	
	static{
		instance = new FiltroPhishing();
	}
	
	private FiltroPhishing(){
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(getClass().getResource("/com/cmrevoredo/s2d2/bdd/exemplo/payload.json").getFile()));
			StringBuilder sb = new StringBuilder();
			while(br.ready()){
				sb.append(br.readLine());
			}
			br.close();
			jsonPayLoadString = sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static FiltroPhishing getInstance(){
		return instance;
	}
	
	public String getJsonPayLoadString(){
		return this.jsonPayLoadString;
	}
	
	public boolean isPhishingPhishTank(String xml){
		if (xml.contains("<in_database>")){
			String resultado = xml.substring(xml.indexOf("<in_database>")+13, xml.indexOf("</in_database>"));
			return new Boolean(resultado);
		}
		return false;
	}

	public boolean isPhishingSafeBrowsing(String xml){
		return xml.contains("matches");
	}
	
}
