import entities.Card;
import entities.Course;
import entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.CardRepository;
import repository.CourseRepository;
import repository.Repository;
import repository.StudentRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class App {


    public static void main( String[] args) {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("default")) {
            System.out.println("Script generado en schema.sql");
        }

        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
             EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            Repository<Card> cardRepository = new CardRepository(entityManager);

            Card paco_card = new Card();
            paco_card.setCode(1);
            paco_card.setType(Card.Type.bac);
            cardRepository.save(paco_card);

            Card paca_card = new Card();
            paca_card.setCode(2);
            paca_card.setType(Card.Type.fp);
            cardRepository.save(paca_card);

            CourseRepository courseRepository = new CourseRepository(entityManager);

            Course tic = new Course();
            tic.setName("TIC");
            tic.setBeginDate(LocalDate.of(2026, 9, 8));
            tic.setEndDate(LocalDate.of(2027, 6, 5));
            courseRepository.save(tic);


            StudentRepository studentRepository = new StudentRepository(entityManager);

            Student paco = new Student();
            paco.setDNI("00000000T");
            paco.setName("Paco");
            paco.setPhone("622222222");
            paco.setCard(paco_card);
            Set<Course> courses_paco = new HashSet<>();
            courses_paco.add(tic);
            paco.setCourses(courses_paco);
            studentRepository.save(paco);

            Student paca = new Student();
            paca.setDNI("12345678Z");
            paca.setName("Paca");
            paca.setPhone("611111111");
            paca.setCard(paca_card);
            Set<Course> courses_paca = new HashSet<>();
            courses_paca.add(tic);
            paca.setCourses(courses_paca);
            studentRepository.save(paca);

        }

    }

}