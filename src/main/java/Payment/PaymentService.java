package Payment;

public interface PaymentService
{
    //payment service
    boolean order_exist(String num,String total,String tva,String ttc);
    boolean delivery_point_saller_exist(String point,String gps,String phone,String mail,String daily);//delivery point or delivery point to the seller
    boolean delivery_home_exist(String wilaya,String commune,String c_post,String home,String gps,String phone,String mail,String daily,String trnsp);
}
