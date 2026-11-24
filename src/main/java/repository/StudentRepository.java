package repository;

import jakarta.persistence.EntityManager;

import java.util.List;

import entities.Student;


    public class StudentRepository implements Repository<Student> {

        private final EntityManager entityManager;

        public StudentRepository(EntityManager entityManager){
            this.entityManager = entityManager;
        }

        public Student findById(int id) {
            return entityManager.find(Student.class, id);
        }
        public List<Student> findAll() {
            return entityManager.createQuery(" SELECT DISTINCT s FROM Student s " +
                             "JOIN FETCH s.card " +
                            "LEFT JOIN FETCH s.courses", Student.class)
                    .getResultList();
        }

        @Override
        public void save(Student student) {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        }

        @Override
        public void delete(Student student) {
            entityManager.getTransaction().begin();
            entityManager.remove(student);
            entityManager.getTransaction().commit();
        }

        @Override
        public void update(Student student) {
            entityManager.getTransaction().begin();
            entityManager.merge(student);
            entityManager.getTransaction().commit();
        }

}
