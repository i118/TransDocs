package com.td.model.entity.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by zerotul.
 */
@Embeddable
@Access(AccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderAdditional implements Serializable{

    private static final long serialVersionUID = -1815608856438000190L;

    @Column(name = Columns.TRANSPORT_TYPE)
    private String transportType;

    @Column(name = Columns.BORDER_СROSSING)
    private String borderCrossing;

    @Column(name = Columns.TEMPERATURE_REGIME)
    private String temperatureRegime;

    @Column(name = Columns.ADDITIONAL_SERVICE)
    private String additionalService;

    public OrderAdditional(){}

    public static class Columns{
        public static final String TRANSPORT_TYPE = "transport_type";
        public static final String BORDER_СROSSING = "border_сrossing";
        public static final String TEMPERATURE_REGIME = "temperature_regime";
        public static final String ADDITIONAL_SERVICE = "additional_service";
    }

    public String getTransportType() {
        return transportType;
    }

    public String getBorderCrossing() {
        return borderCrossing;
    }

    public String getTemperatureRegime() {
        return temperatureRegime;
    }

    public String getAdditionalService() {
        return additionalService;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public void setBorderCrossing(String borderCrossing) {
        this.borderCrossing = borderCrossing;
    }

    public void setTemperatureRegime(String temperatureRegime) {
        this.temperatureRegime = temperatureRegime;
    }

    public void setAdditionalService(String additionalService) {
        this.additionalService = additionalService;
    }

    public static class OrderAdditionalBuilder{

        private String transportType;

        private String borderCrossing;

        private String temperatureRegime;

        private String additionalService;

        private OrderAdditionalBuilder(){}

        private OrderAdditionalBuilder(String transportType, String borderCrossing, String temperatureRegime, String additionalService) {
            this.transportType = transportType;
            this.borderCrossing = borderCrossing;
            this.temperatureRegime = temperatureRegime;
            this.additionalService = additionalService;
        }


        public static OrderAdditionalBuilder with(){
            return new OrderAdditionalBuilder();
        }

        public static OrderAdditionalBuilder with(String transportType, String borderСrossing, String temperatureRegime, String additionalService){
            return new OrderAdditionalBuilder(transportType, borderСrossing, temperatureRegime, additionalService);
        }

        public OrderAdditional build(){
            OrderAdditional additional = new OrderAdditional();
            additional.additionalService = this.additionalService;
            additional.borderCrossing = this.borderCrossing;
            additional.temperatureRegime = this.temperatureRegime;
            additional.transportType = this.transportType;
            return additional;
        }

        public OrderAdditionalBuilder setTransportType(String transportType) {
            this.transportType = transportType;
            return this;
        }

        public OrderAdditionalBuilder setBorderСrossing(String borderСrossing) {
            this.borderCrossing = borderСrossing;
            return this;
        }

        public OrderAdditionalBuilder setTemperatureRegime(String temperatureRegime) {
            this.temperatureRegime = temperatureRegime;
            return this;
        }

        public OrderAdditionalBuilder setAdditionalService(String additionalService) {
            this.additionalService = additionalService;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderAdditional that = (OrderAdditional) o;

        if (transportType != null ? !transportType.equals(that.transportType) : that.transportType != null)
            return false;
        if (borderCrossing != null ? !borderCrossing.equals(that.borderCrossing) : that.borderCrossing != null)
            return false;
        if (temperatureRegime != null ? !temperatureRegime.equals(that.temperatureRegime) : that.temperatureRegime != null)
            return false;
        return !(additionalService != null ? !additionalService.equals(that.additionalService) : that.additionalService != null);

    }

    @Override
    public int hashCode() {
        int result = transportType != null ? transportType.hashCode() : 0;
        result = 31 * result + (borderCrossing != null ? borderCrossing.hashCode() : 0);
        result = 31 * result + (temperatureRegime != null ? temperatureRegime.hashCode() : 0);
        result = 31 * result + (additionalService != null ? additionalService.hashCode() : 0);
        return result;
    }
}
