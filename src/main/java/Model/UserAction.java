/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author thaiv
 */
public class UserAction {
     private String userName;
    private String action;

    public UserAction(String userName, String action) {
        this.userName = userName;
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return  
                "userName : " + userName  +
                " , action :" + action ;
    }
}
