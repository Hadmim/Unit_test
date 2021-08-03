package Order;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

public class OrderException {
    //order exception
    OrderService order;
    public boolean order_person(Map<Integer, Integer> product, String lastname, String firstname, String phone,
                                       String email, String total) throws Exception {
     //exception order if the buyer is a person
        double total_numeric;


        if(StringUtils.isBlank(firstname)|| StringUtils.isBlank(lastname)|| !(StringUtils.isNumeric(phone)) ||
               StringUtils.isBlank(email)||!NumberUtils.isCreatable(total)|| product.isEmpty()
        )
           throw new Exception("les information d'une commande doit être correct et pas vide");

        if(NumberUtils.isCreatable(total)){
            total_numeric=Double.parseDouble(total);
            if(total_numeric <0)
                throw new Exception("le montant doit être positive");
        }
        if(!order.mail_validate(email))
            throw new Exception("l'adresse email doit être correct et exist");
        if(!order.number_phone(phone))
            throw new Exception("le numéro de téléphone doit être valide ");

        for(int prod:product.keySet()){

          if(order.product_QT(prod)<product.get(prod))
              throw  new Exception("le produit "+prod+" insuffisant");
        }
        return true;
    }

    public  boolean order_enterprise(String name, String num_identf, String article, String addrs, String lien, Map<Integer, Integer> product,String total) throws Exception {
        //exception order if the buyer is a company
        double total_numeric;

        if(StringUtils.isBlank(name)|| StringUtils.isBlank(article)||
                StringUtils.isBlank(num_identf)||StringUtils.isBlank(lien)||
                StringUtils.isBlank(addrs)|| product.isEmpty()||!NumberUtils.isCreatable(total))
            throw new Exception("les information d'une commande doit être correct et pas vide");
        if(NumberUtils.isCreatable(total)){
            total_numeric=Double.parseDouble(total);
            System.out.println(total_numeric);
            if(total_numeric <0)
                throw new Exception("le montant doit être positive");
        }

        if(!order.enterprise_exist(name,num_identf,addrs,lien))
            throw new Exception("enterprise n'exist pas");

        for(int prod:product.keySet()){
            if(order.product_QT(prod)<product.get(prod))
                throw  new Exception("le produit "+prod+" insuffisant");
        }
        return true;

    }


}
