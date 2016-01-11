package testproject.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import testproject.Person;

import java.util.ArrayList;
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
        Session session = null;
        List<Person> persons = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            persons = session.createCriteria(Person.class).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return persons;
    }

    @Override
    public void addPerson(Person person) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void deletePersonById(int id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.createQuery("DELETE Person WHERE id = :id").setParameter("id", id).executeUpdate();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Person> findByName(String name) {
        List<Person> result = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            result = session.createCriteria(Person.class).add(Restrictions.eq("name", name)).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }
}
