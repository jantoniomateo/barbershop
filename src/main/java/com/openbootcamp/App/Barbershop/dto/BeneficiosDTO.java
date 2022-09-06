package com.openbootcamp.App.Barbershop.dto;

import java.time.LocalDate;

public class BeneficiosDTO {

    private Double beneficios;
    private int day;
    private int month;
    private int year;

    public BeneficiosDTO(){}

    public BeneficiosDTO(Double beneficios) {
        this.beneficios = beneficios;
    }

    public BeneficiosDTO(Double beneficios, int day, int month, int year) {
        this.beneficios = beneficios;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Double getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(Double beneficios) {
        this.beneficios = beneficios;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
