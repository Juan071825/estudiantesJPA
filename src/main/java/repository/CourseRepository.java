package repository;

import entities.Student;
import jakarta.persistence.EntityManager;
import entities.Course;
import java.util.List;

public class CourseRepository implements Repository<Course> {

        private final EntityManager entityManager;

        public CourseRepository(EntityManager entityManager){
            this.entityManager = entityManager;
        }

        public Course findById(int id) {
            return entityManager.find(Course.class, id);
        }
        public List<Course> findAll() {
            return entityManager.createQuery(" SELECT DISTINCT c FROM Course c " +
                            "LEFT JOIN FETCH c.students", Course.class)
                    .getResultList();
        }

        @Override
        public void save(Course course) {
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
        }

        @Override
        public void delete(Course course) {
            entityManager.getTransaction().begin();
            entityManager.remove(course);
            entityManager.getTransaction().commit();
        }

        @Override
        public void update(Course course) {
            entityManager.getTransaction().begin();
            entityManager.merge(course);
            entityManager.getTransaction().commit();
        }

}
