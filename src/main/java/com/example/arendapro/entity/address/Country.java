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
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String ISO;

    @OneToOne(mappedBy = "country")
    private Address address;
}
