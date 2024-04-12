package model.manager;

import model.price.Price;


/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class Manager {

    private Price mgprice;
    private String username;
    private String password;


    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
        mgprice = Price.getPrice();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean SignIn(String user, String psw) {
        return username.equals(user) & password.equals(psw) ;
    }

    public boolean modifyPrice(String category, String plane, double price) {
        return mgprice.modifyPrice(category, plane, price);
    }

}
