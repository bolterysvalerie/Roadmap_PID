

package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.Review;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    /**
     * Récupère tous les avis
     * @return Liste des avis
     */
    public List<Review> getAll() {
        List<Review> reviews = new ArrayList<>();
        repository.findAll().forEach(reviews::add);
        return reviews;
    }

    /**
     * Récupère un avis par son ID
     * @param id ID de l'avis
     * @return L'avis correspondant ou null
     */
    public Review get(String id) {
        Long reviewId = Long.parseLong(id);
        Optional<Review> review = repository.findById(reviewId);
        return review.orElse(null);
    }

    /**
     * Ajoute un nouvel avis
     * @param review Objet Review à ajouter
     */
    public void add(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        repository.save(review);
    }

    /**
     * Met à jour un avis existant
     * @param id ID de l'avis à mettre à jour
     * @param review Nouvelles informations de l'avis
     */
    public void update(String id, Review review) {
        review.setUpdatedAt(LocalDateTime.now());
        repository.save(review);
    }

    /**
     * Supprime un avis
     * @param id ID de l'avis à supprimer
     */
    public void delete(String id) {
        Long reviewId = Long.parseLong(id);
        repository.deleteById(reviewId);
    }

    /**
     * Récupère les avis associés à un utilisateur
     * @param user Utilisateur associé
     * @return Liste des avis
     */
    public List<Review> getByUser(User user) {
        return repository.findByUser(user);
    }

    /**
     * Récupère les avis associés à un spectacle
     * @param show Spectacle associé
     * @return Liste des avis
     */
    public List<Review> getByShow(Show show) {
        return repository.findByShow(show);
    }
}