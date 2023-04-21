package com.cpan228.distributionCentre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String itemName;
    private String brandName;
    @Min(2021)
    private int itemYear;
    @Min(1000)
    private double price;
    private int quantity;
    public void setQuantity(int newQuantity){
        this.quantity = newQuantity;
    }
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "distribution_centre_id", nullable = false)
    private DistributionCentre distributionCentre;
}