package com.patel.tupdates;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patel.tupdates.model.Result;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.twilio.sdk.TwilioRestClient;


import java.util.Date;
import java.util.HashMap;

@SpringBootApplication
@Configuration
public class Application {

    @Value("${com.patel.tupdates.application.origin}")
    private String origin;
    @Value("${com.patel.tupdates.application.destination}")
    private String destination;
    @Value("${com.patel.tupdates.application.trafficModel}")
    private String trafficModel;
    @Value("${com.patel.tupdates.application.googleAPIKey}")
    private String googleAPIKey;
    @Value("${com.patel.tupdates.application.twilioAcId}")
    private String twilioAcId;
    @Value("${com.patel.tupdates.application.twilioAuthToken}")
    private String twilioAuthToken;
    @Value("${com.patel.tupdates.application.twilioToPhone}")
    private String twilioToPhone;
    @Value("${com.patel.tupdates.application.twilioFromPhone}")
    private String twilioFromPhone;


    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

   /* @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }*/

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            String url="https://maps.googleapis.com/maps/api/distancematrix/json";
            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("units", "imperial")
                    .queryParam("origins",origin )
                    .queryParam("destinations", destination)
                    .queryParam("traffic_model", trafficModel)
                    .queryParam("departure_time", new Date().getTime())
                    .queryParam("key", googleAPIKey);

            String quote = restTemplate.getForObject(builder.build().encode().toUri(),String.class);
            Result result= restTemplate.getForObject(builder.build().encode().toUri(),Result.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(quote);
            String time= actualObj.get("rows").get(0).get("elements").get(0).get("duration_in_traffic").get("text").toString();

            String acSID=twilioAcId;
            String authToken=twilioAuthToken;

            TwilioRestClient twilioRestClient=new TwilioRestClient(acSID,authToken);

            Account account = twilioRestClient.getAccount();

            SmsFactory factory = account.getSmsFactory();

            HashMap<String, String> message = new HashMap<>();

            message.put("To", twilioToPhone);
            message.put("From", twilioFromPhone);
            message.put("Body", result.getDestinations().toString()+time);

            factory.create(message);

            System.out.println("Values are as "+origin +"\n"+twilioAuthToken);
        };
    }
}
