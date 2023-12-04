package com.example.arendapro.config.redis;

import com.example.arendapro.entity.Favorites;
import com.example.arendapro.entity.User;
import com.example.arendapro.entity.address.Address;
import com.example.arendapro.enums.PropertyType;
import com.example.arendapro.enums.State;
import com.example.arendapro.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("ImmovablesCache")
public class ImmovablesCache implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private User owner;

    private String title;

    private Integer numOfRooms;

    private Double area;

    private String description;

    private Long price;

    private Date createdAt;

    private List<String> images;

    private PropertyType propertyType;

    private State state;

    private Status status;

    private Address address;

    private List<Favorites> favorites;

}
