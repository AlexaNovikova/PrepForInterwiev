package ru.gb;

import org.hibernate.SessionFactory;

public class StudentDao extends AbstractDao<Student, Long> {

    public StudentDao(SessionFactory sessionFactory) {
        super(sessionFactory, Student.class, Long.class);
    }

}
