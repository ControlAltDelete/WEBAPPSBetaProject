function redirect()
{
  document.getElementById("Signup").onclick = function() 
  {
  	addUserToList();	
  };
}

function addUserToList()
{
  var emailofUser = document.getElementById('inputEmail').value;
  var pwdOfUser = document.getElementById('inputPassword').value;
  var cnfrm = document.getElementById("cnfrmPassword").value;
  var emptyCount = 0;


  console.log(emailofUser);
  if (emailofUser == "")
  {
  	alert("Email cannot be empty");
  	emptyCount++;
  }

  if (pwdOfUser == "")
  {
  	alert("Password cannot be empty");
  	emptyCount++;
  }

  if (pwdOfUser != cnfrm)
  {
  	alert("Password does not match");
  	emptyCount++;
  }

  if (emptyCount == 0)
  {
    setCookie(emailofUser, emailofUser, 2);
    var y = readCookie(emailofUser);
    console.log(y);
    window.location.href = "homepage.html";
  }
}

function setCookie(cName, cValue, days)
{
  console.log("cookie name: " + cName + " cookie value: " + cValue);
  var date = new Date();
  date.setTime(date.getTime() + (days * 24 * 60 * 60 *100));
  var expires = "expires=" + date.toGMTString();
  var saveThisCookie = cName + "=" + cValue + "; " + expires + "; path=/";
  console.log(saveThisCookie);
  document.cookie = saveThisCookie;
}

function readCookie(name) 
{
  var nameEQ = name + "=";
  var ca = document.cookie.split(';');
	
  for(var i=0;i < ca.length;i++) 
  {
	var c = ca[i];
	
	while (c.charAt(0)==' ') 
	{
	  c = c.substring(1, c.length);
	}

	if (c.indexOf(nameEQ) == 0) 
	{
	  return c.substring(nameEQ.length,c.length);
    }
  }

  return null;
}

function checkCookie() 
{
  var toBeChecked = document.getElementById('inputEmail').value;
  var username = readCookie(toBeChecked);
  
  if (username != null) 
  {
    window.location.href="main.html"
  }
  
  else
  {
    alert("Username or password is invalid");
  }
}