package vn.demo.starter.repository;

import java.util.Optional;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.demo.starter.entity.User;
import vn.demo.starter.entity.UserWallet;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT uw FROM UserWallet uw WHERE uw.user.id = :userId")
    Optional<UserWallet> lockByUserId(Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT uw FROM UserWallet uw WHERE uw.id = :id")
    Optional<UserWallet> lockById(Long id);

    Optional<UserWallet> findByUser(User user);
}
