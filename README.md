# Smart Home System

This project implements a simple smart home system featuring:

- **Smart Devices**: Smart Light and Smart Thermostat.
- **Communication Protocols**: WiFi and Bluetooth.
- **Time-based Automation Rules**: Schedule on/off actions for devices at specific times.
- **Central Control Panel**: The `Main` class reads commands from standard input and outputs results.

## Project Structure
```
src/
├── Protocol.java       # Defines supported protocols
├── Device.java         # Abstract base class for devices
├── Light.java          # Smart Light implementation (extends Device)
├── Thermostat.java     # Smart Thermostat implementation (extends Device)
├── DeviceManager.java  # CRUD operations for devices
├── Rule.java           # Model for a time-based rule
├── RuleManager.java    # Adding, executing, and listing rules
└── Main.java           # Entry point and command processor
```

## Build and Run
1. Place all source files under the `src/` directory.
2. Compile using JDK:
   ```bash
   javac src/*.java
   ```
3. Run the application:
   ```bash
   java -cp src Main
   ```
4. Enter commands via the console as shown below.

## Supported Commands

### Device Management
- `add_device <type> <name> <protocol>`
  - `<type>`: `light` or `thermostat`
  - `<protocol>`: `WiFi` or `Bluetooth`

- `set_device <name> <property> <value>`
  - For `light`: `<property>` = `status`|`brightness`
  - For `thermostat`: `<property>` = `status`|`temperature`
  - `status`: `on` or `off`
  - `brightness`: integer 0–100
  - `temperature`: integer 10–30

- `remove_device <name>`
- `list_devices`

### Automation Rules
- `add_rule <device_name> <HH:MM> <action>`
  - `<action>`: `on` or `off`

- `check_rules <HH:MM>`
- `list_rules`

## Example Session
```
8
add_device light lamp1 WiFi
add_device thermostat thermo1 Bluetooth
set_device lamp1 brightness 75
add_rule lamp1 20:00 on
list_devices
check_rules 20:00
list_devices
list_rules
```

## Object-Oriented Design
- **Abstraction**: `Device` defines common properties and behaviors.
- **Inheritance**: `Light` and `Thermostat` extend `Device`.
- **Polymorphism**: `getInfo` and `setProperty` methods are implemented differently in each subclass.
- **Extensibility**: To add a new device type, simply create a subclass of `Device` and implement its logic.

## Implementation Details
- Time validation uses a custom `isValidTime` method that splits the string and checks numeric ranges.
- Device insertion order is preserved using `LinkedHashMap`.
- Removing a device automatically deletes its related automation rules.

## Author
This project was written by Sajjad Sarokhani.

---

© 2025 Smart Home System Project

