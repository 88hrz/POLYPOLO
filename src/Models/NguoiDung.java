/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author HP
 */
public class NguoiDung {
    Integer maND;
    String  tenND;
    String  mk;
    String  vaitro;

    public NguoiDung(Integer maND, String tenND, String mk, String vaitro) {
        this.maND = maND;
        this.tenND = tenND;
        this.mk = mk;
        this.vaitro = vaitro;
    }

    public NguoiDung() {
    }

    public Integer getMaND() {
        return maND;
    }

    public void setMaND(Integer maND) {
        this.maND = maND;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }
    
    
}
