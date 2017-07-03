package com.cmrevoredo.s2d2.bdd.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class HttpRequestor {

	public static enum METHOD {GET, POST, PUT, DELETE};

	public static String get(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		return response.toString();

	}

	public static String post(String strUrl, String jsonPayLoad) throws Exception {
		byte[] postData = jsonPayLoad.getBytes( StandardCharsets.UTF_8 );
		int postDataLength = postData.length;
		URL url = new URL(strUrl);
		HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects( false );
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json"); 
		conn.setRequestProperty("charset", "utf-8");
		conn.setRequestProperty("Content-Length", Integer.toString( postDataLength));
		conn.setUseCaches(false);
		StringBuilder sb = new StringBuilder(); 
		try{
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);			
			wr.flush(); 
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) { 
				sb.append(line); 
			} 
			wr.close(); 
			rd.close(); 
		}catch(Exception e){
			e.printStackTrace();
		}

		return sb.toString();		
	}

}
