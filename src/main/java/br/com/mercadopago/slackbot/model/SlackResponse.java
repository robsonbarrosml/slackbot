package br.com.mercadopago.slackbot.model;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SlackResponse {
    @JsonProperty("text")
    private String text;

    @JsonProperty("response_type")
    private String responseType;

    public SlackResponse() {
    }

    public SlackResponse(String text) {
        this.text = text;
    }
}
