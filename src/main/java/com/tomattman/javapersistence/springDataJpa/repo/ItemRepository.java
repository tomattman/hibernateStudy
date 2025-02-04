package com.tomattman.javapersistence.springDataJpa.repo;

import com.tomattman.javapersistence.springDataJpa.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Iterable<Item> findByMetricWeight(double weight);
}
