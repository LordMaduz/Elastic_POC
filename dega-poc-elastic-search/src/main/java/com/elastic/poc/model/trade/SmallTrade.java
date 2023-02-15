package com.elastic.poc.model.trade;

public class SmallTrade extends TradeEntity {

    private static final String  META_GLOBAL_KEY = "SmallTrade";
    private static final String  META_EXTERNAL_KEY = "SmallTrade";

    private static final String  FILE_NAME = "100KB.json";

    public String getMetaGlobalKey(){
        return META_GLOBAL_KEY;
    }

    public String getMetaExternalKey(){
        return META_EXTERNAL_KEY;
    }

    public String getFileName(){
        return FILE_NAME;
    }

}
