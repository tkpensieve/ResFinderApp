function onLoginLink(){
	document.getElementById("LoginTable").style.display="inline";
	document.getElementById("LoginForm:submitButtons").style.display="inline";
}

function onLoginAction(){
	document.getElementById("LoginTable").style.display="none";
	document.getElementById("LoginForm:welcomeMessage").style.display="inline";
	document.getElementById("LoginForm:submitButtons").style.display="none";
}