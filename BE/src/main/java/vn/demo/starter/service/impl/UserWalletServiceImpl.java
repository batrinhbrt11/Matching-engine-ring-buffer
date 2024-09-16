package vn.demo.starter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.demo.starter.entity.User;
import vn.demo.starter.entity.UserWallet;
import vn.demo.starter.exception.BadRequestException;
import vn.demo.starter.repository.UserWalletRepository;
import vn.demo.starter.security.SecurityUtils;
import vn.demo.starter.service.UserService;
import vn.demo.starter.service.UserWalletService;
import vn.demo.starter.service.dto.WalletDto;
import vn.demo.starter.service.mapper.AppMapper;
import vn.demo.starter.util.BalanceUtils;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserWalletServiceImpl implements UserWalletService {

    private final UserWalletRepository userWalletRepository;
    private final UserService userService;
    private final AppMapper appMapper;

    @Override
    @Transactional
    public void createUserWallet(User user) {
        log.info("Start create the user wallets for user: {}", user.getId());
        userWalletRepository.save(new UserWallet(user));
        log.info("Create user wallets done.");
    }

    @Override
    @Transactional
    public UserWallet lockById(Long id) {
        return userWalletRepository
                .lockById(id)
                .orElseThrow(() -> new BadRequestException("User Wallet not exist"));
    }

    @Override
    public UserWallet getWalletByUser(User user) {
        return userWalletRepository
                .findByUser(user)
                .orElseThrow(() -> new BadRequestException("User Wallet not exist"));
    }

    @Override
    @Transactional
    public void updateBalance(Long userWalletId, BigDecimal availableBalance, BigDecimal freezeBalance) {
        UserWallet userWallet = this.lockById(userWalletId);
        userWallet.setAvailableBalance(userWallet.getAvailableBalance().add(availableBalance));
        userWallet.setFreezeBalance(userWallet.getFreezeBalance().add(freezeBalance));
    }

    @Override
    public void checkAvailableBalance(Long userWalletId, BigDecimal neededBalance) {
        UserWallet userWallet = userWalletRepository.findById(userWalletId)
                .orElseThrow(() -> new BadRequestException("User Wallet not exist"));

        if(BalanceUtils.isLT(userWallet.getAvailableBalance(), neededBalance)){
            throw  new BadRequestException("Insufficient balance");
        }
    }

    @Override
    public WalletDto getWallet() {
        User user = userService.getUser(SecurityUtils.getCurrentUserId());
        return appMapper.toDto(getWalletByUser(user));
    }


}
