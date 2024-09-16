package vn.demo.starter.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.demo.starter.entity.Asset;
import vn.demo.starter.entity.User;
import vn.demo.starter.entity.UserCryptoWallet;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCryptoWalletRepository extends JpaRepository<UserCryptoWallet, Long> {

    Optional<UserCryptoWallet> findByUserAndAsset(User user, Asset asset);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT uw FROM UserCryptoWallet uw WHERE uw.user.id = :userId AND uw.asset.id = :coinId")
    Optional<UserCryptoWallet> lockByUserIdAndCoinId(Long userId, Long coinId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT uw FROM UserCryptoWallet uw WHERE uw.id = :id")
    Optional<UserCryptoWallet> lockById(Long id);

    List<UserCryptoWallet> findByUser(User user);
}
