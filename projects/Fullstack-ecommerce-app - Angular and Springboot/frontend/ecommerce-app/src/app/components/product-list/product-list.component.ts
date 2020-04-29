import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { ProductService } from 'src/app/services/product.service';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';
import { CartService } from 'src/app/services/cart.service';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})


export class ProductListComponent implements OnInit {

  thePageNumber: number = 1
  thePageSize: number = 10;
  theTotalElements: number = 0;

  products: Product[];

  private productService: ProductService;
  private activatedRoute: ActivatedRoute;
  private cartService: CartService;

  currentCategoryId: number;

  constructor(ps: ProductService, ar: ActivatedRoute, cs: CartService) {
    this.productService = ps;
    this.activatedRoute = ar;
    this.cartService = cs;
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(() => {
      this.listProducts();
    })
  }

  listProducts() {
    
    // check if "id" parameter is available
    const hasCategoryId: boolean = this.activatedRoute.snapshot.paramMap.has('id');


    if(hasCategoryId) {
      this.currentCategoryId = +this.activatedRoute.snapshot.paramMap.get('id'); 
    } else {
      this.currentCategoryId = 1;
    }

    this.productService.getProductList(this.currentCategoryId)
    .subscribe(
     data => {

       this.products = data;
     })
 //     this.processResult());


    }


  // get pagination info
  /*
  processResult() {

    return data => {
   
      this.thePageNumber = this.thePageNumber;  // two-way binding... current page number isnow assigned a new page number based on page number selected/clicked.. 

      
      let productsByCategory = data;
      this.theTotalElements = productsByCategory.length;
     // this.products = this.temp;

     // write up some notes to explain logic
      let temp = []; 
      let pageSizeofLastPage = this.theTotalElements % this.thePageSize;

      if (this.thePageNumber == 3) {

        for(let i = (this.thePageNumber*10)-10; i < ((this.thePageNumber*10)-10) + pageSizeofLastPage; i++) {
          temp.push(productsByCategory[i]);
        }
        this.products = temp;

      } else {

        for(let i = (this.thePageNumber*10)-10; i < this.thePageNumber*10; i++) {
          temp.push(productsByCategory[i]);
        }
  
        this.products = temp;


      }




  //    let pageSizeofLastPage = this.theTotalElements % this.thePageSize;
    //  let numberOfPages = this.theTotalElements / this.thePageSize;

      if (this.thePageNumber == 3) {
        this.thePageSize = 5;
        alert("test");
      } else {

        this.thePageSize = 10;
      }

    }

  } */



  addToCart(theProduct:Product) {

    console.log(`Adding to cart --> Product Name: ${theProduct.name}.. UnitPrice: ${theProduct.unitPrice}`);

    let theCartItem = new CartItem(theProduct);

    this.cartService.addToCart(theCartItem);
  }


}
