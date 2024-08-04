package com.dindi.Jobapplication.job.impl;

import com.dindi.Jobapplication.job.Job;
import com.dindi.Jobapplication.job.JobRepository;
import com.dindi.Jobapplication.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(long id) {
       return  jobRepository.findById(id).orElse(null);
    }

    @Override
    public void createJob(Job job) {
    jobRepository.save(job);
    }

    @Override
    public boolean updateJob(Job updatedJob, long id) {
        Optional<Job> jobOptional=jobRepository.findById(id);
            if (jobOptional.isPresent()) {
                Job job=jobOptional.get();
                job.setDescription(updatedJob.getDescription());
                job.setLocation(updatedJob.getLocation());
                job.setTitle(updatedJob.getTitle());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                jobRepository.save(job);
                return true;
            }
        return false;
    }

    @Override
    public boolean deleteJob(long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
}
