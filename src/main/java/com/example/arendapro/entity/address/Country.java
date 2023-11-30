package com.example.arendapro.entity.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country")
public class Country implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String iso;

}
