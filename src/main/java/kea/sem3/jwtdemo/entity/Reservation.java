package kea.sem3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    private Car reservedCar;

    @ManyToOne
    private Member reservedByMember;

    @CreationTimestamp
    private LocalDateTime reservationDate;

    private LocalDateTime rentalDate;

    @UpdateTimestamp
    private LocalDateTime dateEdited;

    public Reservation() {
    }

    //TODO: reservation constructor need fixing
    public Reservation(LocalDateTime rentalDate, Member reservedByMember, Car reservedCar) {
        this.rentalDate = rentalDate;
        this.reservedByMember = reservedByMember;
        this.reservedCar = reservedCar;
        //reservedCar.addReservation(this);
        //reservedByMember.addReservation(this);
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

    public Car getReservedCar() {
        return reservedCar;
    }

    public Member getReservedByMember() {
        return reservedByMember;
    }
}
