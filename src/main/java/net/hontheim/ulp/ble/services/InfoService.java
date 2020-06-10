package net.hontheim.ulp.ble.services;

import it.tangodev.ble.BleService;

public class InfoService extends BleService {

    public InfoService() {
        super("/ulp/s/info", "180a", true);
    }
}
