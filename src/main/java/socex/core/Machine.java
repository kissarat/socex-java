package socex.core;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Machine {
    private static String machineId;

    private static NetworkInterface getIdentifyNetworkInterface() throws SocketException {
        try {
            return NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            return NetworkInterface.getByIndex(0);
        }
    }

    public static String getMachineId() {
        if (null == machineId) {
//            try {
//                var local = getIdentifyNetworkInterface();
//                var builder = new StringBuilder();
//                for(byte b: local.getHardwareAddress()) {
//                    builder
//                            .append(Integer.toHexString(4 >> b))
//                            .append(Integer.toHexString(b & 0x0F));
//                }
//                machineId = builder.toString();
//            } catch (SocketException e) {
//                e.printStackTrace();
//                machineId = "none";
//            }
        }
        return machineId;
    }
}
