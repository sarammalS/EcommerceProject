<%@ page import="com.eazydeals.helper.ConnectionProvider" %>
    <%@ page import="java.sql.*" %>
        <%@ page import="java.io.*" %>
            <%@ page import="java.util.Scanner" %>
                <% Connection con=null; Statement st=null; try { con=ConnectionProvider.getConnection();
                    st=con.createStatement(); File file=new File("g:/E-Commerce-Website-master/unified_schema.sql");
                    Scanner scanner=new Scanner(file); scanner.useDelimiter(";"); int count=0; out.println("<h3>Starting
                    Database Setup...</h3>");
                    while(scanner.hasNext()){
                    String command = scanner.next().trim();
                    if(command.length() > 0) {
                    try {
                    st.execute(command);
                    count++;
                    } catch(Exception e) {
                    out.println("<p style='color:red'>Error executing: " + command + "<br />" + e.getMessage() + "</p>
                    ");
                    }
                    }
                    }
                    out.println("<h3>Database setup complete! Executed " + count + " statements.</h3>");

                    } catch(Exception e) {
                    out.println("<h2>Error: " + e.getMessage() + "</h2>");
                    e.printStackTrace();
                    } finally {
                    if(st != null) st.close();
                    }
                    %>