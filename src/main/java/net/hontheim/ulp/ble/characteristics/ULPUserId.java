package net.hontheim.ulp.ble.characteristics;

import it.tangodev.ble.BleCharacteristic;
import it.tangodev.ble.BleCharacteristicListener;
import it.tangodev.ble.BleService;
import org.bluez.GattCharacteristic1;

import java.util.ArrayList;
import java.util.List;

public class ULPUserId extends BleCharacteristic implements GattCharacteristic1 {
    public ULPUserId(BleService service) {
        super(service);
        List<CharacteristicFlag> flags = new ArrayList<CharacteristicFlag>();
        flags.add(CharacteristicFlag.WRITE);
        setFlags(flags);

        this.path = "/ulp/c/id";
        this.uuid = "2D58B503-FE42-4010-BA8D-3F6A7632FCD5";

        this.listener = new BleCharacteristicListener() {
            @Override
            public void setValue(byte[] value) {
                System.out.println("New value for ID: " + value);
            }

            @Override
            public byte[] getValue() {
                throw new RuntimeException("Reading not wanted!");
            }
        };
    }
}
