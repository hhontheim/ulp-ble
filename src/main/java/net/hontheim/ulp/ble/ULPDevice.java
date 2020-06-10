package net.hontheim.ulp.ble;

import it.tangodev.ble.*;
import net.hontheim.ulp.ble.characteristics.InfoSerial;
import net.hontheim.ulp.ble.characteristics.ULPUserId;
import net.hontheim.ulp.ble.characteristics.ULPUserLED;
import net.hontheim.ulp.ble.characteristics.ULPUserName;
import net.hontheim.ulp.ble.services.InfoService;
import net.hontheim.ulp.ble.services.ULPService;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.ArrayList;
import java.util.List;

public class ULPDevice implements Runnable {
    BleApplication app;

    ULPService ulpService;
    InfoService infoService;

    ULPUserId ulpUserId;
    ULPUserLED ulpUserLED;
    ULPUserName ulpUserName;
    InfoSerial infoSerial;

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

        ulpService = new ULPService();
        infoService = new InfoService();

        List<BleCharacteristic.CharacteristicFlag> flags = new ArrayList<>();
        flags.add(BleCharacteristic.CharacteristicFlag.WRITE);

        ulpUserId = new ULPUserId(ulpService);
        ulpUserLED = new ULPUserLED(ulpService);
        ulpUserName = new ULPUserName(ulpService);

        infoSerial = new InfoSerial(infoService);


        ulpService.addCharacteristic(ulpUserId);
        ulpService.addCharacteristic(ulpUserLED);
        ulpService.addCharacteristic(ulpUserName);

        infoService.addCharacteristic(infoSerial);

        app.addService(ulpService);
        app.addService(infoService);

        app.start();
    }

    @Override
    public void run() {
    }

    public BleApplication getApp() {
        return app;
    }

    public static void main(String[] args) throws DBusException, InterruptedException {
        ULPDevice ulpDevice = new ULPDevice();
        Thread t = new Thread(ulpDevice);
        t.start();
    }
}
