package chat.rs.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author natalija
 */
public class HttpRequestUtil {
    /**
     * Util class. Private constructor.
     */
    private HttpRequestUtil() {
    }

    /**
     * Constant for IP header params.
     */
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

    /**
     * Util function. Return ipAddress from request.
     * Initially I thought it would be okay to save information about clients who sent inappropriate content,
     * so this ipAddress will be banned. Unfortunately, I haven't finished it.
     *
     * @param request as HttpServletRequest
     * @return formatted ipAddress.
     */
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