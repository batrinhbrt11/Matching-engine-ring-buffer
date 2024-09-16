package vn.demo.starter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.demo.starter.entity.Asset;
import vn.demo.starter.entity.User;
import vn.demo.starter.entity.UserCryptoWallet;
import vn.demo.starter.exception.BadRequestException;
import vn.demo.starter.repository.UserCryptoWalletRepository;
import vn.demo.starter.security.SecurityUtils;
import vn.demo.starter.service.AssetService;
import vn.demo.starter.service.UserCryptoWalletService;
import vn.demo.starter.service.UserService;
import vn.demo.starter.service.dto.WalletDto;
import vn.demo.starter.service.mapper.AppMapper;
import vn.demo.starter.util.BalanceUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserCryptoWalletServiceImpl implements UserCryptoWalletService {

    private final UserCryptoWalletRepository userCryptoWalletRepository;

    private final AssetService assetService;
    private final UserService userService;
    private final AppMapper appMapper;

    @Override
    @Transactional
    public void createCryptoWallet(User user) {
        assetService.getAsset().forEach(asset -> {
            UserCryptoWallet userCryptoWallet = getWalletOrCreate(user,asset);
            userCryptoWalletRepository.save(userCryptoWallet);
        });
    }

    @Override
    @Transactional
    public UserCryptoWallet lockById(Long id) {
        return userCryptoWalletRepository
                .lockById(id)
                .orElseThrow(() -> new BadRequestException("Wallet not exist"));
    }

    @Override
    public UserCryptoWallet getWalletByUserAndAsset(User user, Asset asset) {
        return userCryptoWalletRepository
                .findByUserAndAsset(user, asset)
                .orElseThrow(() -> new BadRequestException("Wallet not exist"));
    }

    @Override
    @Transactional
    public void updateBalance(Long userWalletId, BigDecimal availableBalance, BigDecimal freezeBalance) {
        UserCryptoWallet userCryptoWallet = lockById(userWalletId);
        userCryptoWallet.setAvailableBalance(userCryptoWallet.getAvailableBalance().add(availableBalance));
        userCryptoWallet.setFreezeBalance(userCryptoWallet.getFreezeBalance().add(freezeBalance));
    }

    @Override
    public void checkAvailableBalance(Long userWalletId, BigDecimal neededBalance) {
        UserCryptoWallet userCryptoWallet = userCryptoWalletRepository
                .findById(userWalletId)
                .orElseThrow(() -> new BadRequestException("Wallet not exist"));
        if (BalanceUtils.isLT(userCryptoWallet.getAvailableBalance(), neededBalance)){
            throw  new BadRequestException("Insufficient balance");
        }
    }

    private UserCryptoWallet getWalletOrCreate(User user, Asset asset) {
        return userCryptoWalletRepository
                .findByUserAndAsset(user,asset).orElse(new UserCryptoWallet(user,asset));
    }

    @Override
    public List<WalletDto> getWallets() {
        User user = userService.getUser(SecurityUtils.getCurrentUserId());
        return userCryptoWalletRepository
                .findByUser(user)
                .stream()
                .map(appMapper::toDto)
                .toList();
    }
}
