package net.hontheim.ulp.ble;

import net.hontheim.ulp.ble.example.ExampleMain;
import org.freedesktop.dbus.exceptions.DBusException;

public class Main {
    public static void main(String[] args) throws InterruptedException, DBusException {
        System.out.println("Hello!");
        ExampleMain example = new ExampleMain();
        System.out.println("");
//		Thread t = new Thread(example);
//		t.start();
//		Thread.sleep(15000);
        example.notifyBle("woooooo");
//		Thread.sleep(15000);
//		t.notify();

//		Thread.sleep(5000);
//		System.out.println("stopping application");
        example.getApp().stop();
        System.out.println("Application stopped");
    }
}
