package com.elastic.poc.model.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Party {

    private Meta meta;
    private String name;

    private String description;

    private List<PartyId> partyId;
}
