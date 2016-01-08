package testproject;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Exidnus on 06.01.2016.
 */
@Entity
@Table(name="persons")
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;
    @Column(name="is_admin")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private boolean isAdmin;
    @Column(name="created_date")
    private Timestamp createdDate;

    public Person() {}

    public Person(String name, int age, Timestamp createdDate, boolean isAdmin) {
        this.age = age;
        this.createdDate = createdDate;
        this.isAdmin = isAdmin;
        this.name = name;
    }

    public Person(String name, int age, Timestamp createdDate) {
        this(name, age, createdDate, false);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id).append(name).append(age)
                .append(createdDate).append(isAdmin).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Person that = (Person) obj;
        return new EqualsBuilder().append(this.name, that.name)
                .append(this.age, that.age).append(this.createdDate, that.createdDate)
                .append(this.isAdmin, that.isAdmin).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.NO_FIELD_NAMES_STYLE).append(id)
                .append(name).append(age).append(createdDate).append(isAdmin).toString();
    }
}
