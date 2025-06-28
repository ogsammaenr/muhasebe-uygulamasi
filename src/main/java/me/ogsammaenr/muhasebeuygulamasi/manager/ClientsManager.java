package me.ogsammaenr.muhasebeuygulamasi.manager;

import me.ogsammaenr.muhasebeuygulamasi.model.Client;
import me.ogsammaenr.muhasebeuygulamasi.storage.ClientsDAO;

import java.util.List;

public class ClientsManager {
    private final ClientsDAO clientsDAO = new ClientsDAO();

    public void addClient(Client client) {
        // Burada validasyon ve iş mantığı olabilir
        clientsDAO.addClient(client);
    }

    public void deleteClient(int clientId) {
        // İstersen burada ek işlemler yapabilirsin
        clientsDAO.deleteClient(clientId);
    }

    public List<Client> getAllClients() {
        return clientsDAO.getAllClients();
    }

    public int getClientCount() {
        return clientsDAO.getClientCount();
    }

    public int getClientProductCount(int clientId) {
        return clientsDAO.getClientProductCount(clientId);
    }

    public Client getClientbyId(int clientId) {
        return clientsDAO.getClientById(clientId);
    }

}
