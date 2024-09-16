package vn.demo.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.demo.starter.entity.P2POrder;
import vn.demo.starter.entity.enumeration.OrderStatus;

import java.util.List;

@Repository
public interface P2POrderRepository  extends JpaRepository<P2POrder, Long> {
    List<P2POrder> findByOrderStatusIn(List<OrderStatus> orderStatuses);
}
