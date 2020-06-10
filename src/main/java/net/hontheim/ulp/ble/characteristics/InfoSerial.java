package net.hontheim.ulp.ble.characteristics;

import it.tangodev.ble.BleCharacteristic;
import it.tangodev.ble.BleCharacteristicListener;
import it.tangodev.ble.BleService;
import org.bluez.GattCharacteristic1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InfoSerial extends BleCharacteristic implements GattCharacteristic1 {
    public InfoSerial(BleService service) {
        super(service);
        List<CharacteristicFlag> flags = new ArrayList<CharacteristicFlag>();
        flags.add(CharacteristicFlag.READ);
        setFlags(flags);

        this.path = "/ulp/c/serial";
        this.uuid = "2a25";

        this.listener = new BleCharacteristicListener() {
            @Override
            public void setValue(byte[] value) {
                throw new RuntimeException("Writing not wanted!");
            }

            @Override
            public byte[] getValue() {
                try {
                    return new UUID(0, 0).toString().getBytes("UTF8");
                } catch(Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
