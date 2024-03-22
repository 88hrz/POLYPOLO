/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validator;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author X1
 */
public class Validate {

    public Boolean validPassCode(JPasswordField jpass, String msg, StringBuilder stb){
        String passCode = new String(jpass.getPassword());
        if (passCode.matches("\\d{3}")) {
            return true;
        } else {
            if (stb != null) {
                stb.append("Mật khẩu sai định dạng!").append("\n");
                stb.append(msg);
            }
            return false;
        }
    }
    
    public Boolean isDateSelected(JDateChooser calendar, StringBuilder stb, String msg) {
        Date date = calendar.getDate();
        if (date == null) {
            calendar.setBackground(Color.yellow);
            stb.append(msg).append("\n");
            return false;
        }
        return true;
    }
    
    public Boolean isDateValid(JDateChooser calendar, StringBuilder stb, String msg) {
        Date date = calendar.getDate();
        if (date == null) {
            calendar.setBackground(Color.yellow);
            stb.append(msg).append("\n").append("Không được để trống ngày!").append("\n");
            return false;
        }

        LocalDate selectedDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();

        if (selectedDate.isBefore(currentDate)) {
            calendar.setBackground(Color.yellow);
            stb.append(msg).append("Ngày tạo hóa đơn phải từ hôm nay trở đi!").append("\n");
            return false;
        }

        calendar.setBackground(Color.white);
        return true;
    }
    
    public Boolean isRadioButtonSelected(ButtonGroup buttonGroup, StringBuilder stb, String msg) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return true;
            }
        }
        stb.append(msg).append("\n");
        return false;
    }

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
