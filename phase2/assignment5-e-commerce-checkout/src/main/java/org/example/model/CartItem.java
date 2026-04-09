package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CartItem {
   private Product product;
   private int quantity;

   @Override
   public String toString() {
      return "CartItem{" +
              "product=" + product.getName() +
              ", quantity=" + quantity +
              '}';
   }
}
