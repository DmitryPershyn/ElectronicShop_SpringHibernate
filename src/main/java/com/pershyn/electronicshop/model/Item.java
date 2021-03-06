package com.pershyn.electronicshop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cart_item")
@EqualsAndHashCode(exclude = {"id", "cart"})
@ToString(exclude = {"cart"})
@NoArgsConstructor
public class Item {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count")
    private Integer count;

}