package hu.nye.docassist.controller;

import hu.nye.docassist.entity.ClientEntity;
import hu.nye.docassist.exception.ClientNotFoundException;
import hu.nye.docassist.service.IClientService;
import hu.nye.docassist.request.ClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.time.LocalDate;


@Controller
@RequestMapping("/registration/update")
public class WebUpdateController {

    @Autowired
    private IClientService clientService;

    Logger logger = LoggerFactory.getLogger(WebUpdateController.class);

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        try {
            ClientEntity client = clientService.getClientById(id);
            model.addAttribute("client", client);
            return "update";
        } catch (ClientNotFoundException e) {
            model.addAttribute("error", "Client not found with ID: " + id);
            return "error-page";
        }
    }

    @PostMapping("/{id}")
    public String updateClient(@PathVariable("id") long id, @ModelAttribute ClientEntity clientData, RedirectAttributes redirectAttributes) {
        logger.info("Updating client with ID: {}", id);
        try {
            ClientRequest clientRequest = convertToClientRequest(clientData);
            logger.debug("Converted ClientRequest: {}", clientRequest);
            clientService.updateClient(id, clientRequest);
            redirectAttributes.addFlashAttribute("success", "Client updated successfully");
            return "redirect:/registration/list";
        } catch (ClientNotFoundException e) {
            logger.error("Client not found with ID: {}", id, e);
            redirectAttributes.addFlashAttribute("error", "Client not found with ID: " + id);
            return "redirect:/registration/list";
        }
    }

    private ClientRequest convertToClientRequest(ClientEntity clientEntity) {
        return ClientRequest.builder()
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .age(clientEntity.getAge())
                .problem(clientEntity.getProblem())
                .email(clientEntity.getEmail())
                .phoneNumber(clientEntity.getPhonenumber())
                .registrationDate(LocalDate.now())
                .build();
    }
}
