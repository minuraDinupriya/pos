package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Orders {
    @Id
    private String orderId;
    private String date;

    @ManyToOne
    @JoinColumn(name = "customer_id")//customer entity eke thiyena primary key ek order table eke hadena
    private Customer customer;//       customer_id ekt set krnn kiyal kiyanne(foreign key ek widiyata)

    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Orders(String orderId, String date) {
        this.orderId = orderId;
        this.date = date;
    }
}