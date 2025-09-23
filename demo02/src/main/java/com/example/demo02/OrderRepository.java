package com.example.demo02;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    void findByTotalPriceGreaterThan(double price);
    // select * from order wher total_price > ?

    @Query("select * from Order Where totalPrice > ?")
    void demo(double price);

}
