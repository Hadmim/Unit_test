package Payment;

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

public class TestPayment {
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
    public void  Payment_Delivery_Home_Test_valid() throws Exception {
       // buyer is a person payment valid
        when(payment.order_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        when(payment.delivery_home_exist(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn(true);

        boolean payment = paymExp.payment_delivery_home("wilaya","commune","111","home","14587","mail","gps","10","1000","1001","1200","104","0");
        assertEquals(true,payment);
    }
    @Test
    public void payment_delivery_point_saller_Test_valid() throws Exception {
        //buyer is a company payment validate
        when(payment.order_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        when(payment.delivery_point_saller_exist(anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn(true);

        boolean payment =   paymExp.payment_delivery_point_saller("14587","mail","point","gps","10","1001","1200","104","0");
        assertEquals(true,payment);
    }
    @Test
    public void Payment_Delivery_Home_Test_info_payment_empty() throws Exception {
        // buyer is a person code postal vide
        when(payment.order_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        when(payment.delivery_home_exist(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),
                anyString(),anyString(),anyString())).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une paiement doit être correct et pas vide");
        paymExp.payment_delivery_home("wilaya","commune","","home","14587","mail","gps","10","1000","1001","1200","104","0");
    }
    @Test
    public void Payment_Delivery_Home_Test_order_not_exist() throws Exception {
        // buyer is a person  order not exist in BD
        when(payment.order_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(false);
        when(payment.delivery_home_exist(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette commande pas existé");
       paymExp.payment_delivery_home("wilaya","commune","111","home","14587","mail","gps","10","1000","1001","1200","104","0");

    }
    @Test
    public void Payment_Delivery_Home_Test_delivery_not_exist() throws Exception {
        // buyer is a person delivery not exist in BD
        when(payment.order_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        when(payment.delivery_home_exist(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette livraison pas existé");
        paymExp.payment_delivery_home("wilaya","commune","111","home","14587","mail","gps","10","1000","1001","1200","104","0");
    }
    @Test
    public void payment_delivery_point_saller_Test_info_payment_empty() throws Exception {
        //buyer is a company num_order vide
        when(payment.order_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(false);
        when(payment.delivery_point_saller_exist(anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une paiement doit être correct et pas vide");
        paymExp.payment_delivery_point_saller("14587","mail","point","gps","10","","1200","104","0");

    }
    @Test
    public void payment_delivery_point_saller_Test_order_not_exist() throws Exception {
        // buyer is a company order not exist in BD
        when(payment.order_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(false);
        when(payment.delivery_point_saller_exist(anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette commande pas existé");
        boolean payment = paymExp.payment_delivery_point_saller("14587","mail","point","gps","10","1001","1200","104","0");
        assertEquals(true,payment);
    }
    @Test
    public void payment_delivery_point_saller_Test_delivery_not_exist() throws Exception {
        // buyer is a company delivery not exist in BD
        when(payment.order_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        when(payment.delivery_point_saller_exist(anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette livraison pas existé");
        paymExp.payment_delivery_point_saller("14587","mail","point","gps","10","1001","1200","104","0");

    }

}
