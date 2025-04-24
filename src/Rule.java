public class Rule {
    public final String deviceName;
    public final String time;      // format "HH:MM"
    public final boolean action;   // true=on, false=off

    public Rule(String deviceName, String time, boolean action) {
        this.deviceName = deviceName;
        this.time = time;
        this.action = action;
    }
}