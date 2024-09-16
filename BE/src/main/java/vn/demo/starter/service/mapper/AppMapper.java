package vn.demo.starter.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.demo.starter.entity.Asset;
import vn.demo.starter.entity.Chain;
import vn.demo.starter.entity.Coin;
import vn.demo.starter.entity.P2POrder;
import vn.demo.starter.entity.UserCryptoWallet;
import vn.demo.starter.entity.UserWallet;
import vn.demo.starter.service.dto.AssetDto;
import vn.demo.starter.service.dto.ChainDto;
import vn.demo.starter.service.dto.CoinDto;
import vn.demo.starter.service.dto.P2POrderDto;
import vn.demo.starter.service.dto.WalletDto;
import vn.demo.starter.util.BalanceUtils;

@Component
@RequiredArgsConstructor
public class AppMapper {

    public CoinDto toDto(Coin coin) {
        return new CoinDto(
                coin.getId(),
                coin.getSymbol(),
                coin.getImage(),
                coin.getName()
        );
    }

    public ChainDto toDto(Chain chain) {
        return new ChainDto(
                chain.getId(),
                chain.getSymbol(),
                chain.getImage(),
                chain.getName()
        );
    }

    public AssetDto toDto(Asset asset) {
        return new AssetDto(
                asset.getId(),
                this.toDto(asset.getCoin()),
                asset.isNativeToken(),
                this.toDto(asset.getChain())
        );
    }

    public P2POrderDto toDto(P2POrder p2pOrder) {
        return new P2POrderDto(
                p2pOrder.getId(),
                p2pOrder.getOrderType(),
                p2pOrder.getOrderStatus(),
                BalanceUtils.toString(p2pOrder.getPrice()),
                BalanceUtils.toString(p2pOrder.getRemainingAmount()),
                BalanceUtils.toString(p2pOrder.getTotalAmount()),
                this.toDto(p2pOrder.getAsset())
        );
    }

    public WalletDto toDto(UserWallet userWallet) {
        return new WalletDto(
                userWallet.getId(),
                BalanceUtils.toString(userWallet.getAvailableBalance()),
                BalanceUtils.toString(userWallet.getFreezeBalance()),
                null
        );
    }

    public WalletDto toDto(UserCryptoWallet cryptoWallet) {
        return new WalletDto(
                cryptoWallet.getId(),
                BalanceUtils.toString(cryptoWallet.getAvailableBalance()),
                BalanceUtils.toString(cryptoWallet.getFreezeBalance()),
                toDto(cryptoWallet.getAsset())
        );
    }
}
