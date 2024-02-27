/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Repositories.UserRepository;

/**
 *
 * @author X1
 */
public class UserService {
    UserRepository userRepo = new UserRepository();
    
    public Boolean checkLogin(String userID, String passCode){
        return userRepo.checkLogin(userID, passCode);
    }
    
}
