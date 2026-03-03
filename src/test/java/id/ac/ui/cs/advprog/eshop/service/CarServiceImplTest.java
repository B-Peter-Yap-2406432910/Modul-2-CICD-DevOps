package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepositoryIntr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepositoryIntr carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("test-id");
        car.setCarName("Avanza");
        car.setCarColor("Putih");
        car.setCarQuantity(10);
    }

    @Test
    void testCreateWithId() {
        when(carRepository.create(car)).thenReturn(car);
        Car savedCar = carService.create(car);
        assertEquals("test-id", savedCar.getCarId());
        verify(carRepository, times(1)).create(car);
    }

    @Test
    void testCreateWithoutId() {
        Car newCar = new Car();
        newCar.setCarName("Xenia");
        when(carRepository.create(newCar)).thenReturn(newCar);

        Car savedCar = carService.create(newCar);
        assertNotNull(savedCar.getCarId());
        verify(carRepository, times(1)).create(newCar);
    }

    @Test
    void testFindAll() {
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        Iterator<Car> iterator = carList.iterator();
        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> allCars = carService.findAll();
        assertFalse(allCars.isEmpty());
        assertEquals(1, allCars.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(carRepository.findById("test-id")).thenReturn(car);
        Car foundCar = carService.findById("test-id");
        assertEquals("test-id", foundCar.getCarId());
        verify(carRepository, times(1)).findById("test-id");
    }

    @Test
    void testUpdate() {
        carService.update("test-id", car);
        verify(carRepository, times(1)).update("test-id", car);
    }

    @Test
    void testDeleteCarById() {
        carService.deleteCarById("test-id");
        verify(carRepository, times(1)).delete("test-id");
    }
}