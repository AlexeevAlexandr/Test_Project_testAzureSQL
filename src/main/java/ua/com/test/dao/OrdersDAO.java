package ua.com.test.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ua.com.test.entity.Alexandr_Orders;

import java.util.List;

public class OrdersDAO {
    private Transaction transaction = null;

    public void writeOrders(String id, String name, String description, double sum, String counterparty_uuid, String moment){
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession()){

            transaction = session.beginTransaction();
            Alexandr_Orders orders = new Alexandr_Orders(id,name,description,sum,counterparty_uuid,moment);
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

            List list = session.createQuery("SELECT MAX(name) FROM Alexandr_Orders").list();
            String string = list.get(0).toString();
            return Integer.parseInt(string);

        }catch (HibernateException e){
            e.printStackTrace();
        }
        return 0;
    }
}
