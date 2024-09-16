package vn.demo.starter.service;

import vn.demo.starter.entity.Asset;
import vn.demo.starter.entity.User;
import vn.demo.starter.entity.UserCryptoWallet;
import vn.demo.starter.service.dto.WalletDto;

import java.math.BigDecimal;
import java.util.List;

public interface UserCryptoWalletService {

    void createCryptoWallet(User user);

    UserCryptoWallet lockById(Long id);

    UserCryptoWallet getWalletByUserAndAsset(User user, Asset asset);

    void updateBalance(Long userWalletId, BigDecimal availableBalance, BigDecimal freezeBalance);

    void checkAvailableBalance(Long userWalletId, BigDecimal neededBalance);
    List<WalletDto> getWallets();
}
