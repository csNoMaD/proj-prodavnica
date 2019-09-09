<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/index.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<title>Online prodavnica</title>


<script>
	$(document).ready(function() {

		$('#loginBtn').click(function() {
			$('#registration').hide();
			$('#addProductPart').hide();
			$('#productsPart').hide();
			$('#categoriesPart').hide();
			$('#searchPart').hide();
			$('#addDelivererPart').hide();

			$('#adsFoundPart').hide();

			$('#login').show();
		});

		$('#profileBtn').click(function() {

			$('#customerFavouritePart').hide();
			$('#adsFoundPart').hide();
			$('#productsPart').hide();
			$('#categoriesPart').hide();
			$('#addDelivererPart').hide();
			$('#searchPart').hide();
			$('#changeRolePart').hide();
			$('#addProductPart').hide();
			$('#addDelivererPart').hide();
			$('#repotsPart').hide();
			$('#cartsPart').hide();
			$('#myOrderPart').hide();
			$('#deliveredOrderPart').hide();
			$('#viewReportsPart').hide();

			$('#userProfile').show();

		});

		$('#registrationBtn').click(function() {
			$('#login').hide();
			$('#addProductPart').hide();
			$('#productsPart').hide();
			$('#categoriesPart').hide();
			$('#searchPart').hide();
			$('#adsFoundPart').hide();
			$('#addDelivererPart').hide();

			$('#registration').show();
		});
		
		
		

		$('#reportsBtn').click(function() {
			$('#login').hide();
			$('#addProductPart').hide();
			$('#productsPart').hide();
			$('#categoriesPart').hide();
			$('#registration').hide();
			$('#adsFoundPart').hide();
			$('#addDelivererPart').hide();
			$('#changeRolePart').hide();
			$('#userProfile').hide();
			$('#customerCartPart').hide();
			$('#cartsPart').hide();
			$('#myOrderPart').hide();
			$('#deliveredOrderPart').hide();
			$('#customerPurchaseHistoryPart').hide();
			$('#customerFavouritePart').hide();
			$('#viewReportsPart').hide();

			$('#searchPart').hide();
			$('#repotsPart').show();


		});

		$('#addProductBtn').click(function() {
			$('#login').hide();
			$('#registration').hide();
			$('#changeRolePart').hide();
			$('#searchPart').hide();
			$('#adsFoundPart').hide();
			$('#productsPart').hide();
			$('#categoriesPart').hide();
			$('#addDelivererPart').hide();
			$('#userProfile').hide();
			$('#viewReportsPart').hide();

			$('#addProductPart').show();
		});

		$('#addDelivererBtn').click(function() {
			$('#login').hide();
			$('#registration').hide();
			$('#changeRolePart').hide();
			$('#addProductPart').hide();
			$('#searchPart').hide();
			$('#adsFoundPart').hide();
			$('#productsPart').hide();
			$('#categoriesPart').hide();
			$('#userProfile').hide();
			$('#viewReportsPart').hide();

			$('#addDelivererPart').show();
		});

		$('#usersBtn').click(function() {
			$('#addProductPart').hide();
			$('#searchPart').hide();
			$('#adsFoundPart').hide();
			$('#productsPart').hide();
			$('#categoriesPart').hide();
			$('#addDelivererPart').hide();
			$('#repotsPart').hide();

			$('#changeRolePart').show();
		});


		$('#searchBtn').click(function() {
			$('#login').hide();
			$('#addProductPart').hide();
			$('#productsPart').hide();
			$('#categoriesPart').hide();
			$('#registration').hide();
			$('#adsFoundPart').hide();
			$('#repotsPart').hide();
			$('#addDelivererPart').hide();
			$('#changeRolePart').hide();
			$('#userProfile').hide();
			$('#customerCartPart').hide();
			$('#cartsPart').hide();
			$('#myOrderPart').hide();
			$('#deliveredOrderPart').hide();
			$('#customerPurchaseHistoryPart').hide();
			$('#customerFavouritePart').hide();
			$('#viewReportsPart').hide();

			$('#searchPart').show();

		});

	});

	function changeRole(userRole, userId) {

		window.location.href = "/OnlineProdavnica/user/changeRole/" + userId
				+ "/" + userRole;

	}
</script>

