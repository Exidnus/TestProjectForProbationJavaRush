package testproject.data;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import testproject.Person;

import java.util.List;

/**
 * Created by Exidnus on 09.01.2016.
 */
//@Repository
//@Primary
//@Transactional
public class PersonRepostoryHibernateTransactional extends HibernateDaoSupport implements PersonRepository {
    @Override
    public void addPerson(Person person) {

    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> getAll() {
        return getHibernateTemplate().loadAll(Person.class);
    }

    @Autowired
    public void setupSessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public void deletePersonById(int id) {

    }
}
