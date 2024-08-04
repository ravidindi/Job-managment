package com.dindi.Jobapplication.job;

import java.util.List;

public interface JobService {

    List<Job> findAllJobs();

    Job getJobById(long id);

    void createJob(Job job);

    boolean updateJob(Job job, long id);

    boolean deleteJob(long id);


}
