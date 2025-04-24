import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = Integer.parseInt(sc.nextLine().trim());
        DeviceManager dm = new DeviceManager();
        RuleManager rm = new RuleManager(dm);

        for (int i = 0; i < q; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");
            String cmd = parts[0];

            switch (cmd) {
                case "add_device":
                    System.out.println(dm.addDevice(parts[1], parts[2], parts[3]));
                    break;
                case "set_device":
                    System.out.println(dm.setDevice(parts[1], parts[2], parts[3]));
                    break;
                case "remove_device":
                    String res = dm.removeDevice(parts[1]);
                    if (res.equals("device removed successfully")) {
                        rm.removeRulesByDevice(parts[1]);
                    }
                    System.out.println(res);
                    break;
                case "list_devices":
                    List<Device> devs = dm.listDevices();
                    if (devs.isEmpty()) {
                        System.out.println();
                    } else {
                        for (Device d : devs) {
                            System.out.println(d.getInfo());
                        }
                    }
                    break;
                case "add_rule":
                    System.out.println(rm.addRule(parts[1], parts[2], parts[3]));
                    break;
                case "check_rules":
                    System.out.println(rm.checkRules(parts[1]));
                    break;
                case "list_rules":
                    List<Rule> rules = rm.listRules();
                    if (rules.isEmpty()) {
                        System.out.println();
                    } else {
                        for (Rule r : rules) {
                            System.out.println(r.deviceName + " " + r.time + " " + (r.action ? "on" : "off"));
                        }
                    }
                    break;
                default:
                    // ignore unknown
            }
        }
        sc.close();
    }
}