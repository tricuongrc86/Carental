/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author TriCuong
 */
public class FilterDispatcher implements Filter {

    private static final Logger LOGGER = Logger.getLogger(FilterDispatcher.class);
    private static final boolean debug = true;
    private HashMap<String, List< String>> listKeySrc = new HashMap<>();
    private HashMap<String, String> hashFile = new HashMap<>();
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterDispatcher() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    private final String ERR_PAGE = "error.jsp";
    private final String LOGIN_PAGE = "login1.jsp";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String url = "SetTimeServlet";
        try {
            HttpSession session = req.getSession();
            ServletContext context = req.getServletContext();
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);
            String role = (String) session.getAttribute("ROLE");
            if (resource.equals("")) {

            } else if (resource.equals("loginPage")) {
                if (role == null) {
                    url = context.getAttribute("loginPage").toString();
                }
            } else //   if (resource.length() > 0) {
            if (resource.lastIndexOf(".css") > 0 || resource.lastIndexOf(".js") > 0 || resource.lastIndexOf(".jpg") > 0 || resource.lastIndexOf(".png") > 0 || resource.lastIndexOf(".svg") > 0) {
                url = resource;
            } else {
                if (hashFile.containsKey(resource)) {
                    url = hashFile.get(resource);
                } else {
                    url = resource;
                }
                //  String role = (String) session.getAttribute("ROLE");   
                if (!listKeySrc.get("Common").contains(url)) {
                    if (role == null) {
                        request.setAttribute("NOTIFICATION", "Must be login");
                        url = LOGIN_PAGE;
                    } else {
                        if (!listKeySrc.get(role).contains(url)) {
                            request.setAttribute("NOTIFICATION", "Not accept for you!Please login again for continute service!");
                            url = ERR_PAGE;
                        }
                    }
                }
            }

            if (url != null) {
                RequestDispatcher rd = req.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (Throwable t) {
//            // If an exception is thrown somewhere down the filter chain,
//            // we still want to execute our after processing, and then
//            // rethrow the problem after that.
//             problem = t;
            LOGGER.error(t);
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        String urlTextFile = "/WEB-INF/role.txt";
        ServletContext context = filterConfig.getServletContext();
        String path = context.getRealPath(urlTextFile);
        try {
            List<String> listSrc = new ArrayList<>();
            String keyRole = null;
            FileReader f = null;
            BufferedReader br = null;

            f = new FileReader(path);
            br = new BufferedReader(f);
            String line;
            while ((line = br.readLine()) != null) {
                String part[] = line.split("=");
                if (part.length == 1) {
                    if (listSrc.size() != 0) {
                        listKeySrc.put(keyRole, listSrc);
                    }
                    keyRole = part[0];
                    listSrc = new ArrayList<>();
                }
                if (part.length == 2) {
                    String key = part[0];
                    String value = part[1];
                    hashFile.put(key, value);
                    context.setAttribute(key, value);
                    listSrc.add(value);
                }
            }
            listKeySrc.put(keyRole, listSrc);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterDispatcher()");
        }
        StringBuffer sb = new StringBuffer("FilterDispatcher(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
