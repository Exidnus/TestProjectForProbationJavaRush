package testproject.data;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import testproject.Person;

import java.util.List;

/**
 * Created by Exidnus on 09.01.2016.
 */
@Repository
public class PersonRepostoryHibernateTransactional extends HibernateDaoSupport implements PersonRepository {
    @Override
    public void addPerson(Person person) {

    }

    @Override
    public List<Person> getAll() {
        return null;
    }


}
