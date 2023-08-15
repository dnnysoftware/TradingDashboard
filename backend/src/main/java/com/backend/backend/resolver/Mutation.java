package com.backend.backend.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.backend.backend.model.Asset;
import com.backend.backend.model.Trade;
import com.backend.backend.repository.AssetRepository;
import com.backend.backend.repository.TradeRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;


@Controller
public class Mutation implements GraphQLMutationResolver {
    

    private AssetRepository assetRepository;
    private TradeRepository tradeRepository;
  
    @Autowired
    public Mutation(AssetRepository assetRepository, TradeRepository tradeRepository) {
      this.assetRepository = assetRepository;
      this.tradeRepository = tradeRepository;
    }

    @MutationMapping
    public Asset createAsset( @Argument String name, @Argument String ticker, @Argument String marketSector, @Argument String assetType, @Argument String stockType) {
        Asset asset = new Asset(name, ticker, marketSector, assetType, stockType);
        return assetRepository.save(asset);
    }

    @MutationMapping
    public Trade createTrade(@Argument float price ,@Argument int amountShares,@Argument String tradeType,@Argument String orderType,@Argument String asset, @Argument boolean active) {
        Trade temp = new Trade(asset);
        Asset obj = getAsset(temp);
        Trade trade = new Trade(price, amountShares, tradeType, orderType, obj, active);
        return tradeRepository.save(trade);
    }

    public Asset getAsset(@Argument Trade trade) {
        return this.assetRepository.findById(trade.getAssetID()).orElseThrow(null);
    }


}
