package ua.com.application.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Alexandr_Orders {

    @Column(name = "id_orders", nullable = false)
    private String id_orders;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sum")
    private double sum;

    @Column(name = "counterparty_uuid")
    private String counterparty_uuid;

    @Column(name = "moment")
    private String moment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "counterparty_uuid", nullable = false)
    private Alexandr_Clients clients;

    public Alexandr_Orders(){}

    public Alexandr_Orders(String id_orders, String name, String description, double sum, String counterparty_uuid, String moment) {
        this.id_orders = id_orders;
        this.name = name;
        this.description = description;
        this.sum = sum;
        this.counterparty_uuid = counterparty_uuid;
        this.moment = moment;
    }

    public String getId_orders() {
        return id_orders;
    }

    public void setId_orders(String id) {
        this.id_orders = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getCounterparty_uuid() {
        return counterparty_uuid;
    }

    public void setCounterparty_uuid(String counterparty_uuid) {
        this.counterparty_uuid = counterparty_uuid;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }

    public Alexandr_Clients getClients() {
        return clients;
    }

    public void setClients(Alexandr_Clients clients) {
        this.clients = clients;
    }

    @Override
    public String toString(){
        return  "id: " + getId_orders() + "\n" +
                "name: " + getName() + "\n" +
                "description: " + getDescription() + "\n" +
                "sum: " + getSum() + "\n" +
                "counterparty_uuid: " + getCounterparty_uuid() + "\n" +
                "moment: " + getMoment() + "\n";
    }
}
