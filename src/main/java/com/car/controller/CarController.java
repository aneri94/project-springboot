package com.car.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.car.exception.CarNotFoundException;
import com.car.model.Car;
import com.car.repository.CarRepository;

@RestController
@RequestMapping("/")
public class CarController {

	@Autowired
	private CarRepository carRepository;

	@GetMapping("/cars")
	public List<Car> retrieveAllCars() {
		return carRepository.findAll();
	}

	@GetMapping("/cars/{id}")
	public Resource<Car> retrieveCarById(@PathVariable long id) {
		Optional<Car> car = carRepository.findById(id);
		if (!car.isPresent())
			throw new CarNotFoundException("id-" + id);
		Resource<Car> resource = new Resource<Car>(car.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllCars());
		resource.add(linkTo.withRel("all-cars"));
		return resource;
	}

	@GetMapping("/cars/search")
	public Optional<List<Car>> retrieveCarByParam(@RequestParam Map<String, String> queryMap) {
		Optional<List<Car>> car = null;
		Map.Entry<String, String> entry = queryMap.entrySet().stream().findFirst().get();
		String param = entry.getKey();
		String value = entry.getValue();
		switch (param) {
		case "name":
			car = carRepository.findByName(value);
			break;
		case "manufactureName":
			car = carRepository.findByManufactureName(value);
			break;
		case "model":
			car = carRepository.findByModel(value);
			break;
		case "manufacturingYear":
			car = carRepository.findByManufacturingYear(value);
			break;
		case "color":
			car = carRepository.findByColor(value);
			break;
		}
		if (car != null && !car.isPresent())
			throw new CarNotFoundException("not found - " + param);
		return car;
	}

	@DeleteMapping("/cars/{id}")
	public void deleteCar(@PathVariable long id) {
		carRepository.deleteById(id);
	}

	@PostMapping("/cars")
	public ResponseEntity<Object> createCar(@RequestBody Car car) {
		Car createdCar = carRepository.save(car);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCar.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/cars/{id}")
	public ResponseEntity<Object> updateCar(@RequestBody Car car, @PathVariable long id) {
		Optional<Car> carOptional = carRepository.findById(id);
		if (!carOptional.isPresent())
			return ResponseEntity.notFound().build();
		car.setId(id);
		carRepository.save(car);
		return ResponseEntity.noContent().build();
	}
}
