<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<script type="text/javascript" src='js/bootstrap.js'></script>
<script type="text/javascript"
	src="https://pagination.js.org/dist/2.1.5/pagination.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#applicationsTable').DataTable();
		$('.dataTables_length').addClass('bs-select');
	});
</script>
</head>
<body>
	<jsp:include page="../header/header.jsp" />
	<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
		<div class="d-flex flex-row">
			<div class="p-2">
				<button type="button" class="btn btn-primary"
					onclick="window.location.href='${pageContext.request.contextPath}/controller?command=CHANGE_TO_POSTED'">Posted</button>
			</div>
			<div class="p-2">
				<button type="button" class="btn btn-primary"
					onclick="window.location.href='${pageContext.request.contextPath}/controller?command=CHANGE_TO_VERYFYING'">Verifying</button>
			</div>
			<div class="p-2">
				<p>Search by organization:</p>
			</div>
			<div class="p-2">
				<form name="findByOrganization" method="post" id="localeForm"
					action="${pageContext.request.contextPath}/controller">
					<input type="hidden" name="command" value="APPLICANT_FILTERED" />
					<select class="form-select form-select-sm"
						aria-label=".form-select-sm example" name="organization"
						onChange="this.form.submit()">
						<c:forEach items="${applicantsList}" var="applicant">
							<option value="${applicant.getOrganizationName()}"
								${applicant.getOrganizationName() == selectedName ? 'selected="selected"' : ''}>${applicant.getOrganizationName()}</option>
						</c:forEach>
					</select>
				</form>
			</div>
		</div>
		<div class="d-flex flex-row-reverse bd-highlight">
			<div class="p-2 bd-highlight">
				<input type="button" class="btn btn-primary"
					value="Create Application" />
			</div>
		</div>
	</nav>

	<table id="applicationsTable" class="table table-borderless-xl">
		<thead>
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Photo</th>
				<th scope="col">Name</th>
				<th scope="col">Surname</th>
				<th scope="col">Traits</th>
				<th scope="col">Weight</th>
				<th scope="col">Height</th>
				<th scope="col">Description</th>
				<th scope="col">Reward</th>
				<th scope="col">Expiration date</th>
				<th scope="col">Applicant</th>
			</tr>
		</thead>
		<c:forEach items="${unpostedList}" var="application">
			<tr>
				<td id="applicationId">${application.getApplicationId()}</td>
				<td><img id="wanted_image"
					src="image/wanted/${application.getPhoto()}" width="70" height="70" /></td>
				<td id="name">${application.getName()}</td>
				<td id="surname">${application.getSurname()}</td>
				<td id="traits">${application.getTraits()}</td>
				<td id="weight">${application.getWeight()}</td>
				<td id="height">${application.getHeight()}</td>
				<td id="description">${application.getDescription()}</td>
				<td id="reward">${application.getReward()}</td>
				<td id="exdate">${application.getExpirationDate()}</td>
				<c:forEach items="${applicantsList}" var="applicants">
					<c:if
						test="${applicants.getId() == application.getApplicantId()}">
						<td>${applicants.getOrganizationName()}</td>
					</c:if>
				</c:forEach>
				<td><button type="button" class="btn btn-primary"
						onclick="window.location.href='${pageContext.request.contextPath}/controller?command=EDIT_APPLICATION&applicationId=${application.getApplicationId()}'">Edit</button>
					<button type="submit" class="btn btn-primary"
						onclick="window.location.href='${pageContext.request.contextPath}/controller?command=DELETE_APPLICATION&applicationId=${application.getApplicationId()}'">Delete</button>
					<c:if test="${application.getStatusId() == 1}">
						<button type="button" class="btn btn-primary"
							onclick="window.location.href='${pageContext.request.contextPath}/controller?command=POST&applicationId=${application.getApplicationId()}'">Post</button>
					</c:if></td>
				<c:if test="${application.getStatusId() == 2}">
					<td>
						<p>Posted</p>
					</td>
				</c:if>

			</tr>
		</c:forEach>
	</table>
	<ul class="pagination">
		<li class="paginate_button page-item previous"
			id="dtBasicExample_previous"><a href="#"
			aria-controls="dtBasicExample" data-dt-idx="0" tabindex="0"
			class="page-link">Previous</a></li>
		<li class="paginate_button page-item "><a href="#"
			aria-controls="dtBasicExample" data-dt-idx="1" tabindex="0"
			class="page-link">1</a></li>
		<li class="paginate_button page-item "><a href="#"
			aria-controls="dtBasicExample" data-dt-idx="2" tabindex="0"
			class="page-link">2</a></li>
		<li class="paginate_button page-item "><a href="#"
			aria-controls="dtBasicExample" data-dt-idx="3" tabindex="0"
			class="page-link">3</a></li>
		<li class="paginate_button page-item active"><a href="#"
			aria-controls="dtBasicExample" data-dt-idx="4" tabindex="0"
			class="page-link">4</a></li>
		<li class="paginate_button page-item "><a href="#"
			aria-controls="dtBasicExample" data-dt-idx="5" tabindex="0"
			class="page-link">5</a></li>
		<li class="paginate_button page-item "><a href="#"
			aria-controls="dtBasicExample" data-dt-idx="6" tabindex="0"
			class="page-link">6</a></li>
		<li class="paginate_button page-item next" id="dtBasicExample_next"><a
			href="#" aria-controls="dtBasicExample" data-dt-idx="7" tabindex="0"
			class="page-link">Next</a></li>
	</ul>
	</ul>
	</nav>
</body>
</html>