package com.utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import io.restassured.RestAssured;


public class apiUtlity extends TestBase {

	public static Double getinfoAPI() throws Exception {
		TestBase.init();
		String latlonapi = "";
		String info = "";
		Double temprature=0.0;
		RestAssured.baseURI = prop.getProperty("url_latlon");
		httpRequest = RestAssured.given();
		httpRequest.header("x-api-key", prop.getProperty("apikey"));

		response = httpRequest.get("/geo/1.0/direct?q=Delhi,IN,&limit=1");
		if (response.getStatusCode() != 200) {
			staticWait(5);
		}
		if (response.getStatusCode() == 200) {
			latlonapi = "Pass";
		}

		if (latlonapi.equals("Pass")) {
			String responseBody = response.getBody().asString();
			JSONParser jsonparser = new JSONParser();
			Object obj_array = jsonparser.parse(responseBody);
			JSONArray js_ary = (JSONArray) obj_array;
			JSONObject js_obj = (JSONObject) js_ary.get(0);
			Double lat = (Double) js_obj.get("lat");
			Double lon = (Double) js_obj.get("lon");
			System.out.println("lattitude:" + lat + "& longitude" + lon);
			staticWait(3);

			RestAssured.baseURI = prop.getProperty("url_latlon");
			httpRequest = RestAssured.given();
			httpRequest.header("x-api-key", prop.getProperty("apikey"));
			response = httpRequest.get("/data/2.5/weather?lat=" + lat + "&lon=" + lon);
			if (response.getStatusCode() != 200) {
				staticWait(5);
			}
			if (response.getStatusCode() == 200) {
				info = "Pass";
			}
			if (info.equals("Pass")) {
				String responseinfoBody = response.getBody().asString();
				System.out.println("responseinfoBody : " + responseinfoBody);
				Object obj_array_info = jsonparser.parse(responseinfoBody);
				JSONObject js_obj_info = (JSONObject) obj_array_info;
				JSONObject main_obj = (JSONObject) js_obj_info.get("main");
				temprature = (Double) main_obj.get("temp");
				System.out.println("temprature from API:" + temprature);
			}

		}
		return temprature;
	}
}
