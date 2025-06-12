package io.reflectoring.demo.repository;

import io.reflectoring.demo.entity.DashboardStatus;
import io.reflectoring.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DashboardStatusRepository extends JpaRepository<DashboardStatus, Long> {

    Optional<DashboardStatus> findByUserId(String userId);
    List<DashboardStatus>findAll();
}
