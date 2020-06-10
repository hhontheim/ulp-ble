# UrbanLife+ BLE Device

Hiermit kann ein Raspberry Pi als BLE-Gerät verwendet werden, an dem sich ein Nutzer mit der [UrbanLife+-App](https://github.com/hhontheim/UrbanLifePlusApp) anmelden kann.

## Installation

1. Klonen Sie das Repository rekursiv mit `git clone --recurse-submodules <URL>` direkt auf dem Raspberry Pi.
1. Aktivieren Sie in der Datei `/lib/systemd/system/bluetooth.service` des RPi mit `bluetoohd --experimental` die benötigten erweiterten Funktionen.
1. Starten Sie im Project-Root mit `sudo ./gradlew` den Build-Prozess, der anschließend die Anwendung startet.
