/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Person;
import entity.Users;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.PersonFacade;
import session.UsersFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "SecurityController", urlPatterns = {
    "/login","/logout","/showRegistration","/registration", "/ShowLogin"
    })

public class SecurityController extends HttpServlet {
@EJB UsersFacade usersFacade;
@EJB PersonFacade personFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path=request.getServletPath();
        if(path !=null)
            switch(path){
                case "/login":
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Users regUser =usersFacade.findUserByLogin(username);
            if (regUser==null){
                request.setAttribute("info", "Неправильный логин или пароль");
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);}
            if(!password.equals(regUser.getPassword())){
                   request.setAttribute("info", "Неправильный логин или пароль");
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
                }
            HttpSession session = request.getSession(true);
            session.setAttribute("regUser", regUser);
              request.setAttribute("info", "Вы вошли в систему");
            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            break;
                case "/showRegistration":
                        request.getRequestDispatcher("/ShowRegistration.jsp").forward(request, response);
                                break;
                case "/ShowLogin":
                        request.getRequestDispatcher("/ShowLogin").forward(request, response);
                                break;
                case "/registration":
                    String name=request.getParameter("name");
                    String surname=request.getParameter("surname");
                    String email=request.getParameter("email");
                    String login=request.getParameter("login");
                    String password1=request.getParameter("password1");
                    String password2=request.getParameter("password2");
                    if(!password1.equals(password2)){
                        request.setAttribute("info", "Неодинаковые пароли");
                        request.getRequestDispatcher("/ShowRegistration").forward(request, response);
                        break;
                    }
                    Person person = new Person(name,surname,email);
                    personFacade.create(person);
                    Users user =new Users(login,password1,person);
                    usersFacade.create(user);
                    request.setAttribute("info", "Вы зарегистрированы");
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                                break;
            }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
