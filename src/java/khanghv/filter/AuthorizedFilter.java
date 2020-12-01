/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Vector;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class AuthorizedFilter implements Filter {
    
    private static final boolean debug = true;
    private final Vector<String> guestList;
    private final Vector<String> memberList;
    private final Vector<String> adminList;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AuthorizedFilter() {
        guestList = new Vector<>();
        guestList.add("index.jsp");
        guestList.add("error.jsp");
        guestList.add("LoadingCake");
        guestList.add("errorupdate.jsp");
        guestList.add("");
        guestList.add("Login");
        guestList.add("FilterDispatcher");
        guestList.add("detail.jsp");
        guestList.add("catalog.jsp");
        guestList.add("viewcart.jsp");
        guestList.add("Checkout");
        guestList.add("AddToCart");
        guestList.add("DeleteProductInCart");
        guestList.add("UpdateProductInCart");
        guestList.add("SearchCake");
        guestList.add("LoadDetail");
        

        memberList = new Vector<>();
        memberList.add("index.jsp");
        memberList.add("error.jsp");
        memberList.add("LoadingCake");
        memberList.add("errorupdate.jsp");
        memberList.add("");
        memberList.add("Login");
        memberList.add("FilterDispatcher");
        memberList.add("detail.jsp");
        memberList.add("catalog.jsp");
        memberList.add("viewcart.jsp");
        memberList.add("Checkout");
        memberList.add("AddToCart");
        memberList.add("DeleteProductInCart");
        memberList.add("UpdateProductInCart");
        memberList.add("SearchCake");
        memberList.add("LoadDetail");
        memberList.add("TrackOrder");
        memberList.add("trackorder.jsp");
        memberList.add("Logout");

        adminList = new Vector<>();
        adminList.add("index.jsp");
        adminList.add("detail.jsp");
        adminList.add("error.jsp");
        adminList.add("errorupdate.jsp");
        adminList.add("catalog.jsp");
        adminList.add("createcake.jsp");
        adminList.add("edit.jsp");
        adminList.add("CreateCake");
        adminList.add("LoadDetail");
        adminList.add("LoadingCake");
        adminList.add("Login");
        adminList.add("Logout");
        adminList.add("SearchCake");
        adminList.add("Update");
        adminList.add("TrackOrder");
        adminList.add("trackorder.jsp");
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizedFilter:DoBeforeProcessing");
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
            log("AuthorizedFilter:DoAfterProcessing");
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
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        int index = uri.lastIndexOf("/");
        String resource = uri.substring(index + 1).trim();
        
        
        HttpSession session = req.getSession(false);
        
        
        if (session == null || session.getAttribute("ROLE") == null) {
            if (guestList.contains(resource)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("LoadingCake");
            }
        } else {
            String role = (String) session.getAttribute("ROLE");
            if (role != null) {
                if (role.equals("Member")) {
                    if (memberList.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect("LoadingCake");
                    }
                } else if (role.equals("Admin")) {
                    if (adminList.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect("LoadingCake");
                    }
                }

            } else {
                res.sendRedirect("LoadingCake");
            }

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
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthorizedFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthorizedFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthorizedFilter(");
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
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
