package Order;

public interface OrderService {
//order service

    int product_QT(int id_product);
    boolean mail_validate(String mail);
    boolean enterprise_exist(String nom,String num_identf,String addrs,String lien);
    boolean number_phone(String number);


}
