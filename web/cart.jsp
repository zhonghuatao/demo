<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>购物车</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	function pNum(pid,p,no){
		var nums = $("#num_count"+no).val();
		$.ajax({
			url:"${pageContext.request.contextPath}/cart?methods=addCartNum&pid="+pid,
			method:"get",
			success:function(){
				location.href = "${pageContext.request.contextPath}/cart?methods=getCart";
			},
			error:function(){
				alert("服务器异常");
			}
		})
	}
	function mNum(pid,p,no){
		var num = -1; //数量
		var nums = $("#num_count"+no).val();
		if(Number(nums)<=1){
			if(confirm("确认要删除吗?")){
				num = 0;
			}else{
				return;
			}
		}
		$.ajax({
			url:"${pageContext.request.contextPath}/cart?methods=removeCartNum&pid="+pid,
			method:"get",
			success:function(){
				location.href = "${pageContext.request.contextPath}/cart?methods=getCart";
			},
			error:function(){
				alert("服务器异常");
			}
		})
	}
	function clearCart(pid){
		if(confirm("确认要删除吗")&&pid!=0){
			location.href="${pageContext.request.contextPath}/cart?methods=deleteCart&pid="+pid;
		}
		console.log(pid);
		if (pid===0){
			$.ajax({
				url:"${pageContext.request.contextPath}/cart?methods=removeCartAll",
				method:"get",
				success:function(){
					location.href = "${pageContext.request.contextPath}/cart?methods=getCart";
				},
				error:function(){
					alert("服务器异常");
				}
			})
		}
	}
</script>
</head>
<body style="background-color:#f5f5f5">
<%@ include file="header.jsp"%>
<div class="container" style="background-color: white;">
	<div class="row" style="margin-left: 40px">
		<h3>我的购物车<small>温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</small></h3>
	</div>
	<div class="row" style="margin-top: 40px;">
		<div class="col-md-10 col-md-offset-1">
			<table class="table table-bordered table-striped table-hover">
 				<tr>
 					<th>序号</th>
 					<th>商品名称</th>
 					<th>价格</th>
 					<th>数量</th>
 					<th>小计</th>
 					<th>操作</th>
 				</tr>
 				<c:set value="0" var="sum"></c:set>
 				<c:forEach items="${carts}" var="c" varStatus="i">
	 				<tr>
	 					<th>${i.count}</th>
	 					<th>${c.product.p_name}</th>
	 					<th>${c.product.p_price}</th>
	 					<th width="100px">
		 					<div class="input-group">
		 						<span class="input-group-btn">
		 							<button class="btn btn-default" type="button" onclick="mNum(${c.product.p_id},${c.product.p_price},${i.count})">-</button>
		 						</span>
		 						<input type="text" class="form-control" id="num_count${i.count}" value="${c.c_num}" readonly="readonly" style="width:40px">
		 						<span class="input-group-btn">
		 							<button class="btn btn-default" type="button" onclick="pNum(${c.product.p_id},${c.product.p_price},${i.count})">+</button>
		 						</span>
	 						</div>
	 					</th>
	 					<th>¥&nbsp;${c.c_count }</th>
	 					<th>
	 						<button type="button" class="btn btn-default" onclick="clearCart(${c.product.p_id})">删除</button>
	 					</th>
	 				</tr>
	 				<c:set var="sum" value="${sum+c.c_count}"></c:set>
 				</c:forEach>
			</table>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="pull-right" style="margin-right: 40px;">

	            <div>
	            	<button id="removeAllProduct" onclick="clearCart(0)" class="btn btn-default btn-lg">清空购物车</button>
	            	&nbsp;&nbsp;
	            	<a href="${pageContext.request.contextPath}/address?methods=selectAddr" class="btn  btn-danger btn-lg">添加收货地址</a>
	            	<a href="${pageContext.request.contextPath}/cart?methods=orderPv" class="btn  btn-danger btn-lg">订单预览</a>

	            </div>
	            <br><br>
	            <div style="margin-bottom: 20px;">
	            	商品金额总计：<span id="total" class="text-danger"><b>￥&nbsp;&nbsp;${sum}</b></span>
	            </div>
		</div>
	</div>
</div>


<!-- 底部 -->
<%@ include file="footer.jsp"%>

</body>
</html>
