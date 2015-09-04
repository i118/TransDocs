package com.td.model.dto.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.annotation.DTO;
import com.td.model.dto.DirtySupportDTO;
import com.td.model.dto.ModelDTO;
import com.td.model.entity.document.OrderAdditional;

import javax.persistence.Column;

/**
 * Created by zerotul.
 */
@DTO(mappedBy = OrderAdditional.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderAdditionalDTO extends DirtySupportDTO{
    private static final long serialVersionUID = -1828404623799265876L;

    private String transportType;

    private String borderCrossing;

    private String temperatureRegime;

    private String additionalService;

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getBorderCrossing() {
        return borderCrossing;
    }

    public void setBorderCrossing(String borderCrossing) {
        this.borderCrossing = borderCrossing;
    }

    public String getTemperatureRegime() {
        return temperatureRegime;
    }

    public void setTemperatureRegime(String temperatureRegime) {
        this.temperatureRegime = temperatureRegime;
    }

    public String getAdditionalService() {
        return additionalService;
    }

    public void setAdditionalService(String additionalService) {
        this.additionalService = additionalService;
    }
}
