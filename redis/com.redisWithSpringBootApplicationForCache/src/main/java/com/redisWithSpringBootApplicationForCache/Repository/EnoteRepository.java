package com.redisWithSpringBootApplicationForCache.Repository;

import com.redisWithSpringBootApplicationForCache.entities.Enote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnoteRepository extends JpaRepository<Enote,Integer> {
}
