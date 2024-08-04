package com.dindi.Jobapplication.company.impl;

import com.dindi.Jobapplication.company.Company;
import com.dindi.Jobapplication.company.CompanyRepository;
import com.dindi.Jobapplication.company.CompanyService;
import com.dindi.Jobapplication.job.Job;
import com.dindi.Jobapplication.job.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    private final JobRepository jobRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, JobRepository jobRepository) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
    }


    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional=companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company1=companyOptional.get();
            company1.setDescription(company.getDescription());
            company1.setName(company.getName());
            company1.setJobsList(company.getJobsList());
            companyRepository.save(company1);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);

    }

    @Override
    public boolean deleteCompany(Long id) {
        Optional<Company> companyOptional=companyRepository.findById(id);
        if(companyOptional.isPresent()){
            List<Job> jobs= jobRepository.findAllByCompanyId(id);
            jobs=jobs.stream().peek(job -> job.setCompany(null)).collect(Collectors.toList());
            companyRepository.delete(companyOptional.get());
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
