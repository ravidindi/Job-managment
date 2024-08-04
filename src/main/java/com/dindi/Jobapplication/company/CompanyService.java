package com.dindi.Jobapplication.company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanies();

    boolean updateCompany(Company company,Long id);

    void createCompany(Company company);

    boolean deleteCompany(Long id);

    Company getCompany(Long id);
}
