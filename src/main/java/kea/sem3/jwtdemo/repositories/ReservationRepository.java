package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findReservationByReservedCar_IdAndRentalDate (int id, LocalDateTime dateTime);

}
