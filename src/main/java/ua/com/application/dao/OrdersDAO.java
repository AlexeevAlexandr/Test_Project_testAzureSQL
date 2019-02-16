package ua.com.application.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ua.com.application.entity.Orders;

import java.util.List;

public class OrdersDAO {

    private Transaction transaction = null;

    public void writeNewOrdersToDatabase(String id, String name, String description, double sum, String counterparty_uuid, String moment){
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession()){

            transaction = session.beginTransaction();
            Orders orders = new Orders(id,name,description,sum,counterparty_uuid,moment);
            session.save(orders);
            transaction.commit();

        }catch (HibernateException e){
            if (transaction != null){ transaction.rollback(); }
            e.printStackTrace();
        }
    }

    public int getLastNameOfOrderFromDatabase(){
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession()){

            List list = session.createQuery("SELECT MAX(name) FROM Orders").list();
            String string = list.get(0).toString();
            return Integer.parseInt(string);

        }catch (NullPointerException e){
            return 0;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List getOrdersByNameOfClient(String clientName){
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession()){

            return session.createQuery("FROM Orders WHERE counterparty_uuid=" +
                    "(SELECT id_clients FROM Clients WHERE name='" + clientName + "')").list();

        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    public List getOrderByNameOfOrder(String name){
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession()){

            return session.createQuery("FROM Orders WHERE name = '" + name + "'").list();

        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    public List getAllOrdersFromDatabase(){
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession()){

            return session.createQuery("FROM Orders").list();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }
}
