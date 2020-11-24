package br.com.mercadopago.slackbot.controller;

import java.util.Arrays;
import br.com.mercadopago.slackbot.model.SlackResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class SlackController {

    @Autowired
    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    public ResponseEntity<String> getTeste() {
        return new ResponseEntity<>("Teste OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/slack",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public JsonNode slackCommand(@RequestParam("team_id") String teamId,
                                       @RequestParam("team_domain") String teamDomain,
                                       @RequestParam("channel_id") String channelId,
                                       @RequestParam("channel_name") String channelName,
                                       @RequestParam("user_id") String userId,
                                       @RequestParam("user_name") String userName,
                                       @RequestParam("command") String command,
                                       @RequestParam("text") String text,
                                       @RequestParam("response_url") String responseUrl) throws JsonProcessingException {
        //SlackResponse response = new SlackResponse();
        //response.setText("Funfou");
        //response.setResponseType("in_channel");
//12385876294
        String uri = "https://5fbb14bdcc6c9d001624a816.mockapi.io/homologator/v1/payment/";
        //String payment = event.getText().substring(event.getText().indexOf(" ") + 1, event.getText().length());
//10174126009

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("X-Caller-Scopes", "admin");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<JsonNode> result = restTemplate.exchange(uri + text, HttpMethod.GET, entity, JsonNode.class);
        JsonNode responsePayload = result.getBody();

        //String returnedPayment = mapper.treeToValue(responsePayload, String.class);

        return responsePayload;
    }

    /*@RequestMapping(value = "/slack",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public SlackResponse slackCommand(@RequestParam("team_id") String teamId,
                                      @RequestParam("team_domain") String teamDomain,
                                      @RequestParam("channel_id") String channelId,
                                      @RequestParam("channel_name") String channelName,
                                      @RequestParam("user_id") String userId,
                                      @RequestParam("user_name") String userName,
                                      @RequestParam("command") String command,
                                      @RequestParam("text") String text,
                                      @RequestParam("response_url") String responseUrl) {
        SlackResponse response = new SlackResponse();
        response.setText("Funfou");
        response.setResponseType("in_channel");

        return response;
    }*/

}
