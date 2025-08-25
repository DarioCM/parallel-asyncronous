package com.learnjava.service.thread;

import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.domain.Review;
import com.learnjava.domain.runnable.ProductInfoRunnable;
import com.learnjava.domain.runnable.ReviewRunnable;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ProductService;
import com.learnjava.service.ReviewService;

public class ProductServiceUsingThread {
  private ProductInfoService productInfoService;
  private ReviewService reviewService;

  public ProductServiceUsingThread(ProductInfoService productInfoService, ReviewService reviewService) {
    this.productInfoService = productInfoService;
    this.reviewService = reviewService;
  }

  public Product retrieveProductDetails(String productId) throws InterruptedException {
    stopWatch.start();

    //Thread runnable
    ProductInfoRunnable productInfoRunnable = new ProductInfoRunnable(productId, productInfoService);
    Thread productInfoThread = new Thread(productInfoRunnable);

    ReviewRunnable reviewRunnable = new ReviewRunnable(productId, reviewService);
    Thread reviewThread = new Thread(reviewRunnable);

    productInfoThread.start();
    reviewThread.start();

    productInfoThread.join();
    reviewThread.join();

    ProductInfo productInfo = productInfoRunnable.getProductInfo();
    Review review = reviewRunnable.getReview();


    stopWatch.stop();
    log("Total Time Taken : "+ stopWatch.getTime());
    return new Product(productId, productInfo, review);
  }

  public static void main(String[] args) {

    ProductInfoService productInfoService = new ProductInfoService();
    ReviewService reviewService = new ReviewService();
    ProductService productService = new ProductService(productInfoService, reviewService);
    String productId = "ABC123";
    Product product = productService.retrieveProductDetails(productId);
    log("Product is " + product);

  }
}
