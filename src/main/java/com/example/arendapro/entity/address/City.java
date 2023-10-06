package com.example.arendapro.entity.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToOne(mappedBy = "city")
    private Address address;
}
