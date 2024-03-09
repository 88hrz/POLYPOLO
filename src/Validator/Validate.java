/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validator;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author X1
 */
public class Validate {
    
    public Boolean isEmpty(JTextField txt, StringBuilder stb, String msg) {
        if (txt.getText().trim().isEmpty()) {
            txt.setBackground(Color.YELLOW);
            stb.append(msg).append("\n");
            return false;
        }
        txt.setBackground(Color.WHITE);
        return true;
    }
    
    public Boolean isEmpty(JTextArea txt, StringBuilder stb, String msg) {
        if (txt.getText().trim().isEmpty()) {
            txt.setBackground(Color.YELLOW);
            stb.append(msg).append("\n");
            return false;
        }
        txt.setBackground(Color.WHITE);
        return true;
    }

    public Boolean isNumber(JTextField txt, StringBuilder stb, String msg, int type) {
        if (!isEmpty(txt, stb, msg)) {
            return false;
        } else {
            try {
                if (type > 0) {
                    double numberD = Double.parseDouble(txt.getText().trim());
                } else {
                    int numberI = Integer.parseInt(txt.getText().trim());
                }
            } catch (Exception e) {
                txt.setBackground(Color.YELLOW);
                stb.append(msg).append("\n");
                return false;
            }
        }
        txt.setBackground(Color.WHITE);
        return true;
    }

    public Boolean NumberLimit(JTextField txt, StringBuilder stb, String msg, int type, int min) {
        if (!isNumber(txt, stb, msg, type)) {
            return false;
        } else {
            int NumberI = Integer.parseInt(txt.getText().trim());
            if (NumberI < min) {
                txt.setBackground(Color.YELLOW);
                stb.append(msg).append("\n");
                return false;
            }
        }
        txt.setBackground(Color.WHITE);
        return true;
    }
    
    public Boolean isPhoneNumber(JTextField txt, String msg, StringBuilder stb) {
        if (!isEmpty(txt, stb, msg)) {
            return false;
        } else {
            String phone = "\\d{10}";
            if (txt.getText().trim().matches(phone)) {
                txt.setBackground(Color.WHITE);
                return true;
            } else {
                txt.setBackground(Color.YELLOW);
                stb.append(msg).append("\n");
                return false;
            }
        }
    }
    
    public Boolean isNumberGreater(JTextField txt, String msg, StringBuilder stb, int type, int min) {
        if (!isNumber(txt, stb, msg, type)) {
            return false;
        } else {
            double number = Double.parseDouble(txt.getText().trim());
            if (number < min) {
                txt.setBackground(Color.YELLOW);
                stb.append(msg).append("\n");
                return false;
            }
        }
        txt.setBackground(Color.WHITE);
        return true;
    }

}