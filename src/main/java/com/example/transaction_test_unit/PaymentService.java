package com.example.transaction_test_unit;

public interface PaymentService
{
    //payment service
    boolean order_exist(String num,String total,String tva,String ttc);
    boolean delivery_exist_pv(String point,String gps,String phone,String mail,String daily);
    boolean delivery_exist(String wilaya,String commune,String c_post,String home,String gps,String phone,String mail,String daily,String trnsp);
}
