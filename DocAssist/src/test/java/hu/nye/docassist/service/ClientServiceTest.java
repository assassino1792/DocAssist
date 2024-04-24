package hu.nye.docassist.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import hu.nye.docassist.entity.ClientEntity;
import hu.nye.docassist.exception.ClientNotFoundException;
import hu.nye.docassist.repository.ClientRepository;
import hu.nye.docassist.request.ClientRequest;

import java.time.LocalDate;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveClient() {
        ClientRequest request = new ClientRequest("John", "Doe", 30, "Cough", "john.doe@example.com", "+36-2345678", LocalDate.now());
        ClientEntity expectedClient = new ClientEntity(Doe, "John", "30", "john.doe@example.com", "+36-2345678", LocalDate.now());

        when(clientRepository.save(any(ClientEntity.class))).thenReturn(expectedClient);

        ClientEntity result = clientService.saveClient(request);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(clientRepository, times(1)).save(any(ClientEntity.class));
    }

    @Test
    public void testGetClientById() throws ClientNotFoundException {
        Long clientId = 1L;
        ClientEntity client = new ClientEntity(1L, "John", "Doe", 30, "Cough", "john.doe@example.com", "+36-2345678", LocalDate.now());

        when(clientRepository.findById(clientId)).thenReturn(java.util.Optional.of(client));

        ClientEntity result = clientService.getClientById(clientId);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    public void testGetClientById_NotFound() {
        Long clientId = 1L;
        when(clientRepository.findById(clientId)).thenReturn(java.util.Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> {
            clientService.getClientById(clientId);
        });
    }

}
