package com.example.transaction_test_unit;

public interface DeliveryService {
    //delivery service
    boolean link_exist(String wilaya,String commune,String home,String GPS);
    boolean link_point(String point,String GPS);

}
