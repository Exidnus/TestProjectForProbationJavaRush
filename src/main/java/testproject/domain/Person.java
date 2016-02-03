package testproject.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Exidnus on 06.01.2016.
 */
@Entity
@Table(name="user")
public class Person implements Serializable {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Type(type="org.hibernate.type.NumericBooleanType")
    private boolean isAdmin;
    private Date createdDate;

    public Person() {}

    public Person(String name, int age, boolean isAdmin) {
        this.age = age;
        this.createdDate = new Date();
        this.isAdmin = isAdmin;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimpleDate() {
        return simpleDateFormat.format(createdDate);
    }

    public String getStringAdmin() {
        return isAdmin ? "Да" : "Нет";
    }

    /*
    В документации к Hibernate настойчиво рекомендуется
    реализовывать equals и hashcode на основе id, что я и сделал
     */

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Person that = (Person) obj;
        return new EqualsBuilder().append(this.id, that.id).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.NO_FIELD_NAMES_STYLE).append(id)
                .append(name).append(age).append(createdDate).append(isAdmin).toString();
    }
}
