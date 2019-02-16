package ua.com.test;

import org.junit.Test;
import ua.com.application.Main;
import ua.com.application.dao.OrdersDAO;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class Tests {

    @Test
    public void test_getOrdersByNameOfClient(){
        String expected = "" +
                "[id: 6cdcf105-2891-11e9-9ff4-34e8001bea86\n" +
                "name: 00003\n" +
                "description: \n" +
                "sum: 300000.0\n" +
                "counterparty_uuid: caa9e602-288c-11e9-9ff4-3150006975d1\n" +
                "moment: 2019-02-04 18:27:00\n" +
                ", id: a9272c07-2ed6-11e9-9ff4-34e8000924d3\n" +
                "name: 00012\n" +
                "description: \n" +
                "sum: 300000.0\n" +
                "counterparty_uuid: caa9e602-288c-11e9-9ff4-3150006975d1\n" +
                "moment: 2019-02-12 17:58:00\n" +
                "]";
        String actual = new OrdersDAO().getOrdersByNameOfClient("S&P Global").toString();

        assertEquals(expected, actual);
    }

    @Test
    public void main() {
        Main.main(new String[] {});
    }

    @Test
    public void test_getAllOrdersFromDatabase() {
        List list = new OrdersDAO().getAllOrdersFromDatabase();
        for (Object a : list) {
            System.out.println(a);
            System.out.println();
        }
    }

    @Test
    public void test_getOrderByNameOfOrder() {
        String expected = "id: 4d802cc6-2891-11e9-9107-5048001bc000\n" +
                "name: 00001\n" +
                "description: \n" +
                "sum: 300000.0\n" +
                "counterparty_uuid: ca4f41bb-288c-11e9-9ff4-31500069753f\n" +
                "moment: 2019-02-04 18:26:00";
        String actual = new OrdersDAO().getOrderByNameOfOrder("00001").get(0).toString().trim();

        assertEquals(expected,actual);
    }
}
