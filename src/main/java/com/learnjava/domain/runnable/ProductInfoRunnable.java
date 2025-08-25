package com.learnjava.domain.runnable;

import com.learnjava.domain.ProductInfo;
import com.learnjava.service.ProductInfoService;
import lombok.Getter;

public class ProductInfoRunnable implements Runnable {

  @Getter
  private ProductInfo productInfo;
  private String productId;
  private ProductInfoService productInfoService;

  public ProductInfoRunnable(String productId, ProductInfoService productInfoService) {
    this.productId = productId;
    this.productInfoService = productInfoService;
  }

  @Override
  public void run() {
    productInfo = productInfoService.retrieveProductInfo(productId);
  }

}
