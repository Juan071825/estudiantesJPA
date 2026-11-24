package repository;

import jakarta.persistence.EntityManager;

import java.util.List;
import entities.Card;

public class CardRepository implements Repository<Card>{

    private final EntityManager entityManager;

    public CardRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Card findById(int id) {
        return entityManager.find(Card.class, id);
    }
    public List<Card> findAll() {
        return entityManager.createQuery("SELECT c FROM Card c", Card.class)
                .getResultList();
    }

    @Override
    public void save(Card card) {
        entityManager.getTransaction().begin();
        entityManager.persist(card);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Card card) {
        entityManager.getTransaction().begin();
        entityManager.remove(card);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Card card) {
        entityManager.getTransaction().begin();
        entityManager.merge(card);
        entityManager.getTransaction().commit();
    }

}
