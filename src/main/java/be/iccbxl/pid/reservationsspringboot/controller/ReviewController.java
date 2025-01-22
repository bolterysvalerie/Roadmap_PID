package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.Review;

import be.iccbxl.pid.reservationsspringboot.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/reviews")
    public String getAllReviews(Model model) {
        List<Review> reviews = reviewService.getAll();
        model.addAttribute("reviews", reviews);
        model.addAttribute("title", "Liste des avis");
        return "review/index"; // Assurez-vous que ce fichier existe
    }

    // Afficher les détails d'un avis spécifique
    @GetMapping("/reviews/{id}")
    public String review(Model model, @PathVariable("id") String id) {
        Review review = reviewService.get(id); // Corrigé pour appeler reviewService.get

        if (review == null) {
            // Gérer le cas où l'avis n'existe pas
            return "error/404"; // Assurez-vous qu'une page 404 existe dans templates/error/
        }

        model.addAttribute("review", review); // Ajouter l'avis à la vue
        model.addAttribute("title", "Fiche d'un avis");

        return "review/show"; // Corrigé pour pointer vers le bon fichier
    }

}

