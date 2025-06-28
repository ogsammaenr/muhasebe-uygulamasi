package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewClientController {
    @FXML
    public void initialize() {
        txt_eMail.textProperty().addListener((obs, oldVal, newVal) -> checkEMail(newVal));
        txt_phoneNumber.textProperty().addListener((obs, oldVal, newVal) -> checkPhoneNumber(newVal));
        txt_registerDate.textProperty().addListener((obs, oldText, newText) -> {
            // 1) Sadece rakam ve / kabul et
            StringBuilder filtered = new StringBuilder();
            for (char c : newText.toCharArray()) {
                if (Character.isDigit(c) || c == '/') {
                    filtered.append(c);
                }
            }
            String filteredText = filtered.toString();

            if (!filteredText.equals(newText)) {
                txt_registerDate.setText(filteredText);
                return;
            }

            // 2) Maksimum uzunluk 10 karakter
            if (filteredText.length() > 10) {
                txt_registerDate.setText(oldText);
                return;
            }

            // 3) / konulması gereken yerlere otomatik / ekle
            String digitsOnly = filteredText.replaceAll("/", "");
            StringBuilder formatted = new StringBuilder();

            for (int i = 0; i < digitsOnly.length() && i < 8; i++) { // 8 rakam max (ddMMyyyy)
                if (i == 2 || i == 4) {
                    formatted.append('/');
                }
                formatted.append(digitsOnly.charAt(i));
            }

            if (!formatted.toString().equals(filteredText)) {
                int caretPos = txt_registerDate.getCaretPosition();
                txt_registerDate.setText(formatted.toString());

                // İmleci son pozisyona ayarla (kullanıcı deneyimi için)
                if (caretPos > formatted.length()) {
                    caretPos = formatted.length();
                }
                txt_registerDate.positionCaret(caretPos);
            }
        });

//        txt_registerDate.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            int caretPos = txt_registerDate.getCaretPosition();
//            String text = txt_registerDate.getText();
//
//            if (event.getCode() == KeyCode.BACK_SPACE) {
//                if (caretPos > 0 && text.length() > 0) {
//                    // Eğer silinen karakter '/' ise önceki rakamla beraber sil
//                    if (text.charAt(caretPos - 1) == '/') {
//                        if (caretPos - 2 >= 0 && caretPos <= text.length()) {
//                            StringBuilder sb = new StringBuilder(text);
//                            sb.delete(caretPos - 2, caretPos);
//                            txt_registerDate.setText(sb.toString());
//                            txt_registerDate.positionCaret(caretPos - 2);
//                        }
//                        event.consume();
//                    }
//                }
//            }
//        });
    }

    @FXML
    public void onNowCheck() {
        if (chbx_now.isSelected()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            txt_registerDate.setText(LocalDate.now().format(formatter));
            txt_registerDate.setEditable(false);
        } else {
            txt_registerDate.setText("");
            txt_registerDate.setEditable(true);
        }
    }

    @FXML
    public void onSaveClick(ActionEvent event) {
        if (txt_registerDate.getText().equals("")) {
            chbx_now.setSelected(true);
            onNowCheck();
        }
        LocalDate date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(txt_registerDate.getText(), formatter);
        } catch (Exception e) {
            System.out.println("Yanlış format");
        }


    }

    private void checkEMail(String newVal) {

        if (newVal.equals("")) {
            txt_eMail.setStyle(null);
        } else if (!isValidEmail(newVal)) {
            txt_eMail.setStyle("-fx-border-color: red;");
        } else {
            txt_eMail.setStyle(null);
        }
    }

    private void checkPhoneNumber(String newVal) {
        if (newVal.equals("")) {
            txt_phoneNumber.setStyle(null);
        } else if (!isValidPhoneNumber(newVal)) {
            txt_phoneNumber.setStyle("-fx-border-color: red;");
        } else {
            txt_phoneNumber.setStyle(null);
        }
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    public boolean isValidPhoneNumber(String phone) {
        // Sadece 10 haneli rakam (örnek: 5XXXXXXXXX)
        String phoneRegex = "^5\\d{9}$";
        return phone.matches(phoneRegex);
    }

    @FXML
    private Button btn_save;

    @FXML
    private CheckBox chbx_now;

    @FXML
    private TextField txt_companyName, txt_registerDate, txt_eMail, txt_phoneNumber;

    @FXML
    private TextArea txt_notes;
}
