package Card;


import java.text.SimpleDateFormat;
import java.util.Date;


public class CardException {
//card exception
    CardService carte;
     public  boolean date_Expiry(String num_carte,String date) throws Exception {
         //exception if card date false
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
         if(!(carte.date_expiration(num_carte).equals(sdf.parse(date))))
             throw new Exception("Date d’expiration erronée");
         return true;
     }
    public boolean date_Expiry_expired(Date date) throws Exception{
        //exception if expired date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
       Date now= sdf.parse(sdf.format(new Date())) ;
       Date exp=sdf.parse(sdf.format(date));
       if(!exp.after(now)){
           throw new Exception("Date d’expiration de la carte est dépassé");
       }
       return  true;
        }
    public boolean name_cardholder (String num_carte,String firstname,String lastname) throws Exception{
    //exception if card does not exist or cardholder false
      if(!carte.is_existe(num_carte))
          throw new Exception("la carte est erroné");


          if(!carte.name(num_carte,firstname,lastname))
            throw new Exception("Le nom du titulaire de la carte est erroné");
     return true;


    }
    public  boolean lost_card(String num_card) throws Exception {
        //exception if card lost
         if(carte.card_lost(num_card))
             throw new Exception("Cette carte est perdue");
         return true;
    }
    public  boolean stopped_card(String num_card) throws Exception {
        //exception if card stop
        if(carte.card_stopped(num_card))
            throw new Exception("Cette carte est suspendue");
        return true;
    }
    public  boolean solde_card(String num_card,float solde) throws Exception {
        //exception if insufficient balance card
        if(carte.card_solde(num_card)<solde)
            throw new Exception("Ce montant n'est pas suffisant");
        return true;
    }
    public  boolean card_cvv2(String num_card,int cvv2) throws Exception {
        //exception if card cvv2 false
        if(carte.card_cvv2(num_card)!=cvv2)
            throw new Exception("le cvv2 pas correct");
        return true;
    }
    public  boolean Authorized_ServPayment(String num_card) throws Exception {
        //exception if card not authorized for online payment
        if(!carte.card_Authorized_ServPayment(num_card))
            throw new Exception("cette carte pas autorisée pour le payement en ligne");
        return true;
    }
    public  boolean  Active_SerPayment(String num_card) throws Exception {
        //exception if card not active for online payment
        if(!carte.card_Active_SerPayment(num_card))
            throw new Exception("le service de paiement désactivé pour cette carte");
        return true;
    }
    public  boolean  card_stolen(String num_card) throws Exception {
        //exception if card stolen
        if(carte.card_stolen(num_card))
            throw new Exception("cette carte est volée");
        return true;
    }
    public  boolean  number_transaction(int numb_trans) throws Exception {
        //exception if number of transaction Outmoded
        if(carte.card_number_transaction()<numb_trans)
            throw new Exception("Transaction Plafond Carte Dépassé");
        return true;
    }
    public  boolean  transaction_password(String num_card,String pass_input) throws Exception {
        //exception if password false
        if(!carte.password(num_card).equals(pass_input))
            throw new Exception("Saisie Mot de Passe erroné");
        return true;
    }
    public  boolean  Authorized_merchant(String num_card) throws Exception {
        //exception if card  Not accepted by the merchant
        if(!carte.card_Authorized_merchant(num_card))
            throw new Exception("Carte Non Acceptée par le commerçant");
        return true;
    }

    public  boolean  number_password(int numb_pass) throws Exception {
        //exception if number of input password Outmoded
        if(carte.number_passw()<numb_pass)
            throw new Exception("Dépassement nombre autorisé des PASSWORD");
        return true;
    }

}
