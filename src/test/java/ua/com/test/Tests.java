package ua.com.test;

import org.junit.Test;
import ua.com.application.Main;
import ua.com.application.dao.OrdersDAO;

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
}
