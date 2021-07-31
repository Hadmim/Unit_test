package com.example.transaction_test_unit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestUnitOrder {
    //unit test of order exception
    @InjectMocks
    OrderException orderExp;
    @Mock
    OrderService orderServ;
    Map<Integer, Integer> product=new HashMap<>();



    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void TestTrue_order_person_validate() throws Exception {
        //buyer is a person order valid
        when(orderServ.mail_validate("hh@jj.com")).thenReturn(true);
        when(orderServ.product_QT(1)).thenReturn(250);
        when(orderServ.number_phone("125487")).thenReturn(true);
        product.put(1,100);

        boolean orderprsn = orderExp.order_person_validate(product,"jo","krt","125487","hh@jj.com","8457");
        assertEquals(true, orderprsn);
    }
    @Test
    public void TestExpEmail_order_person_validate() throws Exception {
        //buyer is a person mail not valid
        when(orderServ.mail_validate("hh@jj.com")).thenReturn(false);
        when(orderServ.product_QT(1)).thenReturn(2000);
        when(orderServ.number_phone("125487")).thenReturn(true);

        product.put(1,1100);
         exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("l'adresse email doit être correct et exist");
        orderExp.order_person_validate(product,"jo","krt","125487","hh@jj.com","8457");

    }
    @Test
    public void TestExpPhone_order_person_validate() throws Exception {
        //buyer is a person phone not valid
        when(orderServ.mail_validate("hh@jj.com")).thenReturn(true);
        when(orderServ.product_QT(1)).thenReturn(2000);
        when(orderServ.number_phone("125487")).thenReturn(false);

        product.put(1,1100);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le numéro de téléphone doit être valide ");
        orderExp.order_person_validate(product,"jo","krt","125487","hh@jj.com","8457");

    }
    @Test
    public void TestExpInfoVide_order_person_validate() throws Exception {
        //buyer is a person product is empty
        when(orderServ.mail_validate("hh@jj.com")).thenReturn(true);
        when(orderServ.number_phone("125487")).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une commande doit être correct et pas vide");
        orderExp.order_person_validate(product,"jo","krt","125487","hh@3.jj.com","8457");

    }
    @Test
    public void TestExpProductQt_order_person_validate() throws Exception {
        // buyer is a person product quntity insufficient
        when(orderServ.number_phone("125487")).thenReturn(true);
        when(orderServ.mail_validate("hh@jj.com")).thenReturn(true);
        when(orderServ.product_QT(1)).thenReturn(200);
        product.put(1,1100);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le produit "+1+" insuffisant");
        orderExp.order_person_validate(product,"jo","krt","125487","hh@jj.com","8457");

    }
    @Test
    public void TestTrue_Order_entrp_validate() throws Exception {
        //buyer is a company order validate
        when(orderServ.enterprise_exist("enterprisname","008542001","rue 14","entrp.dz")).thenReturn(true);
        when(orderServ.product_QT(1)).thenReturn(2000);

        product.put(1,1100);

        boolean orderentrp =orderExp.order_entrp_validate("enterprisname","008542001","text","rue 14","entrp.dz",product);
        assertEquals(true, orderentrp);    }
    @Test
    public void TestExpInfoVide_Order_entrp_validate() throws Exception {
        // buyer is a company phone not valid
        when(orderServ.enterprise_exist("enterprisname","008542001","rue 14","entrp.dz")).thenReturn(true);
        when(orderServ.product_QT(1)).thenReturn(2000);
        product.put(1,1100);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une commande doit être correct et pas vide");
        orderExp.order_person_validate(product,"jo","krt","had","hh@3.jj.com","8457");

    }
    @Test
    public void TestEnerpInexist_Order_entrp_validate() throws Exception {
        //buyer is a company enterprise_dose note exist
        when(orderServ.enterprise_exist("enterprisname","008542001","rue 14","entrp.dz")).thenReturn(false);
        when(orderServ.product_QT(1)).thenReturn(2000);
        product.put(1,1100);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("enterprise n'exist pas");
        orderExp.order_entrp_validate("enterprisname","008542001","text","rue 14","entrp.dz",product);

    }
    @Test
    public void TestExpProductQt_Order_entrp_validate() throws Exception {
        // buyer is a company product quntity insufficient
        when(orderServ.enterprise_exist("enterprisname","008542001","rue 14","entrp.dz")).thenReturn(true);
        when(orderServ.product_QT(1)).thenReturn(250);
        product.put(1,1100);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le produit "+1+" insuffisant");
        orderExp.order_entrp_validate("enterprisname","008542001","text","rue 14","entrp.dz",product);

    }





}
