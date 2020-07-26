package n.baldyoung.UtilityClass;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 通用的实用类，提供有通用的静态方法
 * @author baldyoung
 */
public class UtilityClassModule {

    /**
     * 检查给定的对象集中是否有引用为null的对象
     * @param args
     * @return true 给定的对象集中含有null对象 false 给定的对象集中不含有null对象
     */
    public static boolean isAnyEmpty(Object... args){
        boolean result = false;
        for(Object temp : args){
            if(null == temp){
                result = true;
                break;
            }
        }
        return result;
    }


    public static String getLocalIpv4Address() throws SocketException {
        Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
        String siteLocalAddress = null;
        while (ifaces.hasMoreElements()) {
            NetworkInterface iface = ifaces.nextElement();
            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                String hostAddress = addr.getHostAddress();
                if (addr instanceof Inet4Address
                        && !addr.isLoopbackAddress()
                        && !hostAddress.startsWith("192.168")
                        && !hostAddress.startsWith("172.")
                        && !hostAddress.startsWith("169.")) {
                    if (addr.isSiteLocalAddress()) {
                        siteLocalAddress = hostAddress;
                    } else {
                        return hostAddress;
                    }
                }
            }
        }
        return siteLocalAddress == null ? "" : siteLocalAddress;
    }






}
