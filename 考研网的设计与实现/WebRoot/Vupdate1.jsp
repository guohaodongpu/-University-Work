<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>

<html>
  <head>    
  </head>
  <body>
<%
   request.setCharacterEncoding("gbk"); 
   String vname=request.getParameter("vname");
   String vtele=request.getParameter("vtele");
   String vchat=request.getParameter("vchat");
   String vadd=request.getParameter("vadd");
 
%>
<%!   
  public static final String DBDRIVER="com.mysql.jdbc.Driver";
  
  public static final String DBURL="jdbc:mysql://localhost:3306/ghd";
  public static final String DBUSER="root";
  public static final String DBPASS="123456";
%>
<% 
   Connection conn=null;
   PreparedStatement pstmt=null;
   ResultSet rs=null;

   Class.forName(DBDRIVER);
   conn=DriverManager.getConnection(DBURL,DBUSER,DBPASS);
   String sql="update vip set vtele=?,vchat=?,vadd=? where vname=?";
   pstmt=conn.prepareStatement(sql);
   pstmt.setString(4,vname);
   pstmt.setString(1,vtele);
   pstmt.setString(2,vchat);
   pstmt.setString(3,vadd);
   pstmt.executeUpdate();
%> 
<%
   pstmt.close();
   conn.close();
   response.sendRedirect("admin_two.jsp");
%>   <br>
  </body>
</html>