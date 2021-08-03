package Order;

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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestOrder {
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
    public void Order_Perosn_Test_valid() throws Exception {
        //buyer is a person order valid
        when(orderServ.mail_validate(anyString())).thenReturn(true);
        when(orderServ.product_QT(anyInt())).thenReturn(2);
        when(orderServ.number_phone(anyString())).thenReturn(true);
        product.put(1,1);
        boolean orderprsn = orderExp.order_person(product,"lastname","firstname","125487","email","20");
        assertEquals(true, orderprsn);
    }
    @Test
    public void Order_Person_Test_info_order_empty() throws Exception {
        //buyer is a person product is empty
        when(orderServ.mail_validate(anyString())).thenReturn(true);
        when(orderServ.number_phone(anyString())).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une commande doit être correct et pas vide");
        orderExp.order_person(product,"lastname","firstname","125487","email","20");

    }
    @Test
    public void order_person_Test_total_negative() throws Exception {
        //buyer is a person total not valid
        when(orderServ.mail_validate(anyString())).thenReturn(true);
        when(orderServ.product_QT(anyInt())).thenReturn(2);
        when(orderServ.number_phone(anyString())).thenReturn(true);

        product.put(1,1);
        String total="-20";
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le montant doit être positive");
        orderExp.order_person(product,"lastname","firstname","125487","email",total);

    }
    @Test
    public void order_person_Test_mail_not_valid() throws Exception {
        //buyer is a person mail not valid
        when(orderServ.mail_validate("hh@jj.com")).thenReturn(false);
        when(orderServ.product_QT(1)).thenReturn(2);
        when(orderServ.number_phone("125487")).thenReturn(true);

        product.put(1,1);
         exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("l'adresse email doit être correct et exist");
        orderExp.order_person(product,"lastname","firstname","125487","email","20");

    }
    @Test
    public void Order_Person_Test_phone_not_valid() throws Exception {
        //buyer is a person phone not valid
        when(orderServ.mail_validate(anyString())).thenReturn(true);
        when(orderServ.product_QT(anyInt())).thenReturn(2);
        when(orderServ.number_phone(anyString())).thenReturn(false);

        product.put(1,1);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le numéro de téléphone doit être valide ");
        orderExp.order_person(product,"lastname","firstname","125487","email","20");

    }

    @Test
    public void Order_Person_Test_quntity_insufficient() throws Exception {
        // buyer is a person product quntity insufficient
        when(orderServ.number_phone(anyString())).thenReturn(true);
        when(orderServ.mail_validate(anyString())).thenReturn(true);
        when(orderServ.product_QT(anyInt())).thenReturn(2);
        product.put(1,3);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le produit "+1+" insuffisant");
        orderExp.order_person(product,"lastname","firstname","125487","email","8457");

    }
    @Test
    public void Order_Enterprise_Test_valid() throws Exception {
        //buyer is a company order validate
        when(orderServ.enterprise_exist("enterprisname","1","address","lien enterprise")).thenReturn(true);
        when(orderServ.product_QT(1)).thenReturn(2);

        product.put(1,1);

        boolean orderentrp =orderExp.order_enterprise("enterprisname","1","article","address","lien enterprise",product,"1234");
        assertEquals(true, orderentrp);    }
    @Test
    public void Order_Enterprise_Test_info_order_empty() throws Exception {
        // buyer is a company total is empty
        when(orderServ.enterprise_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        when(orderServ.product_QT(anyInt())).thenReturn(2000);
        product.put(1,1100);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("les information d'une commande doit être correct et pas vide");
        orderExp.order_enterprise("enterprisname","1","article","address","lien enterprise",product,"");

    }
    @Test
    public void Order_Enterprise_Test_total_negative() throws Exception {
        //buyer is a company total not valid negative
        when(orderServ.enterprise_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(false);
        when(orderServ.product_QT(anyInt())).thenReturn(2000);
        product.put(1,1100);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le montant doit être positive");
        String total="-1000";
        orderExp.order_enterprise("enterprisname","1","article","address","lien enterprise",product,total);
    }
    @Test
    public void Order_Enterprise_Test_company_not_exist () throws Exception {
        //buyer is a company enterprise dose not exist
        when(orderServ.enterprise_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(false);
        when(orderServ.product_QT(anyInt())).thenReturn(2000);
        product.put(1,1100);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("enterprise n'exist pas");
        orderExp.order_enterprise("enterprisname","1","article","address","lien enterprise",product,"2");

    }
    @Test
    public void Order_Enterprise_Test_quntity_isufficient() throws Exception {
        // buyer is a company product quntity insufficient
        when(orderServ.enterprise_exist(anyString(),anyString(),anyString(),anyString())).thenReturn(true);
        when(orderServ.product_QT(1)).thenReturn(1);
        product.put(1,2);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le produit "+1+" insuffisant");
        orderExp.order_enterprise("enterprisname","1","article","address","lien enterprise",product,"2");

    }





}
