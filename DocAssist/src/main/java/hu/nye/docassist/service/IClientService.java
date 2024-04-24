package hu.nye.docassist.service;
import java.util.List;
import hu.nye.docassist.entity.ClientEntity;
import hu.nye.docassist.exception.ClientNotFoundException;
import hu.nye.docassist.request.ClientRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public interface IClientService {

    ClientEntity saveClient(ClientRequest clientRequest);

    ClientEntity getClientById(Long id) throws ClientNotFoundException;

    ClientEntity updateClient(Long id, ClientRequest clientRequest) throws ClientNotFoundException;

    void deleteClientById(Long id) throws ClientNotFoundException;

    List<ClientEntity> findAllByAge(int age);

    /**
     * Retrieves all clients.
     * @return a list of all ClientEntity
     */
    List<ClientEntity> findAll();
}
