package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.model.ArtistType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.service.ShowService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShowController {
    @Autowired
    ShowService service;


    @GetMapping("/shows")
    public String index(Model model) {
        List<Show> shows = service.getAll();

        model.addAttribute("shows", shows);
        model.addAttribute("title", "Liste des spectacles");

        return "show/index";
    }

    @GetMapping("/shows/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        Show show = service.get(id);

        if (show == null) {
            return "redirect:/shows"; // Redirection vers la liste des spectacles
        }

        //Récupérer les artistes du spectacle et les grouper par type
        Map<String, ArrayList<Artist>> collaborateurs = new TreeMap<>();

        for(ArtistType at : show.getArtistTypes()) {
            String type = at.getType().getType();

            if(collaborateurs.get(type) == null) {
                collaborateurs.put(type, new ArrayList<>());
            }

            collaborateurs.get(type).add(at.getArtist());
        }

        // Ajouter les informations au modèle
        model.addAttribute("show", show);
        model.addAttribute("collaborateurs", collaborateurs);
        model.addAttribute("title", "Fiche d'un spectacle");

        // Retourner la vue associée
        return "show/show";
    }

    // Partie Back-office uniquement pour les admins

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("shows/create")
    public String createForm(Model model) {
        model.addAttribute("show", new Show());
        model.addAttribute("title", "Créer un spectacle");
        return "show/create";
    }


    @PostMapping("shows/create")
    public String create(@ModelAttribute Show show) {
        service.add(show);
        return "redirect:/shows";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/shows/{id}/edit")
    public String editForm(Model model, @PathVariable("id") String id, HttpServletRequest request) {
        Show show = service.get(id);

        if (show == null) {
            return "redirect:/shows";
        }

        model.addAttribute("show", show);

        // Générer le lien retour pour l'annulation
        String referrer = request.getHeader("Referer");

        if (referrer != null && !referrer.equals("")) {
            model.addAttribute("back", referrer);
        } else {
            model.addAttribute("back", "/shows/" + show.getId());
        }

        return "show/edit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/shows/{id}/edit")
    public String update(@Valid @ModelAttribute Show show,
                         BindingResult bindingResult,
                         @PathVariable("id") String id,
                         Model model,
                         RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Échec de la modification du spectacle !");
            return "show/edit";
        }

        Show existing = service.get(id);

        if (existing == null) {
            return "show/index";
        }

        service.update(id, show);
        redirAttrs.addFlashAttribute("successMessage", "Spectacle modifié avec succès.");

        return "redirect:/shows/" + show.getId();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/shows/delete/{id}")
    public String delete(@PathVariable("id") String id, RedirectAttributes redirAttrs) {
        service.delete(id);
        redirAttrs.addFlashAttribute("successMessage", "Le spectacle a été supprimé avec succès.");
        return "redirect:/shows";
    }
}

