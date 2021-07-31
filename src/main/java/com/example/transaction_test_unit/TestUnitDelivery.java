package com.example.transaction_test_unit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestUnitDelivery {
    //unit test of delivery exception
    @InjectMocks
     DeliveryException delivExp;
    @Mock
    DeliveryService delivServ;
    @Mock
    OrderService orderServ;

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void TestTrue_home_delivery_validate() throws Exception {
        //home delivery validate
        when(delivServ.link_exist("batna","chmara","rue 14","Gps")).thenReturn(true);
        when(orderServ.number_phone("125487")).thenReturn(true);
        when(orderServ.mail_validate("hh@gmail.com")).thenReturn(true);
        boolean delev = delivExp.home_delivery_validate("batna","chmara","2515","rue 14",
                "125487","hh@gmail.com","Gps") ;
        assertEquals(true,delev);
    }
    @Test
    public void TestExpVide_home_delivery_validate() throws Exception {
       // home delivery phone vide
        when(delivServ.link_exist("batna","chmara","rue 14","Gps")).thenReturn(true);
        when(orderServ.mail_validate("hh@jj.com")).thenReturn(true);
        when(orderServ.number_phone("")).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une commande doit être correct et pas vide");
        delivExp.home_delivery_validate("batna","chmara","2515","rue 14",
                "","hh@gmail.com","Gps") ;

    }
    @Test
    public void TestExpEmail_home_delivery_validate() throws Exception {
        //home delivery mail not valid
        when(delivServ.link_exist("batna","chmara","rue 14","Gps")).thenReturn(true);
        when(orderServ.mail_validate("hh@jj.com")).thenReturn(false);
        when(orderServ.number_phone("125487")).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("l'adresse email doit être correct et exist");
        delivExp.home_delivery_validate("batna","chmara","2515","rue 14",
                "125487","hh@gmail.com","Gps") ;

    }
    @Test
    public void TestExpPhone_home_delivery_validate() throws Exception {
        //home delivery phone not valid
        when(delivServ.link_exist("batna","chmara","rue 14","Gps")).thenReturn(true);
        when(orderServ.mail_validate("hh@gmail.com")).thenReturn(true);
        when(orderServ.number_phone("125487")).thenReturn(false);


        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le numéro de téléphone doit être valide ");
        delivExp.home_delivery_validate("batna","chmara","2515","rue 14",
                "125487","hh@gmail.com","Gps") ;

    }
    @Test
    public void TestExpGPS_home_delivery_validate() throws Exception {
        //home delivery GPS not compatible
        when(delivServ.link_exist("batna","chmara","rue 14","Gps")).thenReturn(false);
        when(orderServ.mail_validate("hh@gmail.com")).thenReturn(true);
        when(orderServ.number_phone("125487")).thenReturn(true);


        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Le lien GPS doit être compatible avec les informations saisies");
        delivExp.home_delivery_validate("batna","chmara","2515","rue 14",
                "125487","hh@gmail.com","Gps") ;

    }
    @Test
    public void TestTrue_Delivery_PtSr_validate() throws Exception {
        //delivery point or delivery point to the seller validate
        when(delivServ.link_point("El Hamma","C3RM+8R8, El Hamma")).thenReturn(true);
        when(orderServ.number_phone("125487")).thenReturn(true);
        when(orderServ.mail_validate("hh@gmail.com")).thenReturn(true);
        boolean delv = delivExp.Delivery_PtSr_validate("125487","hh@gmail.com","C3RM+8R8, El Hamma","El Hamma") ;
        assertEquals(true,delv);
    }
    @Test
    public void TestExpVide_elivery_PtSr_validate() throws Exception {
        //delivery point or delivery point to the seller mail vide
        when(delivServ.link_point("El Hamma","C3RM+8R8, El Hamma")).thenReturn(true);
        when(orderServ.mail_validate("")).thenReturn(false);
        when(orderServ.number_phone("125487")).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une commande doit être correct et pas vide");
        delivExp.Delivery_PtSr_validate("125487","","C3RM+8R8, El Hamma","El Hamma") ;

    }
    @Test
    public void TestExpEmail_Delivery_PtSr_validate() throws Exception {
        //delivery point or delivery point to the seller mail not valid
        when(delivServ.link_point("El Hamma","C3RM+8R8, El Hamma")).thenReturn(true);
        when(orderServ.mail_validate("hh@jj.com")).thenReturn(false);
        when(orderServ.number_phone("125487")).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("l'adresse email doit être correct et exist");
        delivExp.Delivery_PtSr_validate("125487","hh@gmail.com","C3RM+8R8, El Hamma","El Hamma") ;

    }
    @Test
    public void TestExpPhone_Delivery_PtSr_validate() throws Exception {
        //delivery point or delivery point to the seller phone not valid
        when(delivServ.link_point("El Hamma","C3RM+8R8, El Hamma")).thenReturn(true);
        when(orderServ.mail_validate("hh@gmail.com")).thenReturn(true);
        when(orderServ.number_phone("125487")).thenReturn(false);


        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le numéro de téléphone doit être valide ");
        delivExp.Delivery_PtSr_validate("125487","hh@gmail.com","C3RM+8R8,El Hamma","El Hamma") ;

    }
    @Test
    public void TestExpGPS_Delivery_PtSr_validate() throws Exception {
        ////delivery point or delivery point to the seller GPS not compatible

        when(orderServ.mail_validate("hh@gmail.com")).thenReturn(true);
        when(orderServ.number_phone("125487")).thenReturn(true);
        when(delivServ.link_point("batna","C3RM+8R8,El Hamma")).thenReturn(false);


        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Le lien GPS doit être compatible avec le point de livraison");
        delivExp.Delivery_PtSr_validate("125487","hh@gmail.com","C3RM+8R8,El Hamma","batna") ;

    }


}
