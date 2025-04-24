public abstract class Device {
    protected String name;
    protected Protocol protocol;
    protected boolean status = false; // default id off

    public Device(String name, Protocol protocol) {
        this.name = name;
        this.protocol = protocol;
    }

    public String getName() { return name; }
    public Protocol getProtocol() { return protocol; }
    public boolean isOn() { return status; }
    public void setStatus(boolean status) { this.status = status; }


    /** Returns: name status [brightness%|temperatureC] protocol */
    public abstract String getInfo();

    /**
     * Sets a property. Returns true if successful,
     * false if property or value is invalid.
     */
    public abstract boolean setProperty(String prop, String value);
}