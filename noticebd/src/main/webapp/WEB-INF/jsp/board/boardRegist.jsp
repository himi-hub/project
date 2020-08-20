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
				  <button type="button" onclick="fn_save();" class="btn btn-success">저장</button>
				  <button type="button" onclick="fn_goList();" class="btn">목록</button>
		      </div>
            </div>
            <div class="card-body">
              <div>
              	<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
	                <div class="row">
						<div class="col-md-12">
							<div class="box">
								<div class="box-body">
									<form name="form1" method="post" enctype="multipart/form-data">

										<div class="row">
												<div class="col-xs-12 table-responsive">
														<table class="table table-bordered tp2">
															<colgroup>
																<col style="width:15%"/>
																<col style="width:35%"/>
																<col style="width:15%"/>
																<col style="width:35%"/>
															</colgroup>
															<tbody>
																<tr>
																	<th class="t">제목</th>
																	<td colspan="3">
																		<input type="text" name="bd_title" maxlength="100" value="${boardInfo.BD_TITLE}" class="form-control"/>
																	</td>
																</tr>
																<tr>
																	<th class="t">작성자</th>
																	<td colspan="3">
																		<input type="text" name="bd_writer" maxlength="20" value="${boardInfo.BD_WRITER}" class="form-control"/>
																	</td>
																</tr>
																<tr>
																	<th class="t">내용</th>
																	<td colspan="3">
																		<textarea name="bd_content" style="width:100%; height:150px;" class="form-control">${boardInfo.BD_CONTENT}</textarea>
																	</td>
																</tr>
																<tr>
																	<th class="t">첨부파일1</th>
																	<td colspan="3">
																		<input type="file" name="bd_file1"/>
																	</td>
																</tr>
																<tr>
																	<th class="t">첨부파일2</th>
																	<td colspan="3">
																		<input type="file" name="bd_file2"/>
																	</td>
																</tr>
															</tbody>
														</table>
												</div><!-- /.col -->
											</div>
									</form>
								</div><!-- /.box-body -->
							</div><!-- /.box -->
						</div><!-- /.col -->
					</div><!-- /.row -->
	             </div>
              </div>
            </div>
          </div>
          
<script>
	/* 공지글 저장 */
	function fn_save() {
		var frm = document.forms["form1"];
		frm.action = "/board/boardRegistProc.do";
	    frm.submit();
	}

	/* 공지글 목록 화면 이동 */
	function fn_goList(){
		location.href="/board/boardList.do";
	}
</script>          