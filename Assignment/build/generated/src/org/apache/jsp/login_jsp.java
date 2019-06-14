package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("                <a href=\"home.html\" class=\"navbar-brand\">\r\n");
      out.write("                    <button class=\"btn btn-danger\">Back To Home Page</button>\r\n");
      out.write("                </a>\r\n");
      out.write("                </button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </nav>\r\n");
      out.write("        <!-------- Login page ------------->\r\n");
      out.write("        <header id=\"home-section-login\">\r\n");
      out.write("            <div class=\"dark-overlay\">\r\n");
      out.write("                <div class=\"home-inner\">\r\n");
      out.write("                    <div class=\"container\">\r\n");
      out.write("                        <div class=\"row justify-content-center\">\r\n");
      out.write("                            <div class=\"col-lg-\">\r\n");
      out.write("                                <div class=\"card bg-danger text-center card-form\">\r\n");
      out.write("                                    <div class=\"card-body\">\r\n");
      out.write("                                        <h3>Login Your Account</h3>\r\n");
      out.write("                                        <form action=\"ProcessLogin\" method=\"post\">\r\n");
      out.write("                                            <div class=\"form-group\">\r\n");
      out.write("                                                <input type=\"text\" class=\"form-control form-control-lg\" placeholder=\"Username\" name=\"username\">\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            <div class=\"form-group\">\r\n");
      out.write("                                                <input type=\"password\" class=\"form-control form-control-lg\" placeholder=\"Password\" name=\"password\">\r\n");
      out.write("                                            </div>\r\n");
      out.write("\r\n");
      out.write("                                            <button type=\"submit\" class=\"btn btn-primary btn-block\">Login</button>\r\n");
      out.write("                                            <p class=\"mt-2\">Dont have account?</p>\r\n");
      out.write("                                            <button class=\"btn btn-dark btn-block\"><a href=\"home.html\" class=\"text-white\">Register Now</a></button>\r\n");
      out.write("                                        </form>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("        </header>\r\n");
      out.write("        <!---------Footer ----------------->\r\n");
      out.write("        <footer id=\"main-footer\" class=\"bg-dark\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col text-center\">\r\n");
      out.write("                        <div class=\"py-4\">\r\n");
      out.write("                            <h1 class=\"h3\">Crush Team</h1>\r\n");
      out.write("                            <p>Copyright &copy; 2019</p>\r\n");
      out.write("                            <button class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#contactModal\">Contact Us</button>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </footer>\r\n");
      out.write("        <div class=\"modal fade text-dark\" id=\"contactModal\">\r\n");
      out.write("            <div class=\"modal-dialog\">\r\n");
      out.write("                <div class=\"modal-content\">\r\n");
      out.write("                    <div class=\"modal-header\">\r\n");
      out.write("                        <h5 class=\"modal-title\" id=\"contactModalTitle\">Contact Us</h5>\r\n");
      out.write("                        <button class=\"close\" data-dismiss=\"modal\"><span>&times;</span></button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"modal-body\">\r\n");
      out.write("                        <form>\r\n");
      out.write("                            <div class=\"form-group\">\r\n");
      out.write("                                <label for=\"name\">Name</label>\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"form-group\">\r\n");
      out.write("                                <label for=\"email\">Email</label>\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"form-group\">\r\n");
      out.write("                                <label for=\"name\">Message</label>\r\n");
      out.write("                                <textarea class=\"form-control\"></textarea>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </form>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"modal-footer\">\r\n");
      out.write("                        <button class=\"btn btn-danger btn-block\">Submit</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <script src=\"js/jquery.min.js\"></script>\r\n");
      out.write("        <script src=\"js/popper.min.js\"></script>\r\n");
      out.write("        <script src=\"js/bootstrap.min.js\"></script>\r\n");
      out.write("    </body>\r\n");
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
