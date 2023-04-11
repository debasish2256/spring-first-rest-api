package com.spring.project.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Zippopotam {
	public static void main(String[] args) throws Exception {
		URL url = new URL("https://api.zippopotam.us/us/33162");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
        in.close();
        con.disconnect();
        String jsonData = content.toString();
        System.out.println(jsonData);
    }
}
