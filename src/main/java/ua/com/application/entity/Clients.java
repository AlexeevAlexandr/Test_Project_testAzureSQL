package ua.com.application.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Clients {

    @Column(name = "id_clients", nullable = false)
    private String id_clients;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    private Set<Orders> orders;

    public Clients() {}

    public Clients(String id_clients, String name) {
        this.id_clients = id_clients;
        this.name = name;
    }

    public String getId_clients() {
        return id_clients;
    }

    public void setId_clients(String id_clients) {
        this.id_clients = id_clients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString(){
        return  "id: " + getId_clients() + "\n" +
                "name: " + getName();
    }
}
