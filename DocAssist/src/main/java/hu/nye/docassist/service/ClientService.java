package hu.nye.docassist.service;
import java.util.List;

import hu.nye.docassist.entity.ClientEntity;
import hu.nye.docassist.repository.ClientRepository;
import hu.nye.docassist.request.ClientRequest;
import hu.nye.docassist.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientEntity saveClient(ClientRequest clientRequest) {
        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setFirstName(clientRequest.getFirstName());
        clientEntity.setLastName(clientRequest.getLastName());
        clientEntity.setAge(clientRequest.getAge());
        clientEntity.setEmail(clientRequest.getEmail());
        clientEntity.setPhonenumber(clientRequest.getPhoneNumber());
        clientEntity.setDisease(clientRequest.getDisease());
        clientEntity.setRegistrationDate(clientRequest.getRegistrationDate());

        return clientRepository.save(clientEntity);
    }

    @Override
    public ClientEntity getClientById(Long id) throws ClientNotFoundException {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));
    }

    @Override
    public ClientEntity updateClient(Long id, ClientRequest clientRequest) throws ClientNotFoundException {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));

        clientEntity.setFirstName(clientRequest.getFirstName());
        clientEntity.setLastName(clientRequest.getLastName());
        clientEntity.setAge(clientRequest.getAge());
        clientEntity.setEmail(clientRequest.getEmail());
        clientEntity.setPhonenumber(clientRequest.getPhoneNumber());
        clientEntity.setDisease(clientRequest.getDisease());
        clientEntity.setRegistrationDate(clientRequest.getRegistrationDate());

        return clientRepository.save(clientEntity);
    }

    @Override
    public void deleteClientById(Long id) throws ClientNotFoundException {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientEntity> findAllByAge(int age) {
        return clientRepository.findAllByAge(age);
    }
}
