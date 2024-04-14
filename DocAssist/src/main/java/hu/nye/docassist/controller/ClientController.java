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
            logger.info("Successfully saved user: {}", savedClient);
            return ResponseEntity.ok(savedClient);
        } catch (ValidationException e) {
            logger.error("Validation error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ClientEntity> getClient(@PathVariable("id") Long id) {
        try {
            ClientEntity client = clientService.getClientById(id);
            logger.info("Successfully got user: {}", client);
            return ResponseEntity.ok(client);
        } catch (ClientNotFoundException e) {
            logger.error("Validation error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ClientEntity> updateUser(@PathVariable("id") Long id, @RequestBody ClientRequest request) {
        try {
            ClientEntity updatedClient = clientService.updateClient(id, request);
            logger.info("Successfully updated user: {}", updatedClient);
            return ResponseEntity.ok(updatedClient);
        } catch (ClientNotFoundException e) {
            logger.error("Validation error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleClientNotFound(ClientNotFoundException e) {
        return e.getMessage();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        try {
            clientService.deleteClientById(id);
            logger.info("Successfully deleted user with id: {}", id);
            return ResponseEntity.ok().build();
        } catch (ClientNotFoundException e) {
            logger.error("User not found error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/users/age/{age}")
    public List<ClientEntity> getAllUserByAge(@PathVariable("age") int age) {
        return clientService.findAllByAge(age);
    }

}


