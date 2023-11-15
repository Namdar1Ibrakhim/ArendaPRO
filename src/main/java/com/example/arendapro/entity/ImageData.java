package com.example.arendapro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String type;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "immovable_id", nullable = false)
    private Immovables immovable;

}
