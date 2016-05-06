  <div id=nav>
	<p class=greeting>${!empty customer.firstname ? "Hello," : ""} ${ customer.firstname } ${!empty customer.firstname ? "!" : ""}</p>
	${!empty customer.firstname ? "<a href='Login?logout=true'><div class=button>Logout</div></a>" : ""}
	${!empty customer.firstname ? "<a href=ShoppingCart><div class=button>Cart</div></a>" : ""}
	${empty customer.firstname ? "<a href=register.jsp><div class=button>Register</div></a>" : ""}
	${empty customer.firstname ? "<a href='login.jsp'><div class=button>Login</div></a>" : ""}
	<a href="ShopProducts"><div class=button>Browse Products</div></a>
	<a href="index.jsp"><div class=button>Home</div></a>
</div>