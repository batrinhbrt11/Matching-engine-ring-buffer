package vn.demo.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.demo.starter.entity.Chain;

@Repository
public interface ChainRepository extends JpaRepository<Chain, Long> {
}
