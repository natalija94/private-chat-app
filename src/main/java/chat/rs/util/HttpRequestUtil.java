package chat.rs.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author natalija
 */
public class HttpRequestUtil {
    private HttpRequestUtil() {
    }

    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public static String getClientIpAddressFromRequest(HttpServletRequest request) {
        if (request != null) {
            for (String header : IP_HEADER_CANDIDATES) {
                String ipList = request.getHeader(header);
                if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
                    String ip = ipList.split(",")[0];
                    return ip;
                }
            }
            return request.getRemoteAddr();
        }
        return null;
    }
}