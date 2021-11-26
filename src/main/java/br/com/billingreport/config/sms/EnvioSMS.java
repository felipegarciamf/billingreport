package br.com.billingreport.config.sms;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class EnvioSMS {

	private final static String URL = "https://api.smsdev.com.br/v1/send";

	public void envioSMS() {
		
		 HttpResponse<JsonNode> response = Unirest.post(URL)
				 .header("accept", "application/json")
				 .queryString("key", "")
				 .queryString("type", "")
				 .queryString("number", "")
				 .queryString("msg", "")
				 .asJson();
		
	}
}
