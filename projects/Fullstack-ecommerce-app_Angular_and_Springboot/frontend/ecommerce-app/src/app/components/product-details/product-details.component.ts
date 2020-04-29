import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/common/product';
import { CartItem } from 'src/app/common/cart-item';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product: Product = new Product();
  
  productService: ProductService;
  activatedRoute: ActivatedRoute;
  cartService: CartService;

  constructor(ps: ProductService, ar: ActivatedRoute, cs: CartService) {
    this.productService = ps;
    this.activatedRoute = ar;
    this.cartService = cs;
  }


  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe( () => {
      this.handleProductDetails();
    })
  }

  handleProductDetails() {

    const theProductId = parseInt(this.activatedRoute.snapshot.paramMap.get('id'));
    this.productService.getProductById(theProductId)
    .subscribe(data => {
      this.product = data;
    })
  }


  addToCart() {

    console.log(`Adding to cart --> Name: ${this.product.name}, unitPrice: ${this.product.unitPrice}`);

    const theCartItem = new CartItem(this.product);
    this.cartService.addToCart(theCartItem);

  }

}
