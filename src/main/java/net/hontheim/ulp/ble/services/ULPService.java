package net.hontheim.ulp.ble.services;

import it.tangodev.ble.BleService;

public class ULPService extends BleService {

    public ULPService() {
        super("/ulp/s/ulp", "F278E33F-D8F1-4F4B-8E04-885A5968FA11", true);
    }
}
