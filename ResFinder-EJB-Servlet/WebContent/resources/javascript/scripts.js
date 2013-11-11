function onLoginLink(){
	document.getElementById("LoginTable").style.visibility="visible";
	document.getElementById("LoginForm:submitButtons").style.visibility="visible";
}

function onLoginAction(){
	document.getElementById("LoginTable").style.visibility="hidden";
	document.getElementById("LoginForm:welcomeMessage").style.visibility="visible";
	document.getElementById("LoginForm:submitButtons").style.visibility="hidden";
}