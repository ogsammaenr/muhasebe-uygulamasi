package me.ogsammaenr.muhasebeuygulamasi.storage;

import me.ogsammaenr.muhasebeuygulamasi.model.Client;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientsDAO {

    /**
     * Yeni Müşteri Ekle
     *
     * @param client
     */
    public void addClient(Client client) {
        String sql = "INSERT INTO clients (company_name, registration_date, last_action_date, email, telephone, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, client.getCompanyName());
            pstmt.setString(2, client.getRegistrationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            pstmt.setString(3, client.getLastActionDate() != null ? client.getLastActionDate().toString() : null);
            pstmt.setString(4, client.getEMailAddress() != null ? client.getEMailAddress() : null);
            pstmt.setString(5, client.getPhoneNumber() != null ? client.getPhoneNumber() : null);
            pstmt.setString(6, client.getNotes() != null ? client.getNotes() : null);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Client eklenemedi, etkilenen satır yok.");
            }

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    client.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Client Eklenemedi, Hiçbir satır etkilenmedi");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Müşteriyi Siler
     *
     * @param clientId
     */
    public void deleteClient(int clientId) {
        String sql = "DELETE FROM clients WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clientId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bütün Müşterileri Listeler
     *
     * @return
     */
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("company_name"),
                        rs.getString("registration_date") != null ? LocalDate.parse(rs.getString("registration_date"), formatter) : null,
                        rs.getString("last_action_date") != null ? LocalDate.parse(rs.getString("last_action_date"), formatter) : null
                );

                client.setEMailAddress(rs.getString("email"));
                client.setPhoneNumber(rs.getString("telephone"));
                client.setNotes(rs.getString("notes"));

                clients.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    /**
     * Müşteri Sayısını Döndürür
     *
     * @return
     */
    public int getClientCount() {
        String sql = "SELECT COUNT(*) AS count FROM clients";
        int count = 0;

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                count = rs.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public int getClientProductCount(int clientId) {
        String sql = "SELECT COUNT(*) AS count FROM clients WHERE id = ?";
        int count = 0;
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clientId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Client getClientById(int clientId) {
        String sql = "SELECT * FROM clients WHERE id = ?";
        Client client = null;
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String companyName = rs.getString("company_name");
                String registrationDate = rs.getString("registration_date");
                String lastActionDate = rs.getString("last_action_date");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("telephone");
                String notes = rs.getString("notes");

                LocalDate registerdate = registrationDate != null ? LocalDate.parse(registrationDate) : null;
                LocalDate lastactiondate = lastActionDate != null ? LocalDate.parse(lastActionDate) : null;
                client = new Client(clientId, companyName, registerdate, lastactiondate);
                client.setEMailAddress(email);
                client.setPhoneNumber(phoneNumber);
                client.setNotes(notes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }
}
