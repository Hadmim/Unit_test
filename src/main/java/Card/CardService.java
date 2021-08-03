package Card;

import java.util.Date;

public interface CardService{
//card service
  boolean is_existe(String num);
  boolean name(String num,String firstname,String lastname);//test name cardholder
  Date date_expiration(String num_card);
  boolean card_lost(String num_card);
  boolean card_stopped(String num_card);
  boolean card_stolen(String num_card);
  float card_solde(String num_card);
  int card_cvv2(String num_card);
  boolean card_Authorized_ServPayment(String num_card);
  boolean card_Active_SerPayment(String num_card);
  boolean card_Authorized_merchant(String num_card);
  int card_number_transaction();//number of transaction Authorized
  String password(String num_card);
  int number_passw();// number of password Authorized


}
