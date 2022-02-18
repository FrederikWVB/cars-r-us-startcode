package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    static int carId1, carId2;

    @BeforeEach
    public void setUp(@Autowired CarRepository carRepository){
        carRepository.deleteAll();
        carId1 = carRepository.save(new Car("Mazda", "MX-5 (Miata)", 1337, 50)).getId();
        carId2 = carRepository.save(new Car("Toyota", "Corolla", 500, 50)).getId();
    }

    @Test
    public void testCount(){
        assertEquals(2, carRepository.count());
    }

    @Test
    public void testGetById(){
        Car cFound = carRepository.findById(carId1).orElse(null);
        assertEquals("Mazda", cFound.getBrand());
    }
}