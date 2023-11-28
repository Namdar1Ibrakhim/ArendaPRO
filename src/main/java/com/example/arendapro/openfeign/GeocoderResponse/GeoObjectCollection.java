package com.example.arendapro.openfeign.GeocoderResponse;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Data;

import java.util.List;


@Data
public class GeoObjectCollection {

    @JsonbProperty("metaDataProperty")
    private MetaDataProperty metaDataProperty;

    @JsonbProperty("featureMember")
    private List<FeatureMember> featureMember;

}
