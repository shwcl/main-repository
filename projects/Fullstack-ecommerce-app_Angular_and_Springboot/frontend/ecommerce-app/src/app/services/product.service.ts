import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { map }  from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ProductService {


  private baseUrl = 'http://localhost:8080/ecommerceapp/products';

  httpClient: HttpClient;

  constructor(hc: HttpClient ) {

    this.httpClient = hc;
  }

  //getProductList(): Observable<Product[]> {
   // return this.httpClient.get<GetResponse>(this.baseUrl).pipe(
   //   map(response => response._embedded.products)
  //  );



  
  getProductList(thecategoryId: number): Observable<Product[]> {

    const findByCategoryUrl = `${this.baseUrl}/category/${thecategoryId}`;

    return this.httpClient.get<Product[]>(findByCategoryUrl);
    //return this.httpClient.get<Product[]>(this.baseUrl + '/category/' + theCategoryId);
  }



  getProductById(theProductId: number): Observable<Product> {

    const productUrl = `${this.baseUrl}/${theProductId}`;

    return this.httpClient.get<Product>(productUrl);
    //return this.httpClient.get<Product[]>(this.baseUrl + '/category/' + theCategoryId);
  }



}



