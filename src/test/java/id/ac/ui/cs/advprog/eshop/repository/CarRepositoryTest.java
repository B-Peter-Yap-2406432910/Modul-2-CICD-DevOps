package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {

    @InjectMocks
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("1");
        car.setCarName("Toyota");
        car.setCarColor("Merah");
        car.setCarQuantity(10);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindByIdFound() {
        Car car = new Car();
        car.setCarId("2");
        carRepository.create(car);

        Car foundCar = carRepository.findById("2");
        assertNotNull(foundCar);
        assertEquals("2", foundCar.getCarId());
    }

    @Test
    void testFindByIdNotFound() {
        Car foundCar = carRepository.findById("non-existent");
        assertNull(foundCar);
    }

    @Test
    void testUpdateFound() {
        Car car = new Car();
        car.setCarId("3");
        car.setCarName("Honda");
        car.setCarColor("Biru");
        car.setCarQuantity(5);
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Honda Jazz");
        updatedCar.setCarColor("Kuning");
        updatedCar.setCarQuantity(15);

        Car result = carRepository.update("3", updatedCar);
        assertNotNull(result);
        assertEquals("Honda Jazz", result.getCarName());
        assertEquals("Kuning", result.getCarColor());
        assertEquals(15, result.getCarQuantity());
    }

    @Test
    void testUpdateNotFound() {
        Car updatedCar = new Car();
        updatedCar.setCarName("Honda Jazz");
        Car result = carRepository.update("not-found", updatedCar);
        assertNull(result);
    }

    @Test
    void testDelete() {
        Car car = new Car();
        car.setCarId("4");
        carRepository.create(car);

        carRepository.delete("4");
        Car foundCar = carRepository.findById("4");
        assertNull(foundCar);
    }
}