package Delivery;

import Order.OrderService;
import org.apache.commons.lang3.StringUtils;

public class DeliveryException {
    //delivery exception
    OrderService order;
    DeliveryService delivery;
    public boolean home_delivery(String wilaya,String commune,String c_postal,String home,String phone,String mail,String GPS) throws Exception {
        //exception in the case of home delivery
        if(StringUtils.isBlank(wilaya)|| StringUtils.isBlank(commune)||!(StringUtils.isNumeric(c_postal))||
                StringUtils.isBlank(home)||!(StringUtils.isNumeric(phone))||
                StringUtils.isBlank(mail)||StringUtils.isBlank(GPS))
            throw new Exception("les information d'une commande doit être correct et pas vide");
        if(!order.mail_validate(mail))
            throw new Exception("l'adresse email doit être correct et exist");
        if(!order.number_phone(phone))
            throw new Exception("le numéro de téléphone doit être valide ");
        if(!delivery.link_exist(wilaya,commune,home,GPS))
            throw  new Exception("Le lien GPS doit être compatible avec les informations saisies");


        return true;
    }
    public boolean Delivery_Point_Saller(String phone,String mail,String GPS,String point) throws Exception {
        //exception in the case of delivery point or delivery point to the seller
        if(!(StringUtils.isNumeric(phone))||StringUtils.isBlank(mail)||StringUtils.isBlank(GPS))
            throw new Exception("les information d'une commande doit être correct et pas vide");
        if(!order.mail_validate(mail))
            throw new Exception("l'adresse email doit être correct et exist");
        if(!order.number_phone(phone))
            throw new Exception("le numéro de téléphone doit être valide ");
        if(!delivery.link_point(point,GPS))
            throw  new Exception("Le lien GPS doit être compatible avec le point de livraison");


        return true;
    }


}
