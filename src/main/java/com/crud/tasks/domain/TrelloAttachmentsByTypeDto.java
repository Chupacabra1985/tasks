package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloAttachmentsByTypeDto {
    @JsonUnwrapped
    TrelloTrelloDto trelloTrelloDto;


    public TrelloAttachmentsByTypeDto() {

    }
}
