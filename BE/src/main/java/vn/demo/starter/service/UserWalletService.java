package vn.demo.starter.service;

import vn.demo.starter.entity.User;
import vn.demo.starter.entity.UserWallet;
import vn.demo.starter.service.dto.WalletDto;

import java.math.BigDecimal;

public interface UserWalletService {

    void createUserWallet(User user);

   UserWallet lockById(Long id);

    UserWallet getWalletByUser(User user);

    void updateBalance(Long userWalletId, BigDecimal availableBalance, BigDecimal freezeBalance);

    void checkAvailableBalance(Long userWalletId, BigDecimal neededBalance);

    WalletDto getWallet();
}
