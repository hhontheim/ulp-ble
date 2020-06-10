package net.hontheim.ulp.ble;

import it.tangodev.ble.*;
import net.hontheim.ulp.ble.example.ExampleCharacteristic;
import net.hontheim.ulp.ble.example.ExampleMain;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.ArrayList;
import java.util.List;

public class ULPDevice implements Runnable {
    protected String valueString = "Ciao ciao";
    BleApplication app;
    BleService service;
    BleCharacteristic characteristic;

    public void notifyBle(String value) {
        this.valueString = value;
        characteristic.sendNotification();
    }

    public ULPDevice() throws DBusException, InterruptedException {
        BleApplicationListener appListener = new BleApplicationListener() {
            @Override
            public void deviceDisconnected() {
                System.out.println("Device disconnected");
            }

            @Override
            public void deviceConnected() {
                System.out.println("Device connected");
            }
        };
        app = new BleApplication("/ulp", appListener);
        service = new BleService("/ulp/s", "F278E33F-D8F1-4F4B-8E04-885A5968FA11", true);
        List<BleCharacteristic.CharacteristicFlag> flags = new ArrayList<BleCharacteristic.CharacteristicFlag>();
//        flags.add(CharacteristicFlag.READ);
        flags.add(BleCharacteristic.CharacteristicFlag.WRITE);
//        flags.add(CharacteristicFlag.NOTIFY);

        characteristic = new BleCharacteristic("/ulp/s/c", service, flags, "4FB34DCC-27AB-4D22-AB77-9E3B03489CFC", new BleCharacteristicListener() {
            @Override
            public void setValue(byte[] value) {
                try {
                    valueString = new String(value, "UTF8");
                    System.out.println("New Value received for name: " + valueString);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public byte[] getValue() {
                try {
                    return valueString.getBytes("UTF8");
                } catch(Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        service.addCharacteristic(characteristic);
        app.addService(service);

        ExampleCharacteristic exampleCharacteristic = new ExampleCharacteristic(service);
        service.addCharacteristic(exampleCharacteristic);
        app.start();
    }

    @Override
    public void run() {
        try {
            this.wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BleApplication getApp() {
        return app;
    }

    public static void main(String[] args) throws DBusException, InterruptedException {
        ExampleMain example = new ExampleMain();
        Thread t = new Thread(example);
        t.start();
//		Thread.sleep(15000);
        // example.notifyBle("woooooo");
//		Thread.sleep(15000);
//		t.notify();

//		Thread.sleep(5000);
//		System.out.println("stopping application");
        // example.getApp().stop();
        System.out.println("Application stopped");
    }
}
