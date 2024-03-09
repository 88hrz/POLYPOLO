/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.User;
import Repositories.UserRepository;
import ViewModels.UserViewModel;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class NguoiDungService {
    UserRepository userRepo = new UserRepository();
    
    public Boolean checkLogin(String userID, String passCode){
        return userRepo.checkLogin(userID, passCode);
    }
    public User getCurrentUser(String userID, String passCode){
        return userRepo.getCurrentUser(userID, passCode);
    }
            
    //GETLIST
    public ArrayList<UserViewModel> getList(){
        return userRepo.getList();
    }
    //GETLIST BY SEARCH
    public ArrayList<UserViewModel> getListBySearch(String name){
        return userRepo.getListBySearch(name);
    }
    //GETLIST USER
    public User getListUser(){
        return userRepo.getListUser();
    }public User getListByUserId(String userId){
        return userRepo.getListByUserId(userId);
    }
    
    //HIDE
    public void hideAccount(User u){
        userRepo.hideAccount(u);
    }
    
    //CHECK I4
    public UserViewModel checkI4(String userID, String passCode){
        return userRepo.checkI4(userID, passCode);
    }
    
    //CHECK ID
    public Boolean checkId(String id){
        return userRepo.checkId(id);
    }
    
    //ADD
    public String addAccount(User u){
        Boolean check = userRepo.addAccount(u);
        if (check) {
            return "Thêm tài khoản mới thành công ~uwu~";
        }else{
            return "Thêm tài khoản mới thất bại uhu";
        }
    }
    //UPDATE
    public String updateAccount(User u){
        Boolean check = userRepo.updateAccount(u);
        if (check) {
            return "Sửa thông tin tài khoản thành công ~uwu~";
        }else{
            return "Sửa thông tin tài khoản thất bại uhu";
        }
    }
}
