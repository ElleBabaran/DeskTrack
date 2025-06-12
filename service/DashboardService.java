package io.reflectoring.demo.service;

import io.reflectoring.demo.entity.DashboardStatus;
import io.reflectoring.demo.repository.DashboardStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final DashboardStatusRepository dashboardStatusRepository;
    public DashboardService(DashboardStatusRepository dashboardStatusRepository) {
        this.dashboardStatusRepository = dashboardStatusRepository;
    }

   public List<DashboardStatus> getAllStatuses() {
        return dashboardStatusRepository.findAll();
   }
}
