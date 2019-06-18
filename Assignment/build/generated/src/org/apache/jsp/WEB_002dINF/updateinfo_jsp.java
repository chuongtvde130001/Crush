package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class updateinfo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta charset=\"UTF-8\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n");
      out.write("        <title>Login Account</title>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/font-awesome.min.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/bootstrap.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body id=\"home\">\r\n");
      out.write("        <nav class=\"navbar navbar-expand-sm bg-dark navbar-dark fixed-top\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <form action=\"ProcessLogout\" method=\"get\">\r\n");
      out.write("                    \r\n");
      out.write("                        <button class=\"btn btn-danger\">Logout</button>\r\n");
      out.write("                    \r\n");
      out.write("                    </button>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("        </nav>\r\n");
      out.write("        <!-------- Login page ------------->\r\n");
      out.write("        <header id=\"home-section-update\">\r\n");
      out.write("            <div class=\"home-inner\">\r\n");
      out.write("                <div class=\"container\">\r\n");
      out.write("                    <div class=\"row-1\">\r\n");
      out.write("                        <p class=\"display-1 text-center\">Hello ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.user.userName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("!</p>\r\n");
      out.write("                        <p class=\"display-4 text-center\">Complete Your Profile</p>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"row-5\">\r\n");
      out.write("                        <div class=\"card bg-dark text-center card-form\">\r\n");
      out.write("                            <div class=\"card-body text-left\">\r\n");
      out.write("                                <h3>Your Info</h3>\r\n");
      out.write("                                <p>Please fill out this form to update your information</p>\r\n");
      out.write("                                <form>\r\n");
      out.write("                                    <div class=\"form-row\">\r\n");
      out.write("                                        <div class=\"col\">\r\n");
      out.write("                                            <h4>My Full Name :</h4>\r\n");
      out.write("                                            <input type=\"text\" class=\"form-control\" placeholder=\"Your Full Name\">\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                        <div class=\"col\">\r\n");
      out.write("                                            <h4>Age:</h4>\r\n");
      out.write("                                            <input type=\"text\" class=\"form-control\" placeholder=\"How old are you?\">\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div><br>\r\n");
      out.write("                                    <div class=\"form-row\">\r\n");
      out.write("                                        <div class=\"col\">\r\n");
      out.write("                                            <h4>I want to meet :</h4>\r\n");
      out.write("                                            <select id=\"gender\" class=\"form-control\">\r\n");
      out.write("                                                <option>Male</option>\r\n");
      out.write("                                                <option>Female</option>\r\n");
      out.write("                                            </select>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                        <div class=\"col\">\r\n");
      out.write("                                            <h4>Gender :</h4>\r\n");
      out.write("                                            <select id=\"gender\" class=\"form-control\">\r\n");
      out.write("                                                <option>Male</option>\r\n");
      out.write("                                                <option>Female</option>\r\n");
      out.write("                                            </select>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div><br>\r\n");
      out.write("                                    <div class=\"form-row\">\r\n");
      out.write("                                        <div class=\"col\">\r\n");
      out.write("                                            <h4>Age Range :</h4>\r\n");
      out.write("                                            <div class=\"form-row text-center\">\r\n");
      out.write("                                                <div class=\"col\">\r\n");
      out.write("                                                    <h3>From</h3>\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"col\">\r\n");
      out.write("                                                    <input type=\"text\" class=\"form-control\" placeholder=\"Age\">\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"col\">\r\n");
      out.write("                                                    <h3>To</h3>\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"col\">\r\n");
      out.write("                                                    <input type=\"text\" class=\"form-control\" placeholder=\"Age\">\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                        </div> \r\n");
      out.write("                                        <div class=\"col\">\r\n");
      out.write("                                            <h3>Select Your Avatar</h3>\r\n");
      out.write("                                            <div class=\"col\"><label for=\"file\">File input</label>\r\n");
      out.write("                                                <form method=\"post\" action=\"UploadFileServlet\" enctype=\"multipart/form-data\">\r\n");
      out.write("                                                    <input type=\"file\" id=\"file\" class=\"form-control-file\">\r\n");
      out.write("                                                    <small id=\"fileHelp\" class=\"form-text text-muted\">Max 3mb size</small></div>\r\n");
      out.write("                                            </form>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div><br>\r\n");
      out.write("                                    <br>\r\n");
      out.write("                                    <button type=\"submit\" class=\"btn btn-primary btn-block\">Take me to the home page !</button>\r\n");
      out.write("                                </form>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</header>\r\n");
      out.write("<script src=\"js/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"js/popper.min.js\"></script>\r\n");
      out.write("<script src=\"js/bootstrap.min.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
