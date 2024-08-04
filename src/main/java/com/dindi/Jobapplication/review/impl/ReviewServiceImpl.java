package com.dindi.Jobapplication.review.impl;

import com.dindi.Jobapplication.company.Company;
import com.dindi.Jobapplication.company.CompanyRepository;
import com.dindi.Jobapplication.review.Review;
import com.dindi.Jobapplication.review.ReviewRepository;
import com.dindi.Jobapplication.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Review> getReviews(Long companyId) {
        Optional<Company> company=companyRepository.findById(companyId);
        return company.map(Company::getReviews).orElse(null);

    }

    @Override
    public boolean addReview(Long companyId,Review review) {
        Optional<Company> company=companyRepository.findById(companyId);
        if(company.isPresent()){
            review.setCompany(company.get());
            System.out.println(review);
            reviewRepository.save(review);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Review getReview(Long companyId,Long id) {
        Optional<Company> company=companyRepository.findById(companyId);
        if(company.isPresent()){
            Company company1=company.get();
            return company1.getReviews().stream().filter(review1 -> review1.getId().equals(id)).findFirst().orElse(null);
        }
        else{
            return null;
        }
    }

    @Override
    public boolean putReview(Long companyId,Long id, Review review) {
        Optional<Company> company=companyRepository.findById(companyId);
        if(company.isPresent()){
            Company company1=company.get();
            Review review1= company1.getReviews().stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
            if(review1!=null){
                review1.setCompany(companyRepository.findById(companyId).orElse(null));
                review1.setRating(review.getRating());
                review1.setTitle(review.getTitle());
                review1.setRating(review.getRating());
                reviewRepository.save(review1);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId,Long id) {
        Optional<Company> company=companyRepository.findById(companyId);
        if(company.isPresent()){
            Company company1=company.get();
            Review review1= company1.getReviews().stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
            if(review1!=null){
                reviewRepository.delete(review1);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
}
