package kea.sem3.jwtdemo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private LocalDateTime reservationDate;
    private LocalDateTime rentalDate;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateEdited;

    public Reservation() {
    }

    public Reservation(LocalDateTime reservationDate, LocalDateTime rentalDate) {
        this.reservationDate = reservationDate;
        this.rentalDate = rentalDate;
    }

    public int getId() {
        return Id;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }
}
