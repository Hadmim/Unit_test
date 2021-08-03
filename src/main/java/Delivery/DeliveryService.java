package Delivery;

public interface DeliveryService {
    //delivery service
    boolean link_exist(String wilaya,String commune,String home,String GPS);//GPS compatible
    boolean link_point(String point,String GPS);//GPS compatible with point

}
