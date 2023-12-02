package com.example.arendapro.openfeign.GeocoderResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResponse {
    private ResponseWrapper response;

    public ResponseWrapper getResponse() {
        return response;
    }

    public void setResponse(ResponseWrapper response) {
        this.response = response;
    }
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class ResponseWrapper {
    private GeoObjectCollection GeoObjectCollection;

    public GeoObjectCollection getGeoObjectCollection() {
        return GeoObjectCollection;
    }

    public void setGeoObjectCollection(GeoObjectCollection geoObjectCollection) {
        GeoObjectCollection = geoObjectCollection;
    }
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class GeoObjectCollection {
    private MetaDataProperty metaDataProperty;
    private List<FeatureMember> featureMember;

    public MetaDataProperty getMetaDataProperty() {
        return metaDataProperty;
    }

    public void setMetaDataProperty(MetaDataProperty metaDataProperty) {
        this.metaDataProperty = metaDataProperty;
    }

    public List<FeatureMember> getFeatureMember() {
        return featureMember;
    }

    public void setFeatureMember(List<FeatureMember> featureMember) {
        this.featureMember = featureMember;
    }
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class MetaDataProperty {
    private GeocoderResponseMetaData GeocoderResponseMetaData;

    public GeocoderResponseMetaData getGeocoderResponseMetaData() {
        return GeocoderResponseMetaData;
    }

    public void setGeocoderResponseMetaData(GeocoderResponseMetaData geocoderResponseMetaData) {
        GeocoderResponseMetaData = geocoderResponseMetaData;
    }
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class GeocoderResponseMetaData {
    private String request;
    private String found;
    private String results;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class FeatureMember {
    private GeoObject GeoObject;

    public GeoObject getGeoObject() {
        return GeoObject;
    }

    public void setGeoObject(GeoObject geoObject) {
        GeoObject = geoObject;
    }
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class GeoObject {
    private MetaDataProperty metaDataProperty;
    private String description;
    private String name;
    private BoundedBy boundedBy;
    private Point Point;

    public MetaDataProperty getMetaDataProperty() {
        return metaDataProperty;
    }

    public void setMetaDataProperty(MetaDataProperty metaDataProperty) {
        this.metaDataProperty = metaDataProperty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoundedBy getBoundedBy() {
        return boundedBy;
    }

    public void setBoundedBy(BoundedBy boundedBy) {
        this.boundedBy = boundedBy;
    }

    public Point getPoint() {
        return Point;
    }

    public void setPoint(Point point) {
        Point = point;
    }
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class BoundedBy {
    private Envelope Envelope;

    public Envelope getEnvelope() {
        return Envelope;
    }

    public void setEnvelope(Envelope envelope) {
        Envelope = envelope;
    }
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Envelope {
    private String lowerCorner;
    private String upperCorner;

    public String getLowerCorner() {
        return lowerCorner;
    }

    public void setLowerCorner(String lowerCorner) {
        this.lowerCorner = lowerCorner;
    }

    public String getUpperCorner() {
        return upperCorner;
    }

    public void setUpperCorner(String upperCorner) {
        this.upperCorner = upperCorner;
    }
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Point {
    private String pos;

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
