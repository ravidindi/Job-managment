package com.dindi.Jobapplication.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/companies/{companyId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long companyId){
        return ResponseEntity.ok(reviewService.getReviews(companyId));
    }

    @PostMapping
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody  Review review){
        boolean added= reviewService.addReview(companyId,review);
        if(added){
            return ResponseEntity.ok("Review added successfully");
        }
        else{
            return new ResponseEntity<>("Adding Review Failed for the company", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,@PathVariable Long id){
        Review review=reviewService.getReview(companyId,id);
        if(review!=null){
            return ResponseEntity.ok(review);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long id,@RequestBody Review review){
        boolean updated=reviewService.putReview(companyId,id,review);
        if(updated){
            return ResponseEntity.ok("Review updated successfully");
        }
        else{
            return new ResponseEntity<>("Updating review failed",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long id){
        boolean deleted=reviewService.deleteReview(companyId,id);
        if(deleted){
            return ResponseEntity.ok("Review deleted successfully");
        }
        else{
            return new ResponseEntity<>("Deleting review failed",HttpStatus.NOT_FOUND);
        }
    }
}
