package com.cpan228.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpan228.distributionCentre.model.DistributionCentre;
import com.cpan228.distributionCentre.model.Item;
import com.cpan228.distributionCentre.repository.DistributionCentreRepository;
import com.cpan228.distributionCentre.repository.ItemRepository;


@RestController
@RequestMapping("/api/distribution")
public class DistributionCentreController {

    private final DistributionCentreRepository dcRepository;
    private final ItemRepository itemRepository;

    public DistributionCentreController(DistributionCentreRepository dcRepository, ItemRepository itemRepository) {
        this.dcRepository = dcRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public List<DistributionCentre> getAllCentres() {
        return (List<DistributionCentre>) dcRepository.findAll();
    }

    @GetMapping("/{id}/items")
    public List<Item> getItemsForCentre(@PathVariable int id) {
        var currentDistributionCentre = dcRepository.findById(id);
        var items = currentDistributionCentre.get().getItems();
        return items;
    }

    @PostMapping("/{id}/items")
    public Item addItemToCentre(@PathVariable int id, @RequestBody Item item) {
        var currentDistributionCentre = dcRepository.findById(id);
        item.setDistributionCentre(currentDistributionCentre.get());
        var savedItem = itemRepository.save(item);
        return savedItem;
    }

    @GetMapping("/request/{itemName}/{brandName}/{itemQuantity}")
    public String requestItems(@PathVariable String itemName, @PathVariable String brandName, @PathVariable int itemQuantity) {
        ArrayList<DistributionCentre> available = new ArrayList<>();
        var centres = (ArrayList<DistributionCentre>)dcRepository.findAll();
        for (DistributionCentre centre : centres) {
            if (centre.hasItem(itemName, brandName)) {
                available.add(centre);
            }
        }

        if (available.isEmpty()) {
            return "na";
        }
        
        double closestDistance  = Double.POSITIVE_INFINITY;
        DistributionCentre closest = available.get(0);
        String output = "";
        for (DistributionCentre aCentre : available) {
            double distance = aCentre.getDistance(43.7391619, -79.8531527);
            if (distance < closestDistance) {
                closestDistance = distance;
                closest = aCentre;
            }
        }
        Item requested = closest.getItemByNameAndBrand(itemName, brandName);
        if (requested.getQuantity() <= itemQuantity) {
            itemRepository.deleteById(requested.getId());
            output = "Item requested from Distribution Centre: " + closest.getName() + ". However, there are only " + requested.getQuantity() + " available.";
            output += "$"+ requested.getItemYear() +"|" + requested.getPrice() +"|" + requested.getQuantity();

        } else {
            Item itemRef = itemRepository.findById(requested.getId()).get();
            itemRef.setQuantity(itemRef.getQuantity() - itemQuantity);
            itemRepository.deleteById(requested.getId());
            itemRepository.save(itemRef);
            output =  "Item requested from Distribution Centre: " + closest.getName();
            output += "$"+ requested.getItemYear() +"|" + requested.getPrice() +"|" + itemQuantity;
        }
        return output;
    }
    

}
