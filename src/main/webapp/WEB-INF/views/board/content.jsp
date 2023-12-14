<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">글 내용</h3>
		<div class="box-tools pull-right">
			<a href="#" class="btn btn-box-tool" data-toggle="tooltip" title="" data-original-title="Previous"><i class="fa fa-chevron-left"></i></a> <a href="#" class="btn btn-box-tool" data-toggle="tooltip" title="" data-original-title="Next"><i class="fa fa-chevron-right"></i></a>
		</div>
	</div>

	<div class="box-body no-padding">
		<div class="mailbox-read-info">
			<h3>${vo.title }</h3>
			<h5>
				<span class="mailbox-read-time pull-right">15 Feb. 2016 11:03 PM</span>
			</h5>
		</div>

		<div class="mailbox-read-message">
			<p>${vo.content }</p>
		</div>

	</div>

	<div class="box-footer">
		<div class="pull-right">
			<button type="button" class="btn btn-default">
				<i class="fa fa-reply"></i> Reply
			</button>
			<button type="button" class="btn btn-default">
				<i class="fa fa-share"></i> Forward
			</button>
		</div>
		<button type="button" class="btn btn-default">
			<i class="fa fa-trash-o"></i> Delete
		</button>
		<button type="button" class="btn btn-default">
			<i class="fa fa-print"></i> Print
		</button>
	</div>

</div>

<%@ include file="../include/footer.jsp"%>