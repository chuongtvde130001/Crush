package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Crush</title>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/font-awesome.min.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/bootstrap.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body id=\"home\">\r\n");
      out.write("        ");

            if (session.getAttribute("user") != null) {
                response.sendRedirect("ProcessUserHome");
            }
        
      out.write("\r\n");
      out.write("        \r\n");
      out.write("        <nav class=\"navbar navbar-expand-sm bg-dark navbar-dark fixed-top\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <a href=\"home.html\" class=\"navbar-brand\">Crush</a>\r\n");
      out.write("                <button class=\"navbar-toggler\" data-toggle=\"collapse\" data-target=\"#navbarCollapse\">\r\n");
      out.write("                    <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("                </button>\r\n");
      out.write("                <div class=\"collapse navbar-collapse\" id=\"navbarCollapse\">\r\n");
      out.write("                    <ul class=\"navbar-nav ml-auto\">\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <a href=\"#home\" class=\"nav-link\">Home</a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <a href=\"login.jsp\" class=\"nav-link\">Login</a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <a href=\"#explore-head-section\" class=\"nav-link\">About Us</a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <a href=\"#share-head-section\" class=\"nav-link\">How We Work?</a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </nav>\r\n");
      out.write("\r\n");
      out.write("        <!-- HOME SECTION -->\r\n");
      out.write("        <header id=\"home-section\">\r\n");
      out.write("            <div class=\"dark-overlay\">\r\n");
      out.write("                <div class=\"home-inner\">\r\n");
      out.write("                    <div class=\"container\">\r\n");
      out.write("                        <div class=\"row\">\r\n");
      out.write("                            <div class=\"col-lg-8 d-none d-lg-block\">\r\n");
      out.write("                                <h1 class=\"display-4\">Find <strong>your true love</strong></h1>\r\n");
      out.write("                                <div class=\"d-flex flex-row\">\r\n");
      out.write("                                    <div class=\"p-4 align-self-start\">\r\n");
      out.write("                                        <i class=\"fa fa-check\"></i>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"p-4 align-self-end\">\r\n");
      out.write("                                        <p class=\"display-4\">Easy To Use</p>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("\r\n");
      out.write("                                <div class=\"d-flex flex-row\">\r\n");
      out.write("                                    <div class=\"p-4 align-self-start\">\r\n");
      out.write("                                        <i class=\"fa fa-check\"></i>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"p-4 align-self-end\">\r\n");
      out.write("                                        <p class=\"display-4\">Safe</p>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("\r\n");
      out.write("                                <div class=\"d-flex flex-row\">\r\n");
      out.write("                                    <div class=\"p-4 align-self-start\">\r\n");
      out.write("                                        <i class=\"fa fa-check\"></i>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"p-4 align-self-end\">\r\n");
      out.write("                                        <p class=\"display-4\">Private</p>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"col-lg-4\">\r\n");
      out.write("                                <div class=\"card bg-danger text-center card-form\">\r\n");
      out.write("                                    <div class=\"card-body\">\r\n");
      out.write("                                        <h3>Sign Up Today</h3>\r\n");
      out.write("                                        <p>Please fill out this form to register</p>\r\n");
      out.write("                                        <form action=\"ProcessRegister\" method=\"post\">\r\n");
      out.write("                                            <div class=\"alert-danger\" role=\"alert\">\r\n");
      out.write("                                                ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.error}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("                                            </div><br>\r\n");
      out.write("                                            <div class=\"form-group\">\r\n");
      out.write("                                                <input type=\"text\" class=\"form-control form-control-lg\" placeholder=\"Username\" name=\"username\">\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            <div class=\"form-group\">\r\n");
      out.write("                                                <input type=\"email\" class=\"form-control form-control-lg\" placeholder=\"Email\" name=\"email\">\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            <div class=\"form-group\">\r\n");
      out.write("                                                <input type=\"password\" class=\"form-control form-control-lg\" placeholder=\"Password\" name=\"password\">\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            <div class=\"form-group\">\r\n");
      out.write("                                                <input type=\"password\" class=\"form-control form-control-lg\" placeholder=\"Confirm Password\" name=\"confirmPassword\">\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            <input type=\"submit\" class=\"btn btn-outline-light btn-block\" value=\"Register\">\r\n");
      out.write("                                        </form>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </header>\r\n");
      out.write("\r\n");
      out.write("        <!-- ABOUT HEAD -->\r\n");
      out.write("        <section id=\"explore-head-section\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col text-center\">\r\n");
      out.write("                        <div class=\"py-5\">\r\n");
      out.write("                            <h1 class=\"display-4\">About Us</h1>\r\n");
      out.write("                            <p class=\"lead\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Incidunt sed, magni, veritatis aliquam sequi eveniet? Rerum quia accusantium alias provident perferendis</p>\r\n");
      out.write("                            <a href=\"#\" class=\"btn btn-outline-secondary\">More information</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("        <!-- HOW.. HEAD -->\r\n");
      out.write("        <section id=\"share-head-section\" class=\"bg-danger\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col text-center\">\r\n");
      out.write("                        <div class=\"py-5\">\r\n");
      out.write("                            <h1 class=\"display-4\">How We Work?</h1>\r\n");
      out.write("                            <p class=\"lead\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Incidunt sed, magni, veritatis aliquam sequi eveniet? Rerum quia accusantium alias provident perferendis</p>\r\n");
      out.write("                            <a href=\"#\" class=\"btn btn-outline-light\">Find Out More</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("        <!-- MAIN FOOTER -->\r\n");
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
      out.write("\r\n");
      out.write("        <!-- CONTACT MODAL -->\r\n");
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
      out.write("\r\n");
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
