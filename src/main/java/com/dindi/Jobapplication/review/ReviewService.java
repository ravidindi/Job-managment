package com.dindi.Jobapplication.review;
import java.util.List;
public interface ReviewService {
    List<Review> getReviews(Long companyId);

    boolean addReview(Long companyId,Review review);

    Review getReview(Long companyId,Long id);

    boolean putReview(Long companyId,Long id, Review review);

    boolean deleteReview(Long companyId,Long id);


}
