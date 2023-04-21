package com.cpan228.distributionCentre.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.cpan228.distributionCentre.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByItemNameAndBrandName(String itemName, String brandName);
}
