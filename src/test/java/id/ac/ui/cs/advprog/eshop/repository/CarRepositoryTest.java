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

        Car car = new Car();
        car.setCarId("exists");
        carRepository.create(car);
        Car foundCar2 = carRepository.findById("different-id");
        assertNull(foundCar2);
    }

    @Test
    void testUpdateFound() {
        Car car = new Car();
        car.setCarId("3");
        car.setCarName("Honda");
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Honda Jazz");
        updatedCar.setCarColor("Kuning");
        updatedCar.setCarQuantity(15);

        Car result = carRepository.update("3", updatedCar);
        assertNotNull(result);
        assertEquals("Honda Jazz", result.getCarName());
    }

    @Test
    void testUpdateNotFound() {
        Car car = new Car();
        car.setCarId("some-id");
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("New Name");
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

    @Test
    void testDeleteNotFound() {
        Car car = new Car();
        car.setCarId("id-tetap-ada");
        carRepository.create(car);

        carRepository.delete("id-tidak-ada");

        assertNotNull(carRepository.findById("id-tetap-ada"));
    }
}