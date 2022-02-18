package kea.sem3.jwtdemo.configuration;

import kea.sem3.jwtdemo.entity.*;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.Month;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {


    UserRepository userRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public MakeTestData(UserRepository userRepository, MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public  void makePlainUsers(){
        BaseUser user = new BaseUser("user", "user@a.dk", "test12");
        user.addRole(Role.USER);
        BaseUser admin = new BaseUser("admin", "admin@a.dk", "test12");
        admin.addRole(Role.ADMIN);

        BaseUser both = new BaseUser("user_admin", "both@a.dk", "test12");
        both.addRole(Role.USER);
        both.addRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(both);

        Member m1 = new Member("kolosus", "xxx@a.dk", "pass1234", "Frederik", "Wandall", "vej1", "by1", "2100", true);
        m1.addRole(Role.USER);
        Member m2 = new Member("bruger2", "yyyy@b.dk", "pass1234", "Kirsten", "Wandall", "vej2", "by2", "2840", true);
        m2.addRole(Role.USER);
        Member m3 = new Member("bruger3", "email@cc.dk", "kode1234", "Henrik", "von Benzon", "vej2", "by2", "2840", true);
        memberRepository.save(m1);
        memberRepository.save(m2);
        memberRepository.save(m3);
    }

    public void makeCars(){
        carRepository.save(new Car("Mazda", "MX-5 (Miata)", 1337, 50));
        carRepository.save(new Car("Toyota", "Corolla", 500, 50));
        carRepository.save(new Car("Ford", "Focus", 600, 50));

    }

    public void makeReservations() throws Exception{
        Reservation res1 = new Reservation(LocalDateTime.now().plusDays(1), memberRepository.findById("kolosus").orElseThrow(()-> new Exception("Not Found")), carRepository.getById(1));
        reservationRepository.save(res1);

        Reservation res2 = new Reservation(LocalDateTime.of(2022, 2,22,0,0), memberRepository.findById("bruger2").orElseThrow(()-> new Exception("Not Found")), carRepository.getById(2));
        reservationRepository.save(res2);


        // TODO: Can't get reservationcheck to work

        Reservation res = reservationRepository.findReservationByReservedCar_IdAndRentalDate(1,LocalDateTime.of(2022, 2,22,0,0));
        if (res == null){
            Reservation res3 = new Reservation(LocalDateTime.of(2022, 2,22,0,0),memberRepository.findById("bruger3").orElseThrow(()-> new Exception("Not Found")), carRepository.getById(2));
            reservationRepository.save(res3);
        }
        else{
            System.out.println("Car is already reserved that day");
        }


    }

    public void printDefaultValuesWarning(){
        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("#################################### WARNING ! #########################################");
        System.out.println("## This part breaks a fundamental security rule -> NEVER ship code with default users ##");
        System.out.println("########################################################################################");
        System.out.println("########################  REMOVE BEFORE DEPLOYMENT  ####################################");
        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("Created TEST Users, Cars and Reservations");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.deleteAll();
        carRepository.deleteAll();
        reservationRepository.deleteAll();

        makePlainUsers();
        makeCars();
        makeReservations();

        printDefaultValuesWarning();
    }
}
