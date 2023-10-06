package com.example.arendapro.entity.address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class District {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToOne(mappedBy = "district")
    private Address address;
}
