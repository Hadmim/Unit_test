package Card;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TestCard {
    //unit test of card exception
    @InjectMocks
    CardException cardexp;
    @Mock
    CardService card;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


    @Before
    public void init() throws ParseException {

        MockitoAnnotations.initMocks(this);
    }
    @Rule
  public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void date_Expiry_Test_inputdate_true() throws Exception {
        // card date true
        when(card.date_expiration(anyString())).thenReturn(sdf.parse("11-8-2021"));
        String date_Expiry_input="11-08-2021";
        boolean date = cardexp.date_Expiry("", date_Expiry_input);
        assertEquals(true, date);
    }
    @Test
    public void  date_Expiry_Test_inputdate_false() throws Exception {
        // card date false
        when(card.date_expiration(anyString())).thenReturn(sdf.parse("15-5-2021"));
        String date_Expiry_input="11-08-2021";
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Date d’expiration erronée");
        cardexp.date_Expiry("",date_Expiry_input);

    }
    @Test
    public void date_Expiry_expired_Test_not_expired() throws Exception {
        // card date Expiry not expired
        String date_Expiry_input="11-8-2023";
        boolean date = cardexp.date_Expiry_expired(sdf.parse(date_Expiry_input));
        assertEquals(true, date);
    }
    @Test
    public void  date_Expiry_expired_Test_expired() throws Exception {
        // card date Expiry  expired

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Date d’expiration de la carte est dépassé");
        String date_Expiry_input="11-6-2021";
        cardexp.date_Expiry_expired(sdf.parse(date_Expiry_input));

    }
    @Test
    public void name_cardholder_Test_valid() throws Exception {
        // name cardholder true
        when(card.is_existe(anyString())).thenReturn(true);
        when(card.name(anyString(),anyString(),anyString())).thenReturn(true);
        boolean cardholder = cardexp.name_cardholder("","","");
        assertEquals(true, cardholder);
    }
    @Test
    public void name_cardholder_Test_card_not_exist() throws Exception {
        // card not exist
        when(card.is_existe(anyString())).thenReturn(false);
        when(card.name(anyString(),anyString(),anyString())).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("la carte est erroné");
        cardexp.name_cardholder("","","");

    }
    @Test
    public void name_cardholder_Test_name_not_exist() throws Exception {
        // cardholder not exist
        when(card.is_existe(anyString())).thenReturn(true);
        when(card.name(anyString(),anyString(),anyString())).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Le nom du titulaire de la carte est erroné");
        cardexp.name_cardholder("", "","");

    }
    @Test
    public void lost_card_Test_not_lost() throws Exception {
        //card not lost
        when(card.card_lost(anyString())).thenReturn(false);

        boolean Nlost= cardexp.lost_card("");
        assertEquals(true, Nlost);
    }
    @Test
    public void lost_card_Test_lost() throws Exception {
        //card lost
        when(card.card_lost(anyString())).thenReturn(true);

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Cette carte est perdue");
        cardexp.lost_card("");

    }
    @Test
    public void Stop_card_Test_not_stop() throws Exception {
        //card not stopped
        when(card.card_stopped(anyString())).thenReturn(false);

        boolean Nstop= cardexp.stopped_card("");
        assertEquals(true,Nstop);
    }
    @Test
    public void Stop_card_Test_stop() throws Exception {
        //card  stopped
        when(card.card_stopped(anyString())).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Cette carte est suspendue");
        cardexp.stopped_card("");

    }
    @Test
    public void solde_card_Test_sufficient() throws Exception {
        //card sufficient balance card
        when(card.card_solde(anyString())).thenReturn(7800.0F);
        float total=6000;
        boolean solde= cardexp.solde_card("",total);
        assertEquals(true,solde);
    }
    @Test
    public void solde_card_Test_insufficient() throws Exception {
        //card insufficient balance card
        when(card.card_solde(anyString())).thenReturn(7800.0F);
        float total=8000;
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Ce montant n'est pas suffisant");
        cardexp.solde_card("",total);

    }
    @Test
    public void card_cvv2_Test_inputcvv2_true() throws Exception {
        //card cvv2 true
        when(card.card_cvv2(anyString())).thenReturn(126);
        int input_cvv2=126;
        boolean cvv2= cardexp.card_cvv2("",input_cvv2);
        assertEquals(true,cvv2);
    }
    @Test
    public void card_cvv2_Test_inputcvv2_false() throws Exception {
        //card cvv2 false
        when(card.card_cvv2(anyString())).thenReturn(126);
        int input_cvv2=115;
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le cvv2 pas correct");
        cardexp.card_cvv2("",input_cvv2);

    }
    @Test
    public void Authorized_ServPayment_authorized() throws Exception {
        //card  authorized for online payment
        when(card.card_Authorized_ServPayment(anyString())).thenReturn(true);

        boolean ServPayment= cardexp.Authorized_ServPayment("");
        assertEquals(true,ServPayment);
    }
    @Test
    public void Authorized_ServPayment_Test_not_authorized() throws Exception {
       // card not authorized for online payment
        when(card.card_Authorized_ServPayment(anyString())).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette carte pas autorisée pour le payement en ligne");
        cardexp.Authorized_ServPayment("");

    }
    @Test
    public void Active_ServPayment_active() throws Exception {
        //card active for online payment
        when(card.card_Active_SerPayment(anyString())).thenReturn(true);

        boolean active= cardexp.Active_SerPayment("");
        assertEquals(true,active);
    }
    @Test
    public void Active_ServPayment_Test_not_active() throws Exception {
        //card not active for online payment
        when(card.card_Active_SerPayment(anyString())).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le service de paiement désactivé pour cette carte");
        cardexp.Active_SerPayment("");

    }
    @Test
    public void Card_Stolen_Test_not_stolen() throws Exception {
        //card not stolen
        when(card.card_stolen(anyString())).thenReturn(false);

        boolean Nstolen= cardexp.card_stolen("");
        assertEquals(true,Nstolen);
    }
    @Test
    public void Card_Stolen_Test_stolen() throws Exception {
        //card stolen
        when(card.card_stolen(anyString())).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette carte est volée");
        cardexp.card_stolen("");

    }
    @Test
    public void Number_Transaction_Test_not_outmoded() throws Exception {
        //number of transaction not Outmoded
        when(card.card_number_transaction()).thenReturn(3);
        int number_of_transaction=2;
        boolean trans= cardexp.number_transaction(number_of_transaction);
        assertEquals(true,trans);
    }
    @Test
    public void Number_Transaction_Test_outmoded() throws Exception {
        //number of transaction Outmoded
        when(card.card_number_transaction()).thenReturn(3);
        int number_of_transaction=4;
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Transaction Plafond Carte Dépassé");
        cardexp.number_transaction(number_of_transaction);

    }
    @Test
    public void  Transaction_Password_Test_inputpassword_true() throws Exception {
        //password true
        when(card.password(anyString())).thenReturn("password");
        String input_password="password";
        boolean pass= cardexp.transaction_password("",input_password);
        assertEquals(true,pass);
    }
    @Test
    public void Transaction_Password_Test_inputpassword_false() throws Exception {
        //password false
        when(card.password(anyString())).thenReturn("password");
        String input_password="password false";
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Saisie Mot de Passe erroné");
        cardexp.transaction_password("",input_password);

    }

    @Test
    public void Authorized_merchant_authorized() throws Exception {
        //card accepted by the merchant
        when(card.card_Authorized_merchant(anyString())).thenReturn(true);

        boolean merch= cardexp.Authorized_merchant("");
        assertEquals(true,merch);
    }
    @Test
    public void Authorized_merchant_not_accepted() throws Exception {
        //card not accepted by the merchant
        when(card.card_Authorized_merchant(anyString())).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Carte Non Acceptée par le commerçant");
        cardexp.Authorized_merchant("");

    }

    @Test
    public void Number_Password_Test_not_outmoded() throws Exception {
        //number of input password not Outmoded
        when(card.number_passw()).thenReturn(3);
        int number_of_password=2;
        boolean NumPass= cardexp.number_password(number_of_password);
        assertEquals(true,NumPass);
    }
    @Test
    public void Number_Password_Test_outmoded() throws Exception {
        //number of input password Outmoded
        when(card.number_passw()).thenReturn(3);
        int number_of_password=4;
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Dépassement nombre autorisé des PASSWORD");
        cardexp.number_password(number_of_password);

    }

}

