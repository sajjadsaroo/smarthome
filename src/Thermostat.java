public class Thermostat extends Device {
    private int temperature = 20;

    public Thermostat(String name, Protocol protocol) {
        super(name, protocol);
    }

    @Override
    public String getInfo() {
        return String.format(
                "%s %s %dC %s",
                name,
                status ? "on" : "off",
                temperature,
                protocol
        );
    }

    @Override
    public boolean setProperty(String prop, String value) {
        switch (prop) {
            case "status":
                if (!value.equals("on") && !value.equals("off")) return false;
                setStatus(value.equals("on"));
                return true;
            case "temperature":
                try {
                    int t = Integer.parseInt(value);
                    if (t < 10 || t > 30) return false;
                    temperature = t;
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            default:
                return false;
        }
    }
}
