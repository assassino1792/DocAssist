package hu.nye.docassist.controller;

import hu.nye.docassist.entity.ClientEntity;
import hu.nye.docassist.exception.ClientNotFoundException;
import hu.nye.docassist.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import hu.nye.docassist.request.ClientRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.constraints.Email;



@Controller
@RequestMapping("/registration")
public class WebControllerList {

    @Autowired
    private IClientService clientService;

    // Lista megjelenítése
    @GetMapping("/list")
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "list";
    }

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




    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        try {
            clientService.deleteClientById(id);
            redirectAttributes.addFlashAttribute("success", "Client deleted successfully");
        } catch (ClientNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "No client found with ID: " + id);
        }
        return "redirect:/registration/list";
    }


}