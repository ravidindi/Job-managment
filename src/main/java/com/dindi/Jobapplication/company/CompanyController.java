package com.dindi.Jobapplication.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){
        return ResponseEntity.ok(companyService.getCompanies());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
        boolean updated= companyService.updateCompany(company,id);
        if(updated){
            return ResponseEntity.ok("Company updated successfully");
        }
        else{
            return new ResponseEntity<>("Updating company Failed", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return ResponseEntity.ok("Company added successfully");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean updated= companyService.deleteCompany(id);
        if(updated){
            return ResponseEntity.ok("Company deleted successfully");
        }
        else{
            return new ResponseEntity<>("Deleting company Failed", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company=companyService.getCompany(id);
        if(company!=null){
            return ResponseEntity.ok(company);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
