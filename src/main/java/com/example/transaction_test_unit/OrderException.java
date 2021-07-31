package com.example.transaction_test_unit;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderException {
    //order exception
    OrderService order;
    public boolean order_person_validate(Map<Integer, Integer> product, String lastname, String firstname, String phone,
                                       String email, String totale) throws Exception {
     //exception order if the buyer is a person

        if(StringUtils.isBlank(firstname)|| StringUtils.isBlank(lastname)|| !(StringUtils.isNumeric(phone)) ||
               StringUtils.isBlank(email)||StringUtils.isBlank(totale)|| product.isEmpty()
        )
           throw new Exception("les information d'une commande doit être correct et pas vide");
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

    public  boolean order_entrp_validate(String name, String num_identf, String article, String addrs, String lien, Map<Integer, Integer> product) throws Exception {
        //exception order if the buyer is a company

        if(StringUtils.isBlank(name)|| StringUtils.isBlank(article)||
                StringUtils.isBlank(num_identf)||StringUtils.isBlank(lien)||
                StringUtils.isBlank(addrs)|| product.isEmpty())
            throw new Exception("les information d'une commande doit être correct et pas vide");

        if(!order.enterprise_exist(name,num_identf,addrs,lien))
            throw new Exception("enterprise n'exist pas");

        for(int prod:product.keySet()){
            if(order.product_QT(prod)<product.get(prod))
                throw  new Exception("le produit "+prod+" insuffisant");
        }
        return true;

    }


}
