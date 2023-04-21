package com.cpan228.distributionCentre;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.cpan228.distributionCentre.model.DistributionCentre;
import com.cpan228.distributionCentre.model.Item;
import com.cpan228.distributionCentre.repository.DistributionCentreRepository;
import com.cpan228.distributionCentre.repository.ItemRepository;

@SpringBootApplication
@ComponentScan("com.cpan228.controller")
public class DistributionCentreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributionCentreApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ItemRepository itemRepository, DistributionCentreRepository dcRepository) {
		return args -> {
			var dc1 = dcRepository
					.save(DistributionCentre.builder().name("Kipling and Eglinton").latitude(43.677104).longitude(-79.550774).build());
			var dc2 = dcRepository
					.save(DistributionCentre.builder().name("Islington and Steeles").latitude(43.764506).longitude(-79.574785).build());
			var dc3 = dcRepository
					.save(DistributionCentre.builder().name("Vaughan Warehouse").latitude(43.862191).longitude(-79.508496).build());
			var dc4 = dcRepository
					.save(DistributionCentre.builder().name("Brampton Warehouse").latitude(43.734851).longitude(-79.754744).build());
			itemRepository
					.save(Item.builder().itemName("Adidas Joggers")
							.brandName("Adidas")
							.itemYear(2022)
							.price(2200).quantity(10)
							.distributionCentre(dc1).build());
			itemRepository
					.save(Item.builder().itemName("Balenciaga Shoes")
							.brandName("Balenciaga")
							.itemYear(2022)
							.price(1000.54).quantity(10)
							.distributionCentre(dc1).build());
			itemRepository
					.save(Item.builder().itemName("Nike Shirt")
							.brandName("Nike")
							.itemYear(2032)
							.price(2500).quantity(10)
							.distributionCentre(dc1).build());
			itemRepository
					.save(Item.builder().itemName("Adidas Shorts")
							.brandName("Adidas")
							.itemYear(2022)
							.price(2100.50).quantity(10)
							.distributionCentre(dc2).build());
			itemRepository
					.save(Item.builder().itemName("Balenciaga Jacket")
							.brandName("Balenciaga")
							.itemYear(2022)
							.price(1000.54).quantity(10)
							.distributionCentre(dc2).build());
			itemRepository
					.save(Item.builder().itemName("Nike Pants")
							.brandName("Nike")
							.itemYear(2032)
							.price(2000).quantity(10)
							.distributionCentre(dc2).build());
			itemRepository
					.save(Item.builder().itemName("Adidas Joggers")
							.brandName("Adidas")
							.itemYear(2022)
							.price(2200).quantity(10)
							.distributionCentre(dc3).build());
			itemRepository
					.save(Item.builder().itemName("Balenciaga Shoes")
							.brandName("Balenciaga")
							.itemYear(2022)
							.price(1000.54).quantity(10)
							.distributionCentre(dc3).build());
			itemRepository
					.save(Item.builder().itemName("Nike Shirt")
							.brandName("Nike")
							.itemYear(2032)
							.price(2500).quantity(10)
							.distributionCentre(dc3).build());
			itemRepository
					.save(Item.builder().itemName("Adidas Shorts")
							.brandName("Adidas")
							.itemYear(2022)
							.price(2100.50).quantity(10)
							.distributionCentre(dc4).build());
			itemRepository
					.save(Item.builder().itemName("Balenciaga Jacket")
							.brandName("Balenciaga")
							.itemYear(2022)
							.price(1000.54).quantity(10)
							.distributionCentre(dc4).build());
			itemRepository
					.save(Item.builder().itemName("Nike Pants")
							.brandName("Nike")
							.itemYear(2032)
							.price(2000).quantity(10)
							.distributionCentre(dc4).build());
		};
	}

}
