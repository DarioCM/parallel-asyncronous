package com.learnjava.domain.runnable;

import com.learnjava.domain.Review;
import com.learnjava.service.ReviewService;

public class ReviewRunnable implements Runnable {

  private String productId;
  private Review review;
  private ReviewService reviewService;

  public ReviewRunnable(String productId, ReviewService reviewService) {
    this.productId = productId;
    this.reviewService = reviewService;
  }

  @Override
  public void run() {
    review = reviewService.retrieveReviews(productId);
  }

  public Review getReview() {
    return review;
  }
}
