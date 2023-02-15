package com.elastic.poc.model.trade;




public class MediumTrade extends TradeEntity {

    private static String description="";
    private static final String  META_GLOBAL_KEY = "MediumTrade";
    private static final String  META_EXTERNAL_KEY = "MediumTrade";

    private static final String FILE_NAME  = "500KB.json";

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
