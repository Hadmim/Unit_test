package Delivery;

import Order.OrderService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestDelivery {
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
    public void home_delivery_Test() throws Exception {
        //home delivery validate
        when(delivServ.link_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        when(orderServ.number_phone(anyString())).thenReturn(true);
        when(orderServ.mail_validate(anyString())).thenReturn(true);
        boolean delev = delivExp.home_delivery("batna","chmara","2515","rue 14",
                "125487","hh@gmail.com","Gps") ;
        assertEquals(true,delev);
    }
    @Test
    public void home_delivery_Test_infodelivery_empty() throws Exception {
       // home delivery phone vide
        when(delivServ.link_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        when(orderServ.mail_validate(anyString())).thenReturn(true);
        when(orderServ.number_phone(anyString())).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une commande doit être correct et pas vide");
        delivExp.home_delivery("batna","chmara","2515","rue 14",
                "","hh@gmail.com","Gps") ;

    }
    @Test
    public void home_delivery_Test_mail_not_valid() throws Exception {
        //home delivery mail not valid
        when(delivServ.link_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        when(orderServ.mail_validate(anyString())).thenReturn(false);
        when(orderServ.number_phone(anyString())).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("l'adresse email doit être correct et exist");
        delivExp.home_delivery("batna","chmara","2515","rue 14",
                "125487","hh@gmail.com","Gps") ;

    }
    @Test
    public void home_delivery_Test_phone_not_valid() throws Exception {
        //home delivery phone not valid
        when(delivServ.link_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        when(orderServ.mail_validate(anyString())).thenReturn(true);
        when(orderServ.number_phone(anyString())).thenReturn(false);


        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le numéro de téléphone doit être valide ");
        delivExp.home_delivery("batna","chmara","2515","rue 14",
                "125487","hh@gmail.com","Gps") ;

    }
    @Test
    public void home_delivery_Test_GPS_not_valid() throws Exception {
        //home delivery GPS not compatible
        when(delivServ.link_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(false);
        when(orderServ.mail_validate(anyString())).thenReturn(true);
        when(orderServ.number_phone(anyString())).thenReturn(true);


        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Le lien GPS doit être compatible avec les informations saisies");
        delivExp.home_delivery("batna","chmara","2515","rue 14",
                "125487","hh@gmail.com","Gps") ;

    }
    @Test
    public void Delivery_Point_Saller_Test() throws Exception {
        //delivery point or delivery point to the seller validate
        when(delivServ.link_point(anyString(),anyString())).thenReturn(true);
        when(orderServ.number_phone(anyString())).thenReturn(true);
        when(orderServ.mail_validate(anyString())).thenReturn(true);
        boolean delv = delivExp.Delivery_Point_Saller("125487","hh@gmail.com","C3RM+8R8, El Hamma","El Hamma") ;
        assertEquals(true,delv);
    }
    @Test
    public void Delivery_Point_Saller_Test_infodelivery_empty() throws Exception {
        //delivery point or delivery point to the seller :GPS vide
        when(delivServ.link_point(anyString(),anyString())).thenReturn(false);
        when(orderServ.number_phone(anyString())).thenReturn(true);
        when(orderServ.mail_validate(anyString())).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une commande doit être correct et pas vide");
        delivExp.Delivery_Point_Saller("125487","hh@gmail.com","","El Hamma") ;

    }
    @Test
    public void Delivery_Point_Saller_Test_mail_not_valid() throws Exception {
        //delivery point or delivery point to the seller mail not valid
        when(delivServ.link_point(anyString(),anyString())).thenReturn(true);
        when(orderServ.number_phone(anyString())).thenReturn(true);
        when(orderServ.mail_validate(anyString())).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("l'adresse email doit être correct et exist");
        delivExp.Delivery_Point_Saller("125487","hh@gmail.com","C3RM+8R8, El Hamma","El Hamma") ;

    }
    @Test
    public void Delivery_Point_Saller_Test_phone_not_valid() throws Exception {
        //delivery point or delivery point to the seller phone not valid
        when(delivServ.link_point(anyString(),anyString())).thenReturn(true);
        when(orderServ.number_phone(anyString())).thenReturn(false);
        when(orderServ.mail_validate(anyString())).thenReturn(true);


        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le numéro de téléphone doit être valide ");
        delivExp.Delivery_Point_Saller("125487","hh@gmail.com","C3RM+8R8,El Hamma","El Hamma") ;

    }
    @Test
    public void Delivery_Point_Saller_Test_GPS_not_valid() throws Exception {
        ////delivery point or delivery point to the seller GPS not compatible

        when(delivServ.link_point(anyString(),anyString())).thenReturn(false);
        when(orderServ.number_phone(anyString())).thenReturn(true);
        when(orderServ.mail_validate(anyString())).thenReturn(true);


        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Le lien GPS doit être compatible avec le point de livraison");
        delivExp.Delivery_Point_Saller("125487","hh@gmail.com","C3RM+8R8,El Hamma","batna") ;

    }


}
