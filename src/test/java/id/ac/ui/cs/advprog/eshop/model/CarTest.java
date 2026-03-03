package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    Car car;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setCarId("car-123");
        this.car.setCarName("Kijang Innova");
        this.car.setCarColor("Hitam");
        this.car.setCarQuantity(5);
    }

    @Test
    void testGetCarId() {
        assertEquals("car-123", this.car.getCarId());
    }

    @Test
    void testGetCarName() {
        assertEquals("Kijang Innova", this.car.getCarName());
    }

    @Test
    void testGetCarColor() {
        assertEquals("Hitam", this.car.getCarColor());
    }

    @Test
    void testGetCarQuantity() {
        assertEquals(5, this.car.getCarQuantity());
    }
}