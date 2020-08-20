<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">공지</h1>
          <p class="mb-4">공지 게시판입니다.</p>
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <div class="text-right">
              	<a href="/board/boardRegist.do" class="btn btn-info">등록</a>
		      </div>
            </div>
            <div class="card-body">
              <div class="table-responsive">
              	<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
	                <table class="table table-bordered">
<!-- 	                <table class="table table-bordered" id="dataTable"> -->
	             	  <colgroup>
	               		<col width="5%">
	               		<col>
	               		<col width="15%">
	               		<col width="15%">
	               	  </colgroup>
	                  <thead>
	                    <tr>
	                      <th>NO</th>
	                      <th>제목</th>
	                      <th>작성자</th>
	                      <th>작성일</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                  	<c:forEach var="list" items="${boardList}" varStatus="i">
		                    <tr>
		                      <td>${paging.total-paging.start+1-i.index}</td>
		                      <td><a href="#none" onclick="fn_goView('${list.BD_IDX}');">${list.BD_TITLE}</a></td>
		                      <td>${list.BD_WRITER}</td>
		                      <td><fmt:formatDate pattern="yyyy-MM-dd" value="${list.BD_IN_DT}"/></td>
		                    </tr>
		                </c:forEach>    
		                <c:if test="${empty boardList}">
			                <tr class="odd">
			                	<td valign="top" colspan="4" class="dataTables_empty">
			                		No data available in table
			                	</td>
			                </tr>
			            </c:if>
	                  </tbody>
	                </table>
	                <div class="row">
		            	<div class="col-sm-12 col-md-5">
		            		<div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">total ${paging.total} entries</div>
		            	</div>
		            	<div class="col-sm-12 col-md-7">
		            		<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
		            			<ul class="pagination">
		            				<c:if test="${paging.startPage != 1 }">
		            					<li class="paginate_button page-item previous disabled" id="dataTable_previous">
		            						<a href="/board/boardList.do?nowPage=${paging.startPage-1}&cntPerPage=${paging.cntPerPage}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
										</li>
									</c:if>
									<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
										<c:choose>
											<c:when test="${p == paging.nowPage }">
												<a href="#" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">${p}</a>
											</c:when>
											<c:when test="${p != paging.nowPage }">
												<li class="paginate_button page-item active">
													<a href="/board/boardList.do?nowPage=${p}&cntPerPage=${paging.cntPerPage}" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">${p}</a>
												</li>
											</c:when>
										</c:choose>
									</c:forEach>
									<c:if test="${paging.endPage != paging.lastPage}">
										<li class="paginate_button page-item next disabled" id="dataTable_next">
			            					<a href="/board/boardList.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}" aria-controls="dataTable" data-dt-idx="2" tabindex="0" class="page-link">Next</a>
			            				</li>
									</c:if>
		            			</ul>
		            		</div>
		            	</div>
		            </div>
	             </div>
              </div>
            </div>
          </div>
          
<script>
	/* 공지글 상세조회 화면 이동 */
	function fn_goView(idx) {
		location.href="/board/boardEdit.do?bd_idx="+idx+"&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}";
	}
</script>          