package acnbatch;

import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Arun Gupta
 */
@WebServlet(urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {
//
//    @PersistenceUnit(name = "MyPU")
//    EntityManagerFactory em;

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
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CSV-to-Database Chunk Job</title>");
            out.println("</head>");
            out.println("<bodSy>");
            out.println("<h1>CSV-to-Database Chunk Job</h1>");
            JobOperator jo = BatchRuntime.getJobOperator();
            long jid = jo.start("job", new Properties());
            out.println("Job submitted: " + jid + "<br>");
            out.println("<br><br>Check server.log for output, also look at \"myJob.xml\" for Job XML.");
            out.println("</body>");
            out.println("</html>");
        } catch (JobStartException | JobSecurityException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
