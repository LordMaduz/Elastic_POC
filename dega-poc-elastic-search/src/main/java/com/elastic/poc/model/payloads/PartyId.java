package com.elastic.poc.model.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartyId {

    private Meta meta;
    private String identifierType;
    private String identifierScheme;
    private String identifierValue;

}
