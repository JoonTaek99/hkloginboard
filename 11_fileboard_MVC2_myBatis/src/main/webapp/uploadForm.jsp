<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>파일업로드</h1>
<!-- 														     파일 넘겨받으려면 multipart/form-date로 해야함 -->
<form action="fileUpload.file" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th>파일:</th>
			<td><input type="file" name="filename" /></td>
		</tr>
		<tr>
			<th>다운로드(확인용):</th>
			<td><a href="upload/${param.filename}">${param.filename}</a></td>
		</tr>
		<tr>
			<th>다운로드:</th>
			<td><a href="downloadList.file">파일목록 보기</a></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="업로드할거면날눌러보아요" /></td>
		</tr>
	</table>
</form>
<h1>파일업로드 여러개</h1>
<!-- 														     파일 넘겨받으려면 multipart/form-date로 해야함 -->
<form action="fileMultiUpload.multi" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th>파일:</th>
			<td>
				<input type="file" name="filename1" /><br/>
				<input type="file" name="filename2" /><br/>
				<input type="file" name="filename3" />
			</td>

		</tr>
		<tr>
			<th>다운로드:</th>
			<td><a href="downloadList.file">파일목록 보기</a></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="업로드할거면날눌러봐" /></td>
		</tr>
	</table>
</form>
</body>
</html>