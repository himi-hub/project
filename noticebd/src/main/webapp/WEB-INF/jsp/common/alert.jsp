<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Content-Type" content="text/xml; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<title>알림</title>
	<c:if test="${gotoUrl != null}">
	<script type="text/javascript">
	alert("${alertMsg}");
	<c:if test="${gotoUrl eq 'script'}">
	${excuteScript}
	</c:if>
	<c:if test="${gotoUrl ne 'script'}">
	window.location = "${gotoUrl}";
	</c:if>
	</script>
	</c:if>
	<c:if test="${gotoUrl == null}">
	<script type="text/javascript">
	alert("${alertMsg}");
	history.go(-1);
	</script>
	</c:if>
</head>
</html>