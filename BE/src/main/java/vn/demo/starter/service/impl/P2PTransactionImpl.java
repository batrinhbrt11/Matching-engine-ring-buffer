package vn.demo.starter.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import vn.demo.starter.entity.P2POrder;
import vn.demo.starter.entity.P2PTransaction;
import vn.demo.starter.entity.UserCryptoWallet;
import vn.demo.starter.entity.UserWallet;
import vn.demo.starter.repository.P2PTransactionRepository;
import vn.demo.starter.service.P2PTransactionService;
import vn.demo.starter.service.UserCryptoWalletService;
import vn.demo.starter.service.UserWalletService;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class P2PTransactionImpl implements P2PTransactionService {

    private final P2PTransactionRepository p2PTransactionRepository;

    private final UserWalletService userWalletService;
    private final UserCryptoWalletService userCryptoWalletService;


    /**
     * Create a new transaction
     * @param buyOrder
     * @param sellOrder
     * @param quantity
     */
    @Override
    @Transactional
    public void create(P2POrder buyOrder, P2POrder sellOrder, BigDecimal quantity) {
        P2PTransaction transaction = new P2PTransaction();
        transaction.setBuyOrder(buyOrder);
        transaction.setSellOrder(sellOrder);
        transaction.setAmount(quantity);
        transaction.setPrice(sellOrder.getPrice());

        //Freeze balance for buy user
        updateBalanceBuyUser(buyOrder,sellOrder,quantity);
        //Freeze balance for sell user
        updateBalanceSellUser(buyOrder,sellOrder,quantity);

        p2PTransactionRepository.save(transaction);
    }

    @Override
    public List<P2POrder> getOpenOrders() {
        return null;
    }

    /**
     * Update balance for buy user
     * @param buyOrder
     * @param sellOrder
     * @param quantity
     */
    private void updateBalanceBuyUser(P2POrder buyOrder, P2POrder sellOrder, BigDecimal quantity) {
        UserWallet wallet = userWalletService.getWalletByUser(buyOrder.getUser());
        UserCryptoWallet cryptoWallet = userCryptoWalletService.getWalletByUserAndAsset(buyOrder.getUser(),buyOrder.getAsset());

        BigDecimal totalAmount = quantity.multiply(sellOrder.getPrice());
        BigDecimal remainingBalance = BigDecimal.ZERO;

        // if buy quantity is enough, calculate remaining freeze balance and refund to available balance
        if(buyOrder.getTotalAmount().equals(quantity)){
            remainingBalance = wallet.getFreezeBalance().subtract(totalAmount);
            userWalletService.updateBalance(wallet.getId(), remainingBalance, wallet.getFreezeBalance().multiply(BigDecimal.valueOf(-1)));
        }
        // if buy quantity is not enough, reduce freeze balance
        else {
            userWalletService.updateBalance(wallet.getId(),BigDecimal.ZERO, totalAmount.multiply(BigDecimal.valueOf(-1)));
        }
        //add crypto balance for buy user
        userCryptoWalletService.updateBalance(cryptoWallet.getId(), quantity, BigDecimal.ZERO);
    }

    /**
     * Update balance for sell user
     * @param buyOrder
     * @param sellOrder
     * @param quantity
     */
    private void updateBalanceSellUser(P2POrder buyOrder, P2POrder sellOrder, BigDecimal quantity) {
        UserWallet wallet = userWalletService.getWalletByUser(sellOrder.getUser());
        UserCryptoWallet cryptoWallet = userCryptoWalletService.getWalletByUserAndAsset(sellOrder.getUser(),sellOrder.getAsset());

        BigDecimal totalAmount = quantity.multiply(sellOrder.getPrice());
        //add available balance for sale user
        userWalletService.updateBalance(wallet.getId(), totalAmount , BigDecimal.ZERO);
        //reduce freeze balance for sell user
        userCryptoWalletService.updateBalance(cryptoWallet.getId(),  BigDecimal.ZERO, quantity.multiply(BigDecimal.valueOf(-1)));

    }
}
