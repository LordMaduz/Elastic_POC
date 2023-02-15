package com.elastic.poc.model.trade;


public class LargeTrade extends  TradeEntity{

    private static final String  META_GLOBAL_KEY = "LargeTrade";
    private static final String  META_EXTERNAL_KEY = "LargeTrade";

    private static final String  FILE_NAME = "1MB.json";

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
