package hu.nye.docassist.controller;

import hu.nye.docassist.entity.ClientEntity;
import hu.nye.docassist.service.IClientService;
import hu.nye.docassist.request.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
@Controller
public class WebController {

    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private IClientService clientService;

    @GetMapping("/clients")
    public String showForm(Model model) {
        model.addAttribute("client", new ClientEntity());
        return "users"; // Az űrlap megjelenítése
    }

    @PostMapping("/clients/new")
    public ResponseEntity<Long> submitForm(@ModelAttribute ClientEntity clientEntity) {
        ClientEntity savedClient = clientService.saveClient(convertToClientRequest(clientEntity));
        logger.info("Successfully saved user via form: {}", clientEntity);
        return ResponseEntity.ok(savedClient.getId()); // Visszaadja az ID-t JSON formátumban
    }

    private ClientRequest convertToClientRequest(ClientEntity clientEntity) {
        return ClientRequest.builder()
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .age(clientEntity.getAge())
                .disease(clientEntity.getDisease())
                .email(clientEntity.getEmail())
                .phoneNumber(clientEntity.getPhonenumber())
                .registrationDate(LocalDate.now())
                .build();
    }
}


