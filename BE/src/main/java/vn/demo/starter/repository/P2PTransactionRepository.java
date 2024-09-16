package vn.demo.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.demo.starter.entity.P2POrder;
import vn.demo.starter.entity.P2PTransaction;

import java.util.List;

public interface P2PTransactionRepository extends JpaRepository<P2PTransaction, Long> {

}
