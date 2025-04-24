public class Light extends Device {
    private int brightness = 50;

    public Light(String name, Protocol protocol) {
        super(name, protocol);
    }

    @Override
    public String getInfo() {
        return String.format(
                "%s %s %d%% %s",
                name,
                status ? "on" : "off",
                brightness,
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
            case "brightness":
                try {
                    int b = Integer.parseInt(value);
                    if (b < 0 || b > 100) return false;
                    brightness = b;
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            default:
                return false;
        }
    }
}