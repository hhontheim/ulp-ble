package net.hontheim.ulp.ble.characteristics;

import it.tangodev.ble.BleCharacteristic;
import it.tangodev.ble.BleCharacteristicListener;
import it.tangodev.ble.BleService;
import org.bluez.GattCharacteristic1;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ULPUserName extends BleCharacteristic implements GattCharacteristic1 {
    public ULPUserName(BleService service) {
        super(service);
        List<CharacteristicFlag> flags = new ArrayList<CharacteristicFlag>();
        flags.add(CharacteristicFlag.WRITE);
        setFlags(flags);

        this.path = "/ulp/c/name";
        this.uuid = "4FB34DCC-27AB-4D22-AB77-9E3B03489CFC";

        this.listener = new BleCharacteristicListener() {
            @Override
            public void setValue(byte[] value) {
                String newValue = null;
                try {
                    newValue = new String(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if (newValue!= null) {
                    System.out.println("New value for Name: " + newValue);
                }
            }

            @Override
            public byte[] getValue() {
                throw new RuntimeException("Reading not wanted!");
            }
        };
    }
}
