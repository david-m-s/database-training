package com.magento.example.repository;

import com.magento.example.domain.NamesOnly;
import com.magento.example.domain.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class JPAUserRepository {

  private EntityManager em;

  public JPAUserRepository(EntityManager em) {
    this.em = em;
  }

  public List<User> findAll() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);
    Root<User> rootEntry = cq.from(User.class);
    CriteriaQuery<User> all = cq.select(rootEntry);
    TypedQuery<User> allQuery = em.createQuery(all);
    return allQuery.getResultList();
  }

  public User save(User user) {
    return em.merge(user);
  }

  public long count() {
    CriteriaBuilder qb = em.getCriteriaBuilder();
    CriteriaQuery<Long> cq = qb.createQuery(Long.class);
    cq.select(qb.count(cq.from(User.class)));
    return em.createQuery(cq).getSingleResult();
  }

  public boolean existsById(Long id) {
    String query = "select count(u) from users u where u.id=:id";
    Long count = (Long) em.createQuery(query).setParameter("id", id).getSingleResult();
    return count == 1;
  }

  public void deleteById(Long id) {
    em.createQuery("delete from users u where u.id=:id").setParameter("id", id).executeUpdate();
  }

  public List<NamesOnly> findByUsernameStartingWith(String usernameLike) {
    return em.createQuery(
        "select new com.magento.example.domain.NamesOnly(u.firstName, u.lastName) from users u where u.username like :code",
        NamesOnly.class).setParameter("code", usernameLike + "%").getResultList();
  }
}
