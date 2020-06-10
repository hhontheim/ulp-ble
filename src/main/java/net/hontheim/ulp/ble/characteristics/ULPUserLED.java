package net.hontheim.ulp.ble.characteristics;

import it.tangodev.ble.BleCharacteristic;
import it.tangodev.ble.BleCharacteristicListener;
import it.tangodev.ble.BleService;
import org.bluez.GattCharacteristic1;

import java.util.ArrayList;
import java.util.List;

public class ULPUserLED extends BleCharacteristic implements GattCharacteristic1 {
    public ULPUserLED(BleService service) {
        super(service);
        List<CharacteristicFlag> flags = new ArrayList<CharacteristicFlag>();
        flags.add(CharacteristicFlag.WRITE);
        setFlags(flags);

        this.path = "/ulp/c/led";
        this.uuid = "6067DAB3-D8C0-4D82-A486-C7499176B57A";

        this.listener = new BleCharacteristicListener() {
            @Override
            public void setValue(byte[] value) {
                System.out.println("New value for LED: " + value);
            }

            @Override
            public byte[] getValue() {
                throw new RuntimeException("Reading not wanted!");
            }
        };
    }
}
