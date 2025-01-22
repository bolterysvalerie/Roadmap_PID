package be.iccbxl.pid.reservationsspringboot.repository;

import be.iccbxl.pid.reservationsspringboot.model.Review;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.model.User;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {


    List<Review> findByUser(User user);
    List<Review> findByShow(Show show);
}

