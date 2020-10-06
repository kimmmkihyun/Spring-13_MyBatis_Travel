<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
webapp\Travel_Start.jsp

<%
	String viewPage = request.getContextPath() + "/list.tv";
	
	response.sendRedirect(viewPage);
	
%>
