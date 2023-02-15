package com.elastic.poc.model.trade;


import com.elastic.poc.constants.TradeSizeEnum;

public class TradeFactory {

    public static TradeEntity getTradeEntityType(TradeType tradeType){

        switch(TradeSizeEnum.valueOf(tradeType.getPayloadSize())) {
            case SMALL:
                return new SmallTrade();
            case MEDIUM:
                return new MediumTrade();
            case LARGE:
                return new LargeTrade();
            case LARGEST:
                return new LargestTrade();
            default:
                return new MiniTrade();
        }
    }
}
