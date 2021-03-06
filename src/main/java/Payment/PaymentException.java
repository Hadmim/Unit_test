package Payment;

import org.apache.commons.lang3.StringUtils;

public class PaymentException {
    //payment exception
    PaymentService paym;
    public boolean payment_delivery_home(String wilaya, String commune, String c_postal, String home, String phone, String mail,

                                          String GPS,String daily,String trnsp, String num_cmd, String total, String tva,String ttc) throws Exception {

        //exception payment in the case of delivery home
        if(StringUtils.isBlank(wilaya)|| StringUtils.isBlank(commune)||!(StringUtils.isNumeric(c_postal))||
                StringUtils.isBlank(home)||!(StringUtils.isNumeric(phone))||
                StringUtils.isBlank(mail)||StringUtils.isBlank(GPS)||!(StringUtils.isNumeric(daily))||!(StringUtils.isNumeric(trnsp))||StringUtils.isBlank(num_cmd)||
                !StringUtils.isNumeric(total)
        ||!StringUtils.isNumeric(tva)||!StringUtils.isNumeric(ttc))
            throw new Exception("les information d'une paiement doit être correct et pas vide");

        if(!paym.order_exist(num_cmd,total,tva,ttc))
            throw new Exception("cette commande pas existé");
        if(!paym.delivery_home_exist(wilaya,commune,c_postal,home,GPS,phone,mail,daily,trnsp))
            throw  new Exception("cette livraison pas existé");
      return true;
    }

    public boolean payment_delivery_point_saller( String phone, String mail,String point, String GPS,String daily,
                                             String num_cmd, String total, String tva,String ttc) throws Exception {


        //exception payment in the case of delivery point or delivery point to the seller
        if(!(StringUtils.isNumeric(phone))||  StringUtils.isBlank(point)||
                StringUtils.isBlank(mail)||StringUtils.isBlank(GPS)||!StringUtils.isNumeric(daily)||StringUtils.isBlank(num_cmd)||!StringUtils.isNumeric(total)
                ||!StringUtils.isNumeric(tva)||!StringUtils.isNumeric(ttc))
            throw new Exception("les information d'une paiement doit être correct et pas vide");

        if(!paym.order_exist(num_cmd,total,tva,ttc))
            throw new Exception("cette commande pas existé");
        if(!paym.delivery_point_saller_exist(point,GPS,phone,mail,daily))
            throw  new Exception("cette livraison pas existé");
        return true;
    }
}
