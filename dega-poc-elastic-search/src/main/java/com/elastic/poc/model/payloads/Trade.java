package com.elastic.poc.model.payloads;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "trade")
public class Trade {

    @Id
    private String id;

    @Field(type = FieldType.Date, value = "tradeTime", index = false)
    private Instant tradeTime;

    @Field(type = FieldType.Flattened, value = "meta", index = true)
    private Meta meta;

    @Field(type = FieldType.Object, value = "party", index = false)
    private Party party;

    @Field(type = FieldType.Integer, value = "batchNumber", index = false)
    private int batchNumber; // Let's say batch size is 10,000.. and if we have 100,000th record will be in 10th batch.

    @Field(type = FieldType.Integer, value = "externalRefNumber", index = true)
    private int externalRefNumber;

    @Field(type = FieldType.Flattened, value = "contractDetails", index = true)
    private ContractDetails contractDetails;

}
