package com.cpan228.distributionCentre.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DistributionCentre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    @OneToMany(mappedBy = "distributionCentre")
    private List<Item> items;
    public boolean hasItem(String itemName, String brandName) {
        boolean hasItem = false;
        for (Item i : items) {
            if (i.getItemName().equals(itemName) && i.getBrandName().equals(brandName)) {
                hasItem = true;
            }
        }
        return hasItem;
    }

    public Item getItemByNameAndBrand(String itemName, String brandName) {
        Item foundItem = null;
        for (Item i : items) {
            if (i.getItemName().equals(itemName) && i.getBrandName().equals(brandName)) {
                foundItem = i;
            }
        }
        return foundItem;
    }

    public double getDistance(double currentX, double currentY) {
        return Math.pow(Math.pow(longitude - currentX,2) + Math.pow(latitude - currentY, 2),0.5);
    };
    
}