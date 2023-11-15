package com.example.arendapro.entity;

import com.example.arendapro.entity.address.Address;
import com.example.arendapro.enums.PropertyType;
import com.example.arendapro.enums.State;
import com.example.arendapro.enums.Status;
import com.example.arendapro.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Immovables {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private String title;

    private int numOfRooms;

    private double area;

    private String description;

    private long price;

    @OneToMany(mappedBy = "immovable")
    private List<ImageData> images;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "immovable")
    private List<Favorites> favorites;

}
