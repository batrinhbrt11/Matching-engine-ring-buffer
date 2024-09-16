package vn.demo.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.demo.starter.entity.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
}
