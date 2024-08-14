package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private static final Logger logger = Logger.getLogger(UserDaoHibernateImpl.class.getName());



    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "lastName VARCHAR(255) NOT NULL, " +
                    "age TINYINT NOT NULL, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            logger.log(Level.INFO, "Таблицы users создана");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.log(Level.SEVERE,"Проблема при создании таблицы ", e);
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            logger.log(Level.INFO, "Таблица users удалена");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.log(Level.SEVERE,"Проблема при удалении таблицы ", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            logger.info(String.format("Пользователь добавлен: [name: %s, lastName: %s, age: %d]", name, lastName, age));
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.log(Level.SEVERE, "Проблема при добвлении пользователя ", e);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            logger.log(Level.INFO, "Пользователь удален");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.log(Level.SEVERE, "Проблема при удалении пользователя ", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<User> userList = session.createQuery("from User", User.class).list();
            transaction.commit();
            logger.log(Level.INFO, "Пользователи получены");
            return userList;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.log(Level.SEVERE, "Проблема при получении пользователей ", e);
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE users;").executeUpdate();
            transaction.commit();
            logger.log(Level.INFO, "Таблица очищена");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.log(Level.SEVERE,"Проблема при очистке таблицы ", e);
        }
    }
}