</head>
<body>

	<table border="1" width="80%" align="center">
		<tr>
			<td height="100px">
				<h1 align="center">Online shop</h1>
			</td>
		</tr>
		<tr>
			<td height="25px">

				<button id="productsBtn" type="button" class="btn"
					onclick="window.location.href = '/OnlineProdavnica/product/showAll'">Products</button>
				<button id="categoriesBtn" type="button" class="btn"
					onclick="window.location.href = '/OnlineProdavnica/product/showCategory'">Categories</button>
				<button id="searchBtn" type="button" class="btn">Search</button>
				<button id="sortBtn" type="button" class="btn"
					onclick="window.location.href = '/OnlineProdavnica/product/sortName'">Sort
					products</button>

				<button id="saleBtn" type="button" class="btn" style="color: red"
					onclick="window.location.href = '/OnlineProdavnica/product/productsOnSale'">**SALE**</button>



				<c:if test="${sessionScope.loggedUser.role == 'ADMIN' }">

					<button id="addProductBtn" class="btn" type="submit">Add
						product</button>


					<button id="addDelivererBtn" class="btn" type="submit">Add
						deliverer</button>

					<button id="usersBtn" type="button" class="btn"
						onclick="window.location.href = '/OnlineProdavnica/user/allUsers'">Users</button>

					<button id="reportsBtn" class="btn" type="submit">Reports</button>

				</c:if> <c:if test="${sessionScope.loggedUser.role == 'CUSTOMER' }">

					<button id="favouritesBtn" type="button" class="btn"
						onclick="window.location.href = '/OnlineProdavnica/user/getFavourites/${sessionScope.loggedUser.id}'">Favourites</button>

					<button id="purchaseHistoryBtn" type="button" class="btn"
						onclick="window.location.href = '/OnlineProdavnica/user/showPurechesedProducts/${sessionScope.loggedUser.id}'">Purchase
						history</button>

					<button id="cartBtn" type="button" class="btn"
						onclick="window.location.href = '/OnlineProdavnica/user/getSelectedItems/${sessionScope.loggedUser.id}'">Cart</button>

				</c:if> <c:if test="${sessionScope.loggedUser.role == 'DELIVERER' }">
					<button id="cartsBtn" type="button" class="btn"
						onclick="window.location.href = '/OnlineProdavnica/user/getCarts'">Carts</button>

					<button id="myOrderBtn" type="button" class="btn"
						onclick="window.location.href = '/OnlineProdavnica/user/myOrders/${sessionScope.loggedUser.id}'">My
						order</button>

					<button id="cartBtn" type="button" class="btn"
						onclick="window.location.href = '/OnlineProdavnica/user/deliveredOrder/${sessionScope.loggedUser.id}'">Delivered
						order</button>

				</c:if> <c:if test="${sessionScope.loggedUser == null }">
					<div class="right">
						<button id="loginBtn" class="btn" type="submit">LogIn</button>
						<button id="registrationBtn" class="btn" type="submit">Registration</button>
					</div>
				</c:if> <c:if test="${sessionScope.loggedUser != null}">
					<div class="right">

						<button id="profileBtn" class="btn">Profile</button>

						<button id="logOutBtn" class="btn" type="submit"
							onclick="window.location.href = '/OnlineProdavnica/user/logOut'">LogOut</button>




					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td height="400px">

				<div id="addProductPart" style="display: none">
					<form action="/OnlineProdavnica/product/addProduct" method="post">
						<h1>Add Product</h1>
						<div class="textbox">
							<input name="name" type="text" placeholder="Product Name"
								required>
						</div>

						<div class="textbox">
							<input name="description" type="text" placeholder="Description"
								required>
						</div>

						<div class="textbox">
							<input name="price" type="number" placeholder="Price" required>
						</div>
						<div class="textbox">
							<input name="quantity" type="number" placeholder="Quantity"
								required>
						</div>
						<div class="textbox">
							<input name="category" type="text" placeholder="Category"
								required>
						</div>

						<input type="submit" class="btn" value="Add">
					</form>
					<p id="errorLog" style="color: red; display: none;"></p>

				</div>

				<div id="changeRolePart">
					<table>
						<c:if test="${! empty users}">
							<tr>
								<th>Name</th>
								<th>Surname</th>
								<th>Phone number</th>
								<th>Address</th>
								<th>Email</th>
								<th>Role</th>
							</tr>


							<c:forEach var="u" items="${users}">
								<tr>
									<td>${u.name}</td>
									<td>${u.surname}</td>
									<td>${u.phoneNumber}</td>
									<td>${u.adress}</td>
									<td>${u.email}</td>
									<td><select id='${u.id }' name="role"
										onchange="changeRole($(this).val(),'${u.id}')">
											<option value="ADMIN">ADMIN</option>
											<option value="CUSTOMER">CUSTOMER</option>
											<option value="DELIVERER">DELIVERER</option>

									</select> <script>
										$('#${u.id }').val('${u.role}');
									</script></td>

									<c:if test="${u.role == 'DELIVERER'}">
										<td>
											<button class="btn"
												onclick="window.location.href = '/OnlineProdavnica/user/getUser/${u.id}'">Edit</button>
										</td>
										<td>
											<button class="btn"
												onclick="window.location.href = '/OnlineProdavnica/user/removeUser/${u.id}'">Delete</button>
										</td>
									</c:if>

								</tr>


							</c:forEach>
						</c:if>
					</table>
				</div>

				<div id="login" class="login-box" style="display: none">
					<form action="/OnlineProdavnica/user/logIn" method="post">
						<h1>Log In</h1>
						<div class="textbox">
							<input name="userName" type="text" placeholder="Username"
								required>
						</div>
						<div class="textbox">
							<input name="password" type="password" placeholder="Password"
								required>
						</div>
						<input id="loginInput" type="submit" class="btn" value="Log In">
					</form>
					<p id="errorLog" style="color: red; display: none;"></p>

				</div>

				<div id="registration" class="login-box" style="display: none">
					<h1>Registration</h1>
					<form action="/OnlineProdavnica/user/insert" method="post">
						<div class="textbox">
							<input type="text" name="userName" placeholder="Username"
								pattern="^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"
								title="3-20 karaktera" required>
						</div>
						<div class="textbox">
							<input type="password" name="password" placeholder="Password"
								pattern="^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"
								title="3-20 karaktera" required>
						</div>
						<div class="textbox">
							<input type="text" name="name" placeholder="Name"
								pattern="([a-z]{3,20})" title="3-20 karaktera" required>
						</div>
						<div class="textbox">
							<input type="text" name="surname" placeholder="Surname"
								pattern="([a-z]{3,20})" title="3-20 karaktera" required>
						</div>
						<div class="textbox">
							<input type="text" name="phoneNumber" placeholder="Phone number"
								pattern="([0-9]{3,20})" title="3-20 cifara" required>
						</div>
						<div class="textbox">
							<input type="text" name="adress" placeholder="Adress" required>
						</div>
						<div class="textbox">
							<input type="email" name="email" placeholder="Email " required>
						</div>

						<input type="submit" class="btn" value="Register">
					</form>
					<p id="errorReg" style="color: red;"></p>

				</div>

				<div id="productsPart">

					<table>
						<c:if test="${! empty products}">
							<tr>
								<th>Name:</th>
								<th>Description:</th>
								<th>Price:</th>
								<th>Quantity:</th>
								<th>Category:</th>
							</tr>


							<c:forEach var="p" items="${products}">
								<tr>
									<td>${p.name}</td>
									<td>${p.description}</td>
									<td>${p.price}</td>
									<td>${p.quantity}</td>
									<td>${p.category}</td>
									<c:if test="${sessionScope.loggedUser.role == 'CUSTOMER'}">
										<td>
											<button class="btn"
												onclick="window.location.href = '/OnlineProdavnica/user/addToFavourites/${sessionScope.loggedUser.id}/${p.id}'">Add
												to favourite</button>
										</td>
										<td>
											<button class="btn"
												onclick="window.location.href = '/OnlineProdavnica/user/selectProduct/${p.id}/${sessionScope.loggedUser.id}'">Add
												to cart</button>
										</td>
									</c:if>
									<c:if test="${sessionScope.loggedUser.role == 'ADMIN'}">
										<td>
											<button id="editProductBtn" class="btn"
												onclick="window.location.href = '/OnlineProdavnica/product/getProduct/${p.id}'">Edit</button>
										</td>
										<td>
											<button class="btn"
												onclick="window.location.href = '/OnlineProdavnica/product/removeProduct/${p.id}'">Delete</button>
										</td>


									</c:if>
								</tr>


							</c:forEach>
						</c:if>
					</table>
				</div>

				<div id="categoriesPart">

					<table>
						<c:if test="${! empty categories}">

							<tr>
								<th>Category:</th>
							</tr>


							<c:forEach var="c" items="${categories}">
								<tr>
									<td><c:if test="${c != 'SALE'}">

											<button id='${c}' class="btn"
												onclick="window.location.href = '/OnlineProdavnica/product/productOfCategory/${c}'">${c}</button>
										</c:if></td>
								</tr>


							</c:forEach>

						</c:if>
					</table>

				</div>

				<div id="customerPurchaseHistoryPart">

					<table>
						<c:if test="${! empty purchasedItems}">
							<tr>
								<th>Name:</th>
								<th>Description:</th>
								<th>Price:</th>
								<th>Quantity:</th>
								<th>Category:</th>
							</tr>


							<c:forEach var="b" items="${purchasedItems}">
								<tr>
									<td>${b.name}</td>
									<td>${b.description}</td>
									<td>${b.price}</td>
									<td>${b.quantity}</td>
									<td>${b.category}</td>


								</tr>


							</c:forEach>
						</c:if>
					</table>
				</div>

				<div id="customerFavouritePart">

					<table>
						<c:if test="${! empty favourites}">
							<tr>
								<th>Name:</th>
								<th>Description:</th>
								<th>Price:</th>
								<th>Quantity:</th>
								<th>Category:</th>
							</tr>


							<c:forEach var="p" items="${favourites}">
								<tr>
									<td>${p.name}</td>
									<td>${p.description}</td>
									<td>${p.price}</td>
									<td>${p.quantity}</td>
									<td>${p.category}</td>


								</tr>


							</c:forEach>
						</c:if>
					</table>
				</div>

				<div id="userProfile" style="display: none">

					<table>
						<tr>
							<td>Name: ${sessionScope.loggedUser.name}</td>

						</tr>
						<tr>
							<td>Surname: ${sessionScope.loggedUser.surname}</td>

						</tr>
						<tr>
							<td>Phone number: ${sessionScope.loggedUser.phoneNumber}</td>

						</tr>
						<tr>
							<td>Address: ${sessionScope.loggedUser.adress}</td>

						</tr>
						<tr>
							<td>Email: ${sessionScope.loggedUser.email}</td>

						</tr>
					</table>



				</div>

				<div id="customerCartPart">

					<table>
						<c:if test="${! empty selectedItems}">
							<tr>
								<th>Name:</th>
								<th>Description:</th>
								<th>Price:</th>
								<th>Quantity:</th>
								<th>Category:</th>
							</tr>


							<c:forEach var="p" items="${selectedItems}">
								<tr>
									<td>${p.name}</td>
									<td>${p.description}</td>
									<td>${p.price}</td>
									<td>${p.quantity}</td>
									<td>${p.category}</td>


								</tr>


							</c:forEach>


						</c:if>
					</table>

					<c:if test="${! empty selectedItems}">

						<button id="buyBtn" type="button" class="btn"
							onclick="window.location.href = '/OnlineProdavnica/user/buy/${sessionScope.loggedUser.id}'">Buy</button>
					</c:if>


				</div>

				<div id="cartsPart">

					<table>
						<c:if test="${! empty cartss}">
							<tr>
								<th>Customer:</th>
								<th>Date:</th>
								<th>Price:</th>
							</tr>


							<c:forEach var="c" items="${cartss}">

								<%!int count = 0;%>
								<tr>
									<td id="names<%=count%>"></td>
									<td>${c.date}</td>
									<td>${c.price}</td>
									<td>
										<button class="btn"
											onclick="window.location.href = '/OnlineProdavnica/user/takeOrder/${c.id}/${sessionScope.loggedUser.id}'">Take
											the order</button>
									</td>
								</tr>
								<%
									count++;
								%>


							</c:forEach>

							<c:forEach var="n" items="${names}">
								<%!int i = 0;%>

								<script>
									$("#names" +
								<%=i%>
									).append("${n}");
								</script>
								<%
									i++;
								%>
							</c:forEach>

						</c:if>
					</table>

				</div>

				<div id="myOrderPart">

					<table>
						<c:if test="${! empty myOrder}">
							<tr>
								<th>Customer:</th>
								<th>Date:</th>
								<th>Price:</th>
							</tr>


							<c:forEach var="c" items="${myOrder}">
								<%!int v = 0;%>

								<tr>
									<td id="namess<%=v%>"></td>
									<td>${c.date}</td>
									<td>${c.price}</td>
									<td>
										<button class="btn"
											onclick="window.location.href = '/OnlineProdavnica/user/selectAsDelivered/${c.id}'">Mark
											as delivered</button>
									</td>
									<td>
										<button class="btn"
											onclick="window.location.href = '/OnlineProdavnica/user/cancelOrder/${c.id}/${sessionScope.loggedUser.id}'">Cancel</button>
									</td>
								</tr>
								<%
									v++;
								%>

							</c:forEach>

							<c:forEach var="n" items="${names}">
								<%!int c = 0;%>

								<script>
									$("#namess" +
								<%=c%>
									).append("${n}");
								</script>
								<%
									c++;
								%>
							</c:forEach>

						</c:if>
					</table>

				</div>

				<div id="deliveredOrderPart">

					<table>
						<c:if test="${! empty deliveredOrder}">
							<tr>
								<th>Customer:</th>
								<th>Date:</th>

							</tr>


							<c:forEach var="c" items="${deliveredOrder}">
								<%!int z = 0;%>

								<tr>
									<td id="namesss<%=z%>"></td>
									<td>${c.date}</td>

								</tr>
								<%
									z++;
								%>

							</c:forEach>
							<c:forEach var="n" items="${names}">
								<%!int x = 0;%>

								<script>
									$("#namesss" +
								<%=x%>
									).append("${n}");
								</script>
								<%
									x++;
								%>
							</c:forEach>

						</c:if>
					</table>

				</div>

				<div id="editProductPart">
					<c:if test="${p != null}">
						<form action="/OnlineProdavnica/product/editProduct/${p.id}"
							method="post">
							<h1>Edit Product</h1>
							<div class="textbox">
								<input name="name" type="text" value="${p.name}" required>
							</div>

							<div class="textbox">
								<input name="description" type="text" value="${p.description}"
									required>
							</div>

							<div class="textbox">
								<input name="price" type="number" value="${p.price}" required>
							</div>
							<div class="textbox">
								<input name="quantity" type="number" value="${p.quantity}"
									required>
							</div>
							<div class="textbox">
								<input name="category" type="text" value="${p.category}"
									required>
							</div>

							<input type="submit" class="btn" value="Edit">
						</form>
					</c:if>
				</div>

				<div id="addDelivererPart" style="display: none">
					<h1>Add deliverer</h1>
					<form action="/OnlineProdavnica/user/addDeliverer" method="post">
						<div class="textbox">
							<input type="text" name="userName" placeholder="Username"
								pattern="^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"
								title="3-20 karaktera" required>
						</div>
						<div class="textbox">
							<input type="password" name="password" placeholder="Password"
								pattern="^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"
								title="3-20 karaktera" required>
						</div>
						<div class="textbox">
							<input type="text" name="name" placeholder="Name"
								pattern="([a-z]{3,20})" title="3-20 karaktera" required>
						</div>
						<div class="textbox">
							<input type="text" name="surname" placeholder="Surname"
								pattern="([a-z]{3,20})" title="3-20 karaktera" required>
						</div>
						<div class="textbox">
							<input type="text" name="phoneNumber" placeholder="Phone number"
								pattern="([0-9]{3,20})" title="3-20 cifara" required>
						</div>
						<div class="textbox">
							<input type="text" name="adress" placeholder="Adress" required>
						</div>
						<div class="textbox">
							<input type="email" name="email" placeholder="Email " required>
						</div>

						<input type="submit" class="btn" value="Add">
					</form>

				</div>

				<div id="editDelivererPart">
					<c:if test="${ u != null}">
						<h1>Edit deliverer</h1>
						<form action="/OnlineProdavnica/user/editUser/${u.id}"
							method="post">

							<div class="textbox">
								<input type="text" name="name" value="${u.name}"
									pattern="([a-z]{3,20})" title="3-20 karaktera" required>
							</div>
							<div class="textbox">
								<input type="text" name="surname" value="${u.surname}"
									pattern="([a-z]{3,20})" title="3-20 karaktera" required>
							</div>
							<div class="textbox">
								<input type="text" name="phoneNumber" value="${u.phoneNumber}"
									pattern="([0-9]{3,20})" title="3-20 cifara" required>
							</div>
							<div class="textbox">
								<input type="text" name="adress" value="${u.adress}" required>
							</div>
							<div class="textbox">
								<input type="email" name="email" value="${u.email}" required>
							</div>

							<input type="submit" class="btn" value="Edit">
						</form>
					</c:if>
				</div>

				<div id="repotsPart" style="display: none">
					<table>
						<tr>
							<td>
								<button class="btn"
									onclick="window.location.href = '/OnlineProdavnica/product/createReportForToday'">Report
									for today</button>
							</td>

						</tr>

						<tr>
							<td>
								<button class="btn"
									onclick="window.location.href = '/OnlineProdavnica/product/createReportForWeek'">Report
									for week</button>
							</td>

						</tr>

						<tr>
							<td>
								<button class="btn"
									onclick="window.location.href = '/OnlineProdavnica/product/createReportForMonth'">Report
									for month</button>
							</td>

						</tr>

					</table>
				</div>

				<div id="viewReportsPart">
					<c:if test="${! empty cartsForReport}">

						<table>
							<tr>
								<th>Customer:</th>
								<th>Date:</th>
								<th>Price:</th>
								<th>Status:</th>

							</tr>


							<c:forEach var="c" items="${cartsForReport}">
								<%!int b = 0;%>
								<tr>
									<td id="namessss<%=b%>"></td>
									<td>${c.date}</td>
									<td>${c.price}</td>
									<td>${c.status}</td>

								</tr>
								<%
									b++;
								%>
							</c:forEach>
							<c:forEach var="n" items="${namess}">
								<%!int l = 0;%>

								<script>
									$("#namessss" +
								<%=l%>
									).append("${n}");
								</script>
								<%
									l++;
								%>
							</c:forEach>

						</table>

						<h4>Profit: ${profit}</h4>
					</c:if>


				</div>

				<div id="searchPart" style="display: none">
					<form action="/OnlineProdavnica/user/searchProduct" method="post">
						<h1>Search Product</h1>
						<div class="textbox">
							<input name="name" type="text" value=""
								placeholder="Product Name">
						</div>

						<div class="textbox">
							<input name="description" type="text" value=""
								placeholder="Description">
						</div>

						<div class="textbox">
							<input name="price" type="text" value="" placeholder="Price">
						</div>


						<input type="submit" class="btn" value="Search">
					</form>

				</div>



				<div id="adsFoundPart">

					<table>
						<c:if test="${! empty searchProducts}">
							<tr>
								<th>Name:</th>
								<th>Description:</th>
								<th>Price:</th>
								<th>Quantity:</th>
								<th>Category:</th>
							</tr>


							<c:forEach var="p" items="${searchProducts}">
								<tr>
									<td>${p.name}</td>
									<td>${p.description}</td>
									<td>${p.price}</td>
									<td>${p.quantity}</td>
									<td>${p.category}</td>
									<c:if test="${sessionScope.loggedUser.role == 'CUSTOMER'}">
										<td>
											<button class="btn"
												onclick="window.location.href = '/OnlineProdavnica/user/addToFavourites/${sessionScope.loggedUser.id}/${p.id}'">Add
												to favourite</button>
										</td>
										<td>
											<button class="btn"
												onclick="window.location.href = '/OnlineProdavnica/user/selectProduct/${p.id}/${sessionScope.loggedUser.id}'">Add
												to cart</button>
										</td>
									</c:if>
									<c:if test="${sessionScope.loggedUser.role == 'ADMIN'}">
										<td>
											<button id="editProductBtn" class="btn"
												onclick="window.location.href = '/OnlineProdavnica/product/getProduct/${p.id}'">Edit</button>
										</td>
										<td>
											<button class="btn"
												onclick="window.location.href = '/OnlineProdavnica/product/removeProduct/${p.id}'">Delete</button>
										</td>


									</c:if>
								</tr>


							</c:forEach>
						</c:if>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<h6 align="center">Web programiranje 2018/19 - Projekat</h6>

			</td>
		</tr>
	</table>
</body>

</html>