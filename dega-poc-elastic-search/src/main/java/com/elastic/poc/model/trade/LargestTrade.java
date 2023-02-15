package com.elastic.poc.model.trade;



public class LargestTrade extends  TradeEntity{

    private static final String  META_GLOBAL_KEY = "LargestTrade";
    private static final String  META_EXTERNAL_KEY = "LargestTrade";

    private static final String  FILE_NAME = "2MB.json";

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
