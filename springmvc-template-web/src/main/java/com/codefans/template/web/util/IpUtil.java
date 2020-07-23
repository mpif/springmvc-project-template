package com.codefans.template.web.util;

import com.codefans.template.common.util.StringUtils;
import com.codefans.template.web.client.WebContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class IpUtil {
	private static final Logger logger = LoggerFactory.getLogger(IpUtil.class);
	
	public static long getIpNum(String ipAddress){
        String[] ip = ipAddress.split("\\.");
        return Long.parseLong(ip[0]) * 256 * 256 * 256 + Long.parseLong(ip[1]) * 256 * 256 + Long.parseLong(ip[2]) * 256 + Long.parseLong(ip[3]); 
    }
     
    public static boolean isInner(long ip, long begin, long end) {
        return ip >= begin && ip <= end;

    }

	public static String getClientIpAddr() {
		String ip = null;
		HttpServletRequest request = WebContext.getInstance().getRequest();
		String ips = request.getHeader("x-forwarded-for");
		logger.info("ips, ips:" + ips);
		if (!StringUtils.isEmpty(ips)) {
			String[] forwardedFors = ips.split(",");
			ip = forwardedFors[0].trim();
		}
		logger.info("ip, ip:" + ip);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
			ip = request.getHeader("Proxy-Client-IP");
			logger.info("Proxy-Client-IP, ip:" + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			logger.info("WL-Proxy-Client-IP, ip:" + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
			ip = request.getRemoteAddr();
			logger.info("remoteAddr, ip:" + ip);
		}
		return ip;
	}
	
	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = null;
		String ips = request.getHeader("x-forwarded-for");
		logger.info("ips, ips:" + ips);
		if (!StringUtils.isEmpty(ips)) {
			String[] forwardedFors = ips.split(",");
			ip = forwardedFors[0].trim();
		}
		logger.info("ip, ip:" + ip);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
			ip = request.getHeader("Proxy-Client-IP");
			logger.info("Proxy-Client-IP, ip:" + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			logger.info("WL-Proxy-Client-IP, ip:" + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
			ip = request.getRemoteAddr();
			logger.info("remoteAddr, ip:" + ip);
		}
		return ip;
	}

	public static String getServerIpAddr() {
		try {
			// 遍历网卡，查找一个非回路ip地址并返回
			Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
			ArrayList<String> ipv4Result = new ArrayList<String>();
			ArrayList<String> ipv6Result = new ArrayList<String>();
			while (enumeration.hasMoreElements()) {
				final NetworkInterface networkInterface = enumeration.nextElement();
				final Enumeration<InetAddress> en = networkInterface.getInetAddresses();
				while (en.hasMoreElements()) {
					final InetAddress address = en.nextElement();
					if (!address.isLoopbackAddress()) {
						if (address instanceof Inet6Address) {
							ipv6Result.add(normalizeHostAddress(address));
						}
						else {
							ipv4Result.add(normalizeHostAddress(address));
						}
					}
				}
			}

			// 优先使用ipv4
			if (!ipv4Result.isEmpty()) {
				for (String ip : ipv4Result) {
					if (ip.startsWith("127.0") || ip.startsWith("192.168")) {
						continue;
					}

					return ip;
				}

				// 取最后一个
				return ipv4Result.get(ipv4Result.size() - 1);
			}
			// 然后使用ipv6
			else if (!ipv6Result.isEmpty()) {
				return ipv6Result.get(0);
			}
			// 然后使用本地ip
			final InetAddress localHost = InetAddress.getLocalHost();
			return normalizeHostAddress(localHost);
		}
		catch (SocketException e) {
			e.printStackTrace();
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String normalizeHostAddress(final InetAddress localHost) {
		if (localHost instanceof Inet6Address) {
			return "[" + localHost.getHostAddress() + "]";
		}
		else {
			return localHost.getHostAddress();
		}
	}
}