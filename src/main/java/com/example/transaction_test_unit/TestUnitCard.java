package com.example.transaction_test_unit;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestUnitCard {
    //unit test of card exception
    @InjectMocks
    CardException cardexp;
    @Mock
    CardService card;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void Test_date_exp_true() throws Exception {
        // card date true
        when(card.date_expiration("6280 7854")).thenReturn(sdf.parse("11-08-2021"));
        boolean date = cardexp.date_exp_true("6280 7854", "11-08-2021");
        assertEquals(true, date);
    }
    @Test
    public void  TestExp_date_exp_true() throws Exception {
        // card date false
        when(card.date_expiration("6280 7854")).thenReturn(sdf.parse("15-5-2021"));

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Date d’expiration erronée");
        cardexp.date_exp_true("6280 7854","11-08-2021");

    }
    @Test
    public void Test_name_cardholder() throws Exception {
        // name cardholder true
        when(card.is_existe("6280 7854")).thenReturn(true);
        when(card.name("6280 7854","jo","mim")).thenReturn(true);
        boolean cardholder = cardexp.name_cardholder("6280 7854", "jo","mim");
        assertEquals(true, cardholder);
    }
    @Test
    public void TestExpCardExit_name_cardholder() throws Exception {
        // card not exist
        when(card.is_existe("6280 7854")).thenReturn(false);
        when(card.name("6280 7854","jo","mim")).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("la carte est erroné");
        cardexp.name_cardholder("6280 7854", "jo","mim");

    }
    @Test
    public void TestExpName_name_cardholder() throws Exception {
        // cardholder not exist
        when(card.is_existe("6280 7854")).thenReturn(true);
        when(card.name("6280 7854","jo","mim")).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Le nom du titulaire de la carte est erroné");
        cardexp.name_cardholder("6280 7854", "jo","mim");

    }
    @Test
    public void Test_lost_card() throws Exception {
        //card not lost
        when(card.card_lost("6280 7854")).thenReturn(false);

        boolean Nlost= cardexp.lost_card("6280 7854");
        assertEquals(true, Nlost);
    }
    @Test
    public void TestExp_lost_card() throws Exception {
        //card lost
        when(card.card_lost("6280 7854")).thenReturn(true);

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Cette carte est perdue");
        cardexp.lost_card("6280 7854");

    }
    @Test
    public void Test_Stop_card() throws Exception {
        //card not stopped
        when(card.card_stopped("6280 7854")).thenReturn(false);

        boolean Nstop= cardexp.stopped_card("6280 7854");
        assertEquals(true,Nstop);
    }
    @Test
    public void TestExp_Stop_card() throws Exception {
        //card  stopped
        when(card.card_stopped("6280 7854")).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Cette carte est suspendue");
        cardexp.stopped_card("6280 7854");

    }
    @Test
    public void Test_solde_card() throws Exception {
        //card sufficient balance card
        when(card.card_solde("6280 7854")).thenReturn(7800.0F);

        boolean solde= cardexp.solde_card("6280 7854",6000);
        assertEquals(true,solde);
    }
    @Test
    public void TestExp_solde_card() throws Exception {
        //card insufficient balance card
        when(card.card_solde("6280 7854")).thenReturn(7800.0F);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Ce montant n'est pas suffisant");
        cardexp.solde_card("6280 7854",8000);

    }
    @Test
    public void Test_card_cvv2() throws Exception {
        //card cvv2 true
        when(card.card_cvv2("6280 7854")).thenReturn(126);

        boolean cvv2= cardexp.card_cvv2("6280 7854",126);
        assertEquals(true,cvv2);
    }
    @Test
    public void TestExp_card_cvv2() throws Exception {
        //card cvv2 false
        when(card.card_cvv2("6280 7854")).thenReturn(126);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le cvv2 pas correct");
        cardexp.card_cvv2("6280 7854",115);

    }
    @Test
    public void Test_Authorized_ServPayment() throws Exception {
        //card  authorized for online payment
        when(card.card_Authorized_ServPayment("6280 7854")).thenReturn(true);

        boolean ServPayment= cardexp.Authorized_ServPayment("6280 7854");
        assertEquals(true,ServPayment);
    }
    @Test
    public void TestExp_Active_SerPayment() throws Exception {
       // card not authorized for online payment
        when(card.card_Authorized_ServPayment("6280 7854")).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette carte pas autorisée pour le payement en ligne");
        cardexp.Authorized_ServPayment("6280 7854");

    }
    @Test
    public void Test_Active_SerPayment() throws Exception {
        //card active for online payment
        when(card.card_Active_SerPayment("6280 7854")).thenReturn(true);

        boolean active= cardexp.Active_SerPayment("6280 7854");
        assertEquals(true,active);
    }
    @Test
    public void TestExp_Active_SerPaymentt() throws Exception {
        //card not active for online payment
        when(card.card_Active_SerPayment("6280 7854")).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("le service de paiement désactivé pour cette carte");
        cardexp.Active_SerPayment("6280 7854");

    }
    @Test
    public void Test_card_stolen() throws Exception {
        //card not stolen
        when(card.card_stolen("6280 7854")).thenReturn(false);

        boolean Nstolen= cardexp.card_stolen("6280 7854");
        assertEquals(true,Nstolen);
    }
    @Test
    public void TestExp_card_stolen() throws Exception {
        //card stolen
        when(card.card_stolen("6280 7854")).thenReturn(true);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("cette carte est volée");
        cardexp.card_stolen("6280 7854");

    }
    @Test
    public void Test_number_transaction() throws Exception {
        //number of transaction not Outmoded
        when(card.card_number_transaction()).thenReturn(3);

        boolean trans= cardexp.number_transaction(2);
        assertEquals(true,trans);
    }
    @Test
    public void TestExp_number_transaction() throws Exception {
        //number of transaction Outmoded
        when(card.card_number_transaction()).thenReturn(3);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Transaction Plafond Carte Dépassé");
        cardexp.number_transaction(4);

    }
    @Test
    public void Test_password() throws Exception {
        //password true
        when(card.password("6280 7854")).thenReturn("password");

        boolean pass= cardexp.transaction_password("6280 7854","password");
        assertEquals(true,pass);
    }
    @Test
    public void TestExp_password() throws Exception {
        //password false
        when(card.password("6280 7854")).thenReturn("password false");
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Saisie Mot de Passe erroné");
        cardexp.transaction_password("6280 7854","password");

    }

    @Test
    public void Test_Authorized_merchant() throws Exception {
        //card accepted by the merchant
        when(card.card_Authorized_merchant("6280 7854")).thenReturn(true);

        boolean merch= cardexp.Authorized_merchant("6280 7854");
        assertEquals(true,merch);
    }
    @Test
    public void TestExp_Authorized_merchant() throws Exception {
        //card not accepted by the merchant
        when(card.card_Authorized_merchant("6280 7854")).thenReturn(false);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Carte Non Acceptée par le commerçant");
        cardexp.Authorized_merchant("6280 7854");

    }

    @Test
    public void Test_number_password() throws Exception {
        //number of input password not Outmoded
        when(card.number_passw()).thenReturn(3);

        boolean NumPass= cardexp.number_password(2);
        assertEquals(true,NumPass);
    }
    @Test
    public void TestExp_number_password() throws Exception {
        //number of input password Outmoded
        when(card.number_passw()).thenReturn(3);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Dépassement nombre autorisé des PASSWORD");
        cardexp.number_password(4);

    }

}

