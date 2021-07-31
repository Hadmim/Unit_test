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

public class TestUnitPayment {
    //unit test of payment exception
    @InjectMocks
    PaymentException paymExp;
    @Mock
    PaymentService payment;

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void TestTrue_payment_delerhome_validate() throws Exception {
       // buyer is a person payment valid
        when(payment.order_exist("1001","1200","104","0")).thenReturn(true);
        when(payment.delivery_exist("batna","chmara","111","rue","gps","14587","hh@gmail.com","10","1000")).thenReturn(true);

        boolean payment = paymExp.payment_delerhome_validate("batna","chmara","111","rue","14587","hh@gmail.com","gps","10","1000","1001","1200","104","0");
        assertEquals(true,payment);
    }
    @Test
    public void TestTrue_payment_delerPV_validate() throws Exception {
        //buyer is a company payment validate
        when(payment.order_exist("1001","1200","104","0")).thenReturn(true);
        when(payment.delivery_exist_pv("batna","gps","14587","hh@gmail.com","10")).thenReturn(true);

        boolean payment = paymExp.payment_delerPV_validate("14587","hh@gmail.com","batna","gps","10","1001","1200","104","0");
        assertEquals(true,payment);
    }
    @Test
    public void TestExpVide_payment_delerhome_validate() throws Exception {
        // buyer is a person code postal vide
        when(payment.order_exist("1001","1200","104","0")).thenReturn(true);
        when(payment.delivery_exist("batna","chmara","","rue","gps","14587","hh@gmail.com","10","1000")).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une commande doit être correct et pas vide");
        paymExp.payment_delerhome_validate("batna","chmara","","rue","14587","hh@gmail.com","gps","10","1000","1001","1200","104","0");
    }
    @Test
    public void TestExpOrder_payment_delerhome_validate() throws Exception {
        // buyer is a person  order not exist in BD
        when(payment.order_exist("1001","1200","104","0")).thenReturn(false);
        when(payment.delivery_exist("batna","chmara","111","rue","gps","14587","hh@gmail.com","10","1000")).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette commande pas existé");
       paymExp.payment_delerhome_validate("batna","chmara","111","rue","14587","hh@gmail.com","gps","10","1000","1001","1200","104","0");

    }
    @Test
    public void TestExpDliv_payment_delerhome_validate() throws Exception {
        // buyer is a person delivery not exist in BD
        when(payment.order_exist("1001","1200","104","0")).thenReturn(true);
        when(payment.delivery_exist("batna","chmara","111","rue","gps","14587","hh@gmail.com","10","1000")).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette livraison pas existé");
        paymExp.payment_delerhome_validate("batna","chmara","111","rue","14587","hh@gmail.com","gps","10","1000","1001","1200","104","0");
    }
    @Test
    public void TestExpVide_payment_delerPV_validate() throws Exception {
        //buyer is a company num_order vide
        when(payment.order_exist("","1200","104","0")).thenReturn(false);
        when(payment.delivery_exist_pv("batna","gps","14587","hh@gmail.com","10")).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une commande doit être correct et pas vide");
        paymExp.payment_delerPV_validate("14587","hh@gmail.com","batna","gps","10","","1200","104","0");

    }
    @Test
    public void TestExpOrder_payment_delerPV_validate() throws Exception {
        // buyer is a company order not exist in BD
        when(payment.order_exist("1001","1200","104","0")).thenReturn(false);
        when(payment.delivery_exist_pv("batna","gps","14587","hh@gmail.com","10")).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette commande pas existé");
        boolean payment = paymExp.payment_delerPV_validate("14587","hh@gmail.com","batna","gps","10","1001","1200","104","0");
        assertEquals(true,payment);
    }
    @Test
    public void TestExpDelv_payment_delerPV_validate() throws Exception {
        // buyer is a company delivery not exist in BD
        when(payment.order_exist("1001","1200","104","0")).thenReturn(true);
        when(payment.delivery_exist_pv("batna","gps","14587","hh@gmail.com","10")).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette livraison pas existé");
        paymExp.payment_delerPV_validate("14587","hh@gmail.com","batna","gps","10","1001","1200","104","0");

    }

}
