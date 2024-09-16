package vn.demo.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.demo.starter.entity.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
}
