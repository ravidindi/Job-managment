package com.dindi.Jobapplication.job;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findAllByCompanyId(Long id);
}
