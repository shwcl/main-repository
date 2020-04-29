import { Injectable } from '@angular/core';
import { CartItem } from '../common/cart-item';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})


export class CartService {

  cartItems: CartItem[] = [];

  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();

  addToCart(cartItem: CartItem) {

    let alreadyExistsInCart: boolean = false;
    let existingCartItem: CartItem = undefined;

    console.log("location --> cart service method 0- computer card totals...");


    // if length of cartItems > 0
    if (this.cartItems.length > 0) {
      for(let tempCartItem of this.cartItems) {
        if(tempCartItem.id === cartItem.id) {
          existingCartItem = tempCartItem;
          alreadyExistsInCart = true;
          break;
        }
      }
 
   //   alreadyExistsInCart = (existingCartItem != undefined); 
    }

      
    if(alreadyExistsInCart) {
      existingCartItem.quantity = existingCartItem.quantity + 1;
    } else {
      this.cartItems.push(cartItem);
    }
      
    this.computeCartTotals();

  }


  computeCartTotals() {

    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for(let cartItem of this.cartItems) {

      totalPriceValue = totalPriceValue + (cartItem.unitPrice * cartItem.quantity);
      totalQuantityValue = totalQuantityValue + cartItem.quantity;

    }

    //publish the new values so they become eligible for subscription.. all subscribers will receive these values
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);


    //log cart data for debuggind purpose
    this.logCartData(totalPriceValue, totalQuantityValue);
    

  }


  logCartData(totalPriceValue: number, totalQuantity: number) {
    console.log("Content of the cart....");

    for(let tempCartItem of this.cartItems) {
      const subtotal = tempCartItem.unitPrice * tempCartItem.quantity;
      console.log(`name: ${tempCartItem.name}, quantity: ${tempCartItem.quantity}, unitPrice: ${tempCartItem.unitPrice}, subTotal: ${subtotal}`); 
    }

    console.log(`Total value: ${totalPriceValue.toFixed(2)}, Total Quantity: ${totalQuantity}`);
  }


  decrementQuantity(theCartItem: CartItem) {

    theCartItem.quantity = theCartItem.quantity - 1;

    if(theCartItem.quantity === 0) {

      this.removeFromCart(theCartItem);

    } else {

      this.computeCartTotals();
    }


  }


  removeFromCart(theCartItem: CartItem) {

    const itemIndex = this.cartItems.findIndex(tempCartItem => tempCartItem == theCartItem);

    if(itemIndex > -1) {
      
      this.cartItems.splice(itemIndex, 1);  // remove one item from the array

      this.computeCartTotals();
    }



    
  }


  

  
  constructor() {

   }
}
