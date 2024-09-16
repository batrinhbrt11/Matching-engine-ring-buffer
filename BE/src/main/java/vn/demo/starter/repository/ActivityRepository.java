package vn.demo.starter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.demo.starter.entity.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, Long> {
}
