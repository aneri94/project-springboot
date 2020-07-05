package com.car.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	Optional<List<Car>> findByName(String name);

	Optional<List<Car>> findByManufactureName(String manufactureName);

	Optional<List<Car>> findByModel(String model);

	Optional<List<Car>> findByManufacturingYear(String manufacturingYear);

	Optional<List<Car>> findByColor(String color);

}