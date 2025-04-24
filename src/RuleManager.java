import java.util.*;

public class RuleManager {
    private final List<Rule> rules = new ArrayList<>();
    private final DeviceManager dm;

    public RuleManager(DeviceManager dm) {
        this.dm = dm;
    }

    private boolean isValidTime(String time) {
        String[] parts = time.split(":");
        if (parts.length != 2) return false;
        try {
            int hh = Integer.parseInt(parts[0]);
            int mm = Integer.parseInt(parts[1]);
            if (hh < 0 || hh > 23 || mm < 0 || mm > 59) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public String addRule(String name, String time, String act) {
        if (!dm.exists(name)) return "device not found";
        if (!isValidTime(time)) return "invalid time";
        if (!act.equals("on") && !act.equals("off")) return "invalid action";
        for (Rule r : rules) {
            if (r.deviceName.equals(name) && r.time.equals(time))
                return "duplicate rule";
        }
        rules.add(new Rule(name, time, act.equals("on")));
        return "rule added successfully";
    }

    public String checkRules(String time) {
        if (!isValidTime(time)) return "invalid time";
        for (Rule r : rules) {
            if (r.time.equals(time)) {
                dm.setDevice(r.deviceName, "status", r.action ? "on" : "off");
            }
        }
        return "rules checked";
    }

    public List<Rule> listRules() {
        return new ArrayList<>(rules);
    }

    public void removeRulesByDevice(String name) {
        rules.removeIf(r -> r.deviceName.equals(name));
    }
}
