<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일업로드</title>
</head>

<script>
function chkSubmit(){
	var frm = document.forms['frm'];
	var file1 = frm['file1'].value;
	var file2 = frm['file2'].value;
	
	if(file1 == "" && file2 == ""){
		alert("업로딩 할 파일이 없습니다.");
		return false;
	}
	
	return true;
}
</script>

<body>
<h2>파일업로드</h2>
<form name="frm" action="FileUpload.do" method="post" enctype="Multipart/form-data" onsubmit="return chkSubmit()">
	파일1(최대5MB): <input type="file" name="file1"><br>
	<hr>
	파일2(최대5MB): <input type="file" name="file2"><br>
	<hr>
	<input type="submit" value="업로드"/>
</form>
</body>
</html>

