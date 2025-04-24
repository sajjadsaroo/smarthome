import java.util.*;

public class DeviceManager {
    private Map<String, Device> devices = new LinkedHashMap<>();

    public String addDevice(String type, String name, String proto) {
        if (devices.containsKey(name)) return "duplicate device name";
        Protocol p;
        try {
            p = Protocol.valueOf(proto);
        } catch (Exception e) {
            return "invalid input";
        }
        Device d;
        if (type.equals("light")) d = new Light(name, p);
        else if (type.equals("thermostat")) d = new Thermostat(name, p);
        else return "invalid input";
        devices.put(name, d);
        return "device added successfully";
    }

    public String setDevice(String name, String prop, String val) {
        Device d = devices.get(name);
        if (d == null) return "device not found";
        boolean ok = d.setProperty(prop, val);
        if (!ok) {
            if (prop.equals("status") || prop.equals("brightness") || prop.equals("temperature"))
                return "invalid value";
            else
                return "invalid property";
        }
        return "device updated successfully";
    }

    public String removeDevice(String name) {
        if (!devices.containsKey(name)) return "device not found";
        devices.remove(name);
        return "device removed successfully";
    }

    public List<Device> listDevices() {
        return new ArrayList<>(devices.values());
    }

    public boolean exists(String name) {
        return devices.containsKey(name);
    }
}
