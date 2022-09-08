package com.openbootcamp.App.Barbershop.dto;

import java.time.LocalDate;

public class BeneficiosDTO {

    private Double beneficios;

    public BeneficiosDTO(){}

    public BeneficiosDTO(Double beneficios) {
        this.beneficios = beneficios;
    }

    public Double getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(Double beneficios) {
        this.beneficios = beneficios;
    }

}
