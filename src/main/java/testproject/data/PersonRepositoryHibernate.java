package testproject.data;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import testproject.domain.Person;

import java.util.List;

/**
 * Created by Exidnus on 08.01.2016.
 */
@Repository
@Primary
public class PersonRepositoryHibernate implements PersonRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public PersonRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Person> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Person.class).addOrder(Order.asc("name")).list();
    }

    @Override
    public List<Person> getPage(int offset, int length) {
        return sessionFactory.getCurrentSession().createCriteria(Person.class).addOrder(Order.asc("name"))
                .setFirstResult(offset).setMaxResults(length).list();
    }

    @Override
    public void addPerson(Person person) {
        sessionFactory.getCurrentSession().save(person);
    }

    @Override
    public void deletePersonById(int id) {
        sessionFactory.getCurrentSession().createQuery("DELETE Person WHERE id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public List<Person> findByName(String name) {
        return sessionFactory.getCurrentSession().createCriteria(Person.class)
                .add(Restrictions.eq("name", name)).list();
    }

    @Override
    public long getCount() {
        return (long) sessionFactory.getCurrentSession().createCriteria(Person.class)
                .setProjection(Projections.countDistinct("id")).uniqueResult();
    }

    @Override
    public Person getPersonById(int id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Override
    public void update(Person person) {
        sessionFactory.getCurrentSession().update(person);
    }
}
