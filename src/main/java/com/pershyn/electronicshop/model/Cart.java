package com.pershyn.electronicshop.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "cart")
@EqualsAndHashCode(exclude = {"id", "items", "user"})
@ToString(exclude = {"user"})
@NoArgsConstructor
public class Cart {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private Set<Item> items;

    @Column(name = "sum")
    private Double sum = 0d;

    public void addItem(Item item) {
        items.add(item);
    }

    public Item getItem(Integer id) {
        return items.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }
}
