package hu.nye.docassist.controller;

import hu.nye.docassist.entity.ClientEntity;
import hu.nye.docassist.exception.ClientNotFoundException;
import hu.nye.docassist.request.ClientRequest;
import hu.nye.docassist.service.IClientService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

import java.util.List;
@RestController
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private IClientService clientService;

    @PostMapping("/users")
    public ResponseEntity<ClientEntity> saveUser(@RequestBody ClientRequest request) {
        logger.info("Received request: {}", request);
        try {
            ClientEntity savedClient = clientService.saveClient(request);
            return ResponseEntity.ok(savedClient);
        } catch (ValidationException e) {
            logger.error("Validation error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ClientEntity getClient(@PathVariable("id") Long id) throws ClientNotFoundException {
        return clientService.getClientById(id);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleClientNotFound(ClientNotFoundException e) {
        return e.getMessage();
    }

    @DeleteMapping("/users/{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClientById(id);
    }

    @GetMapping("/users/age/{age}")
    public List<ClientEntity> getAllUserByAge(@PathVariable("age") int age) {
        return clientService.findAllByAge(age);
    }

}


