package com.example.demoprj.pattern.factory2;

public class FactoryTest {

    public static void main(String[] args) {
        MobileFactory factory= new MobileFactory();

        Lenovo len = (Lenovo)factory.createMobile("len");
        len.pictureCapacity();
        len.batteryPower();

        Samsung sam= (Samsung)factory.createMobile("sam");
        sam.cost();
    }
}
