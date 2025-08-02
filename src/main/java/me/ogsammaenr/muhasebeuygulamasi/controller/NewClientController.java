package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.ogsammaenr.muhasebeuygulamasi.manager.ClientsManager;
import me.ogsammaenr.muhasebeuygulamasi.model.Client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.UnaryOperator;

public class NewClientController {
    private ClientsManager clientsManager;

    @FXML
    public void initialize() {
        txt_eMail.textProperty().addListener((obs, oldVal, newVal) -> checkEMail(newVal));
        txt_phoneNumber.textProperty().addListener((obs, oldVal, newVal) -> checkPhoneNumber(newVal));
        UnaryOperator<TextFormatter.Change> dateFilter = this::dateChange;

        txt_registerDate.setTextFormatter(new TextFormatter<>(dateFilter));

    }

    public void setClientsManager(ClientsManager clientsManager) {
        this.clientsManager = clientsManager;
    }

    @FXML
    public void onNowCheck() {
        if (chbx_now.isSelected()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            txt_registerDate.setText(LocalDate.now().format(formatter));
            txt_registerDate.setEditable(false);
            txt_registerDate.setStyle(null);
        } else {
            txt_registerDate.setText("");
            txt_registerDate.setEditable(true);
        }
    }

    @FXML
    public void onSaveClick(ActionEvent event) {
        System.out.println("adsfasdfasdfasdf");
        if (txt_registerDate.getText().equals("")) {
            chbx_now.setSelected(true);
            onNowCheck();
        }
        boolean bool = false;
        for (var child : vbox.getChildren()) {
            if (child instanceof TextField || child instanceof TextArea) {
                if (child.getStyle().equals("-fx-border-color: red;")) {
                    bool = true;
                }
            }
        }
        if (bool) {
            System.out.println("bişeyler hatalı");
            return;
        }

        Client client = new Client(txt_companyName.getText(), LocalDate.parse(txt_registerDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if (!txt_eMail.getText().equals("")) {
            client.setEMailAddress(txt_eMail.getText());
        }
        if (!txt_phoneNumber.getText().equals("")) {
            client.setPhoneNumber(txt_phoneNumber.getText());
        }
        if (!txt_notes.getText().equals("")) {
            client.setNotes(txt_notes.getText());
        }

        clientsManager.addClient(client);
        Stage stg = (Stage) vbox.getScene().getWindow();
        stg.close();


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

    public boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);

            return localDate.isBefore(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }

    public TextFormatter.Change dateChange(TextFormatter.Change change) {
        String newText = change.getControlNewText();
        String oldText = change.getControlText();

        // Sadece rakam ve / karakterlerini al
        String digitsOnly = newText.replaceAll("[^0-9]", "");

        // En fazla 8 rakam (ddMMyyyy)
        if (digitsOnly.length() > 8) {
            return null;
        }

        StringBuilder formatted = new StringBuilder();
        int rawCaretPos = change.getCaretPosition(); // caret'in ham pozisyonunu al

        for (int i = 0; i < digitsOnly.length(); i++) {
            if (i == 2 || i == 4) {
                formatted.append('/');
                // Eğer caret bu noktadan ileri gidiyorsa 1 artır
                if (i < rawCaretPos) {
                    rawCaretPos++;
                }
            }
            formatted.append(digitsOnly.charAt(i));
        }

        String finalText = formatted.toString();
        change.setText(finalText);
        change.setRange(0, oldText.length());

        if (digitsOnly.length() == 8) {
            if (!isValidDate(finalText)) {
                // Geçersizse metni reddet
                txt_registerDate.setStyle("-fx-border-color: red;");
            } else {
                txt_registerDate.setStyle(null);
            }
        }

        // Caret pozisyonunu ayarla
        int finalCaretPos = Math.min(rawCaretPos, finalText.length());
        change.setCaretPosition(finalCaretPos);
        change.setAnchor(finalCaretPos); // seçili alanı sıfırla

        return change;
    }

    @FXML
    private Button btn_save;

    @FXML
    private CheckBox chbx_now;

    @FXML
    private TextField txt_companyName, txt_registerDate, txt_eMail, txt_phoneNumber;

    @FXML
    private TextArea txt_notes;

    @FXML
    private VBox vbox;
}
