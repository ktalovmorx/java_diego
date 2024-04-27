import java.sql.*;  
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

public class Login {

    public static boolean autenticar(String name, String pass){  
        boolean status=false;  
        try{
            String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:\\ruta\\a\\tu\\base_de_datos.accdb;";
            Connection con = DriverManager.getConnection(url);

            PreparedStatement ps=con.prepareStatement(  
            "select * from userreg where name=? and pass=?");  
            ps.setString(1,name);  
            ps.setString(2,pass);  
                
            ResultSet rs=ps.executeQuery();  
            status=rs.next();  
        }catch(Exception e){
            System.out.println(e);
        }  
        return status;  
    }  

    public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        String n = request.getParameter("username");  
        String p = request.getParameter("userpass");  
        
        if(autenticar(n, p)){  
            RequestDispatcher rd=request.getRequestDispatcher("servlet2");  
            rd.forward(request,response);  
        }  
        else{  
            out.print("Sorry username or password error");  
            RequestDispatcher rd=request.getRequestDispatcher("index.html");  
            rd.include(request,response);  
        }  
    out.close();  
    }  

}  