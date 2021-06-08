package com.legion.kickstart.Controller;

import com.legion.kickstart.DatabaseEntities.Car;
import com.legion.kickstart.DatabaseService.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/entities")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/car")
    public String saveCar(@RequestBody Car car) throws ExecutionException, InterruptedException {
        return carService.saveCar(car);
    }

    @GetMapping("/car/{componentId}")
    public Car getComponent(@PathVariable String carId) throws ExecutionException, InterruptedException {
        return carService.getCar(carId);
    }

    @DeleteMapping("/car/{componentId}")
    public String deleteCar(@PathVariable String carId) throws ExecutionException, InterruptedException {
        return carService.deleteCar(carId);
    }
}
