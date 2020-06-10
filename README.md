# UrbanLife+ BLE Device

Hiermit kann ein Raspberry Pi als BLE-Gerät verwendet werden, an dem sich ein Nutzer mit der [UrbanLife+-App](https://github.com/hhontheim/UrbanLifePlusApp) anmelden kann.
Das Projekt nutzt Java 8 und Gradle. Letzteres ist als Wrapper mitgeliefert.

## Installation

1. Klonen Sie das Repository rekursiv mit `git clone --recurse-submodules <URL>` direkt auf dem Raspberry Pi.
1. Installieren Sie die benötigten Bibliotheken mit `<sudo> apt install bluez libdbus-java`.
1. Führen Sie `<sudo> ldconfig` aus.
1. Aktivieren Sie in der Datei `/lib/systemd/system/bluetooth.service` des RPi mit `bluetoohd --experimental` die benötigten erweiterten Funktionen und starten Sie den RPi neu.
1. Starten Sie im Project-Root mit `sudo ./gradlew` den Build-Prozess, der anschließend die Anwendung startet.
