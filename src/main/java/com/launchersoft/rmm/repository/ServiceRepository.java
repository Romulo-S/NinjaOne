package com.launchersoft.rmm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.launchersoft.rmm.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    boolean existsByServiceName(String name);
}
