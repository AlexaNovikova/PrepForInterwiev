package ru.gb;

import org.hibernate.SessionFactory;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            StudentDao studentDao = new StudentDao(sessionFactory);
            System.out.println(studentDao.findAll());
            Optional<Student> studentOptional = studentDao.findById(3L);
            if (studentOptional.isPresent()) {
                Student student = studentOptional.get();
                System.out.println(student);
                student.setMark(5);
                studentDao.saveOrUpdate(student);
                System.out.println(studentDao.findById(3L));
                studentDao.delete(student);
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
