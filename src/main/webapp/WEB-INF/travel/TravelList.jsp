<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>    
<script type="text/javascript">
	function insert() {
		location.href = "insert.prd";
	}
	
	function goUpdate(num,pageNumber,pageSize){
		location.href="update.tv?num="+num+"&pageNumber="+pageNumber+"&pageSize="+pageSize; 
	}
</script>

trave\TravelList.jsp<br>
<h2>여행 리스트 화면 (총 레코드 개수 :${ totalCount})</h2>
<form action="list.tv" method="get">
	<select name="whatColumn">
		<option value="all">전체검색
		<option value="area">지역
		<option value="style">여행 타입
	</select>
	<input type="text" name="keyword" value="유럽">
	<input type="submit" value="검색">
</form>
<table border="1">
	<tr>
		<td colspan="8" align="right">
			<input type="button" value="추가하기" onClick="insert()">
		</td>
	</tr>
			
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>나이</th>
		<th>관심지역</th>
		<th>여행타입</th>
		<th>예상비용</th>
		<th>삭제</th>
		<th>수정</th>
	</tr>
	
	<c:forEach items="${lists}" var="travel">
		<tr>
			<td>${travel.num }</td>
			<td>${travel.name }</td>
			<td>${travel.age }</td>
			<td>${travel.area }</td>
			<td>${travel.style }</td>
			<td>${travel.price }</td>
			<td><a href="delete.tv?num=${travel.num }&pageNumber=${pageInfo.pageNumber}">삭제</a></td>
			<td><input type="button" value="수정" onClick="goUpdate('${travel.num }','${pageInfo.pageNumber}','${pageInfo.pageSize}')"/>
			</td>
		</tr>
	</c:forEach>
</table>
<br>
${pageInfo.pagingHtml} <br>
