/**
 * 
 */

var env = null;

function init() {
	
	makeWelcomePanel();
	
	
}



/*####################################################################################################*
 *-----------------------------------------Make Welcome Panel-----------------------------------------* 
 *####################################################################################################*/

function makeWelcomePanel() {
	var body = "" +
	"<div id=\"accueil\">"+
		"<header class=\"mainheader\">"+
			"<nav>"+
				"<div id=\"title\">"+
					"<a href=\"index.html\"><img src=\"img/logo.png\" class=\"title\" alt=\"logo\" width=\"100px\" height=\"60px\"></a>"+
				"</div>"+
				"<div id=\"search_box\">"+
					"<form class=\"form-wrapper\">"+
						"<input type=\"text\" placeholder=\"Search ...\">"+
						"<button type=\"submit\">Go</button>"+
					"</form>"+
				"</div>"+
				"<div id=\"small_menu\">"+
					"<a href=\"doc.html\" target=\"_blank\">Documentation</a>"+
				"</div>"+
				"<div id=\"button_signin\">"+
					"<a href=\"#login_form\">Sign in</a>"+
				"</div>"+
			"</nav>"+
		"</header>"+
		"<a href=\"#\" class=\"overlay\" id=\"login_form\"></a>"+
		"<div id= \"pop\" class=\"popup\">"+
			"<form class=\"signin_form\" method=\"get\" action=\"javascript:connexion(this);\">"+
				"<h1>Login</h1>"+
				"<div id=\"login_obligatoire\">Login obligatoire</div>"+
				"<div id=\"mdp_obligatoire\">Mot de passe obligatoire</div>"+
				"<div id=\"deja_connecter\">Vous êtes dêja connecter</div>"+
				"<div id=\"erreur_login\">login n'est pas correcte</div>"+
				"<div id=\"erreur_mdp\">mode de passe n'est pas correcte</div>"+
				"<div id=\"serveur_erreur\">Oups une erreur inattendue s'est produite ,Verifiez votre connection et reessayer</div>"+
				"<div><input type=\"text\" name=\"login\" placeholder=\"Login\" id=\"username\" /></div>"+
				"<div><input type=\"password\" name=\"pwd\" placeholder=\"Password\" id=\"password\" /></div>"+
				"<input type=\"submit\" value=\"Go\" id=\"signin\" />"+
			"</form>"+
			"<button onclick=\"javascript:forgotten_password(); return false;\">forgotten password</button>"+
			"<a class=\"close\" href=\"#close\"></a>"+
		"</div>"+
	"</div>"+
	"<div id=\"motPasseOublie\">"+
		"<div id=\"global_content\">"+
			"<div id=\"profile\">"+
				"<form class=\"inscription_form\" method=\"get\" action=\"javascript:sendMotPasse(this);\">"+
					"<div id=\"Login_does_not_exist\">Login does not exist</div>"+
					"<div id=\"Invalid_mail\">Email obligatoire</div>"+
					"<div id=\"mailmotPasseOublie\">Email obligatoire</div>"+
					"<div id=\"loginmotPasseOublie\">Login obligatoire</div>"+
					"<span id=\"username_error\"></span>" +
					"	Login : <input type=\"text\" class=\"inscription_input loginmotPasseOublie\" name=\"loginmotPasseOublie\" placeholder=\"Pick a username\"> <br /> "+
					"<span id=\"email_error\"></span>" +
					"	Email : <input type=\"text\" class=\"inscription_input mailmotPasseOublie\" name=\"emailmotPasseOublie\" placeholder=\"Your email\" pattern=\"^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$\">"+
					"<br />"+
					"<button type=\"submit\" id=\"submit\">Send</button>"+
				"</form>"+
			"</div>"+
		"</div>"+
	"</div>"+
	"<div id=\"main_content1\">"+
		"<div id=\"global_content\">"+
			"<div id=\"inscription\">"+
				"<div id=\"firstName\">Prénom obligatoire</div>"+
				"<div id=\"lastName\">Nom obligatoire</div>"+
				"<div id=\"usernameSignUp\">Login obligatoire</div>"+
				"<div id=\"email\">Email obligatoire</div>"+
				"<div id=\"password1\">Mot de passe obligatoire</div>"+
				"<div id=\"password2\">Mot de passe de confirmation obligatoire</div>"+
				"<div id=\"passwordVerif\">Les deux mots de passe necorrespondent pas</div>"+
				"<div id=\"login_tailleSignUp\">La taille du login doit êtreentre 6 et 20 caractères.</div>"+
				"<div id=\"login_existant\">Veuillez choisir un autre login.</div>"+
				"<div id=\"deconnect_login\">Vous etes deconnecté.</div>"+
				"<div id=\"mdp_tailleSignUp\">La taille du mot de passe doitêtre entre 6 et 15 caractères.</div>"+
				"<form class=\"inscription_form\" method=\"get\" action=\"javascript:creation(this);\">"+
					"<span id=\"firstname_error\"></span> "+ 
					"<input type=\"text\"class=\"inscription_input\" name=\"firstName\" placeholder=\"First name\"> "+
					"<br /> "+
					"<span id=\"lastname_error\"></span> "+
					"<input type=\"text\" class=\"inscription_input\" name=\"lastName\" placeholder=\"Last name\">"+
					"<br /> "+
					"<span id=\"username_error\"></span> "+
					"<input type=\"text\" class=\"inscription_input\" name=\"usernameSignUp\" placeholder=\"Pick a username\">"+
					"<br /> "+
					"<span id=\"email_error\"></span> "+
					"<input type=\"text\" class=\"inscription_input\" name=\"email\" placeholder=\"Your email\" pattern=\"^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$\">"+
					"<br /> "+	
					"<input type=\"password\" class=\"inscription_input\" name=\"password\" placeholder=\"Create a password\"> <span id=\"password_error\"></span> "+
					"<br />"+
					"<input type=\"password\" class=\"inscription_input\" name=\"re_password\" placeholder=\"Confirm your password\"> "+
					"<spanid=\"re_password_error\"></span>"+
					"<br />"+
					"<button type=\"submit\" id=\"submit\">Sign Up</button>"+
				"</form>"+
				"<span id=\"subscribe_marketing\">Don't have an account ? It'sfree. Fill this form, it will take only few seconds.</span>"+
			"</div> "+
		"</div> "+
	"</div> "+
	"<div id=\"header\">"+
		"<header class=\"mainheader\">"+
			"<nav>"+
				"<div id=\"title\">"+
					"<a href=\"#\" onclick=\"javascript:home(); return false;\"><img src=\"img/logo.png\" class=\"title\" alt=\"logo\" width=\"100px\" height=\"60px\"></a>"+
					"</div>"+
				"<div id=\"search_box\">"+
					"<form class=\"form-wrapper\" method=\"get\" action=\"javascript:rechercheCommentaire(this);\">"+
						"<input type=\"text\" placeholder=\"Search ...\" name=\"search\">"+
						"<button type=\"submit\">Go</button>"+
					"</form>"+
				"</div>"+
				"<div id=\"small_menu\">"+
					"<input type=\"checkbox\" name=\"search_friend\" value=\"1\" id=\"checkbox\"><span style=\"font-size: 0.8em\"> Friends only</span>"+
				"</div>"+
				"<div id=\"button_logout\"> Hi, <span id=\"connected_name\">" +
					"</span> <a href=\"#\" onclick=\"logout(); return false;\">Disconnect</a>"+
				"</div>"+
			"</nav>"+
		"</header>"+
	"</div>"+
	"<div id=\"Global\" class ='row'>"+
		"<div id=\"countlike\" class=\"menu1\"> nblike<span id=\"countlike\"></span>"+
		"</div>"+
		"<div id=\"countmessage\" class=\"menu1\">nbmessage<span id=\"countmessage\"></span>"+
		"</div>"+
		"<div id=\"menu\" class='col-xs-3'>"+
			"<div id=\"menu1\" class=\"menu1\">"+
				"<a href=\"javascript:profile();\"><img style=\"width: 40px; height: 40px; vertical-align: middle\" src=\"img/ic_profile.png\" /></a> <a href=\"javascript:profile();\">Profile</a>"+
			"</div>"+
			"<div id=\"menu2\" class=\"menu2\">"+
				"<a href=\"javascript:changeMotDepasse();\"><img style=\"width: 40px; height: 40px; vertical-align: middle\" src=\"img/ic_profile.png\" /></a> <a href=\"javascript:changeMotDepasse();\">Change mot de passe</a>"+
			"</div>"+
			"<div id=\"menu3\" class=\"menu3\">"+
				"<a href=\"javascript:chargerlistesFriends(this);\"><img src=\"img/ic_mine.png\" style=\"width: 40px; height: 40px; vertical-align: middle\" /></a> "+
				"<a href=\"javascript:chargerlistesFriends(this);\">Number of friends : </a><span id=\"number_amis\"></span>" +
				"</br><span id=\"zone_stats1\"></span>"+
			"</div>"+
		"</div>"+
		"<div id=\"main\" class='col-xs-6'>"+
			"<div id=\"message_box\">"+
				"<form class=\"message_form\" method=\"get\"	action=\"javascript:envoiMessage(this);\">"+
					"<textarea class=\"box\" name='message' id='message'></textarea>"+
					"<input type=\"submit\" value=\"message\" id=\"message_button\" />"+
				"</form>"+
				"<hr>"+
			"</div>"+
			"<h3 id=\"msgTilte\">La liste des messages</h3>"+
			"<div id=\"insert_messages\"></div>"+
			"<div id=\"insert_messagesparlogin\"></div>"+
		"</div>"+
		"<div id=\"prof\" class='col-xs-6'>"+
		"<div id=\"global_content\" >"+
			"<div id=\"profile\">"+
				"<div id=\"firstName1\">Prénom obligatoire</div>"+
				"<div id=\"lastName1\">Nom obligatoire</div>"+
				"<div id=\"usernameSignUp1\">Login obligatoire</div>"+
				"<div id=\"email1\">Email obligatoire</div>"+
				"<div id=\"login_tailleSignUp1\">La taille du login doit être entre 6 et 20 caractères.</div>"+
				"<div id=\"login_existant1\">Veuillez choisir un autre login.</div>"+
				"<form class=\"inscription_form\" method=\"get\" action=\"javascript:saveEdit(this);\">"+
					"<span id=\"firstname_error\"></span>"+
					"    Firstname : <input type=\"text\" class=\"inscription_input firstname1\" name=\"firstname1\" placeholder=\"First name\"> <br /> "+
					"<span id=\"lastname_error\"></span>"+
					"	Lastname :  <input type=\"text\" class=\"inscription_input lastname1\" name=\"lastname1\" placeholder=\"Last name\"> <br /> "+
					"<span id=\"username_error\"></span>" +
					"	Login : <input type=\"text\" class=\"inscription_input usernameSignUp1\" name=\"usernameSignUp1\" placeholder=\"Pick a username\"> <br /> "+
					"<span id=\"email_error\"></span>" +
					"	Email : <input type=\"text\" class=\"inscription_input email1\" name=\"email1\" placeholder=\"Your email\" pattern=\"^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$\">"+
					"<br />"+
					"<button type=\"submit\" id=\"submit\">Save Edits</button>"+
				"</form>"+
			"</div>"+
		"</div>"+
	"</div>"+
	"<div id=\"changeMotPasse\" class='col-xs-6'>"+
		"<div id=\"global_content\">"+
			"<div id=\"profile\">"+
			"<div id=\"Invalid_precedent_password\">Invalid precedent password</div>"+
				"<div id=\"old_password\">old mot de passe obligatoire</div>"+
				"<div id=\"passwordAchanger\">Mot de passe obligatoire</div>"+
				"<div id=\"re_passwordAchanger\">Mot de passe de confirmation obligatoire</div>"+
				"<div id=\"passwordVerifAchanger\">Les deux mots de passe necorrespondent pas</div>"+
				"<div id=\"mdp_tailleSignUpAchanger\">La taille du mot de passe doitêtre entre 6 et 15 caractères.</div>"+
				"<form class=\"inscription_form\" method=\"get\" action=\"javascript:saveChangeMotPasse(this);\">"+
				    "<span id=\"username_error\"></span>" +
					"<input type=\"text\" class=\"inscription_input\" name=\"old_password\" placeholder=\"old password\"> <br /> "+
					"<input type=\"password\" class=\"inscription_input\" name=\"passwordchange\" placeholder=\"Create a password\"> <span id=\"password_error\"></span> "+
					"<br />"+
					"<input type=\"password\" class=\"inscription_input\" name=\"re_passwordchange\" placeholder=\"Confirm your password\"> "+
					"<button type=\"submit\" id=\"submit\">change</button>"+
				"</form>"+
			"</div>"+
		"</div>"+
	"</div>"+
	"<div id=\"listeUtilisateur\" class='col-xs-3'>"+
		"<h3 id=\"msgTilte\"><a href=\"javascript:chargerlistesUtilisateur(this);\">La liste des utilisateurs</h3><span id='liste_util1'></span><span id='liste_util2'></span>"+
	"</div>"+
	"</div>"+
	"<div style='text-align: center'>"+
	"	<a href=\"#\">About Us</a> | <a href=\"#\">Help</a> | 2017 Copyright &copy; <a href=\"#\" title=\"issathink\">All rights reserved, ZinebSite.</a>"+
	"</div>";

	document.body.innerHTML = body;
}

/*####################################################################################################*
 *----------------------------------------Make Main Panel----------------------------------------* 
 *####################################################################################################*/
/*
checkIfConnect();

function checkIfConnect() {
	var session_id = lireCookie(SESSION_ID);
	console.log("oulalla "+session_id);
	if(session_id == "") {
		console.log("Y a pas de cookie");
		return;
	}else{
		console.log("Y a cookie: " + session_id);
		$.ajax({
			url : "http://li328.lip6.fr:8280/titi/isconnected",
			type : "get",
			data : "key=" + session_id,
			dataType : "json",
			success : function(rep) {
				console.log("je suis dans isconnected"+rep.key);
				if(rep.code == 200){
					main(rep.id, rep.login, rep.key, rep.follows);
					chargeMess();
					$('#accueil').hide();
					$('#main').show();
					$('#header').show();
					$('#prof').hide();
					$('#menu').show();
				}else{
					$('#accueil').show();
					$('#main').hide();
					$('#header').hide();
					$('#prof').hide();
					$('#menu').hide();
				}		
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$('#serveur_erreur').show();
			}
		});
	}
}
/*
/****************** Main Function **********************/
function main(id, login, key,follows) {

	env = new Object();
    env.users = new Array();
    env.Messages = new Array();
    env.message_cpt = 0;
    env.erreur_cpt = 0;
    env.recherche = new Object();
    console.log("Start main: " + JSON.stringify(env))
    
	if(id != undefined && login != undefined && key != undefined && follows != undefined) {	
		env.actif=true;
		env.key=key;
		env.login=login;
		env.id=id;
		if(follows.length!=0){
			env.follows=follows.toString().split(',');
		}else{
			env.follows=follows;
		}
		new User(id, login, true);
		$('#connected_name').text(login);
		$('#number_amis').text(env.follows.length);
		showProfile();
		//chargerlistesFriendrs(key);
		chargerMessage(key);
	} else {
		console.log("Erreur : main (probleme avec les parametres)");
		// window.location.href = "index.html";
	}

}


function User(id, login, contact) {

	this.login = login;
	this.id = id;
	if (contact == undefined)
		this.contact = false;
	this.contact = contact;
	env.users[id] = this;
}

function chargerlistesFriends(key) {

	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/ListFriends",
		type : "get",
		data : "key=" + env.key,
		dataType : "json",
		success :  function(rep){
			traiteReponseListesFriends(rep);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			
		}
	});
}

function traiteReponseListesFriends(rep){
	if(rep.listes_friends == ""){
		var a = "<p><a href class ='stats'>#SansAmis</a></p>";
		$('#zone_stats').append(a);
	}else{	
		img="src='img/icons/icon_sup.png'"
		var size = rep.listes_friends.length;
		var s="";
		var b="";
		console.log("taille de liste friends"+size);
		for(var i=0;i<size;i++){
			var am = rep.listes_friends[i] ;
			console.log(am);
			//\"javascript:chargerMessageParLogin(JSON.stringify(am));\"
			s="<a id='img-"+am+"' href=\"#\" onclick=\"javascript:chargerMessageParLogin('"+am+"'); return false;\">"
			s +=am;
			s +="</a>";
			s +="<a id='img-"+am+"' href=\"#\" onclick=\"javascript:supfriend('"+am+"'); return false;\">"
			s +="<img src=\"img/icons/icon_sup.png\"style='width:30px;height:30px;vertical-align:middle' />"
			s +="</a></br>";
			$('#zone_stats1').append(s);
		}
	}
}

function chargerlistesUtilisateur(key) {

	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/listUtilisateurs",
		type : "get",
		data : "key=" + env.key,
		dataType : "json",
		success :  function(rep){
			console.log(rep.listes_friends);
			traiteReponseListesUtilisateurs(rep);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			
		}
	});
}
	
function traiteReponseListesUtilisateurs(rep){
	if(rep.listes_friends == ""){
		var a = "<a href class ='stats'>#tout les utilisateurs son vos Friends</a>";
		$('#liste_util1').append(a);
	}else{	
		var size = rep.listes_friends.length;
		var s="";
		console.log("taille de liste friends"+size);
		for(var i=0;i<size;i++){
			//var am = rep.listes_friends[i] ;
			//console.log(am);
			//\"javascript:chargerMessageParLogin(JSON.stringify(am));\"
			s ="<a id='img1-"+rep.listes_friends[i]+"' href=\"#\" onclick=\"javascript:chargerMessageParLogin('"+rep.listes_friends[i]+"'); return false;\">"
			s +=rep.listes_friends[i]+"</a>"
			s +="<a id='img1-"+rep.listes_friends[i]+"' href=\"#\" onclick=\"javascript:addfriend('"+rep.listes_friends[i]+"'); return false;\">"
			s +="<img src=\"img/icons/icon_ajout.png\"style='width:30px;height:30px;vertical-align:middle' />"
			s +="</a></br>";
			$('#liste_util1').append(s);
			
		}
	}
}


function addfriend(login) {
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/Add",
		type : "get",
		data : "key=" + env.key+ "&loginFriend=" + login,
		dataType : "json",
		success :  function(rep){
			console.log(rep);
			alert(login+" est devenu votre friends");
			$('#img1-'+login).remove();
			$('#img1-'+login).remove();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			
		}
	});
}
function supfriend(login) {
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/remove",
		type : "get",
		data : "key=" + env.key+ "&loginFriend=" + login,
		dataType : "json",
		success :  function(rep){
			console.log(rep);
			$('#img-'+login).remove();
			$('#img-'+login).remove();
			//traiteReponseListesUtilisateurs(rep);
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			
		}
	});
}

function home() {
	//main(env.id,env.login,env.key,env.follows);
	//chargeMess();
	$('#accueil').hide();
	$('#main_content1').hide();
	$('#main').show();
	$('#header').show();
	$('#prof').hide();
	$('#menu').show();
	$('#message_box').show();
	$('#listeUtilisateur').show();
	$('#changeMotPasse').hide();
	$('#motPasseOublie').hide();
}
function forgotten_password(){
	$('#motPasseOublie').show();
	$('#accueil').show();
	$('#main').hide();
	$('#main_content1').hide();
	$('#header').hide();
	$('#prof').hide();
	$('#menu').hide();
	$('#message_box').hide();
	$('#listeUtilisateur').hide();
	$('#changeMotPasse').hide();
}
///////////////////////////profile///////////////////////////////////

function profile() {
	//main(env.id,env.login,env.key,env.follows);
	$('#motPasseOublie').hide();
	$('#accueil').hide();
	$('#main').hide();
	$('#header').show();
	$('#prof').show();
	$('#menu').show();
	$('#message_box').show();
	$('#listeUtilisateur').show();	
	$('#changeMotPasse').hide();
	$('#main_content1').hide();
	
}
function changeMotDepasse() {
	//main(env.id,env.login,env.key,env.follows);
	$('#motPasseOublie').hide();
	$('#accueil').hide();
	$('#main').hide();
	$('#header').show();
	$('#prof').hide();
	$('#menu').show();
	$('#message_box').hide();
	$('#listeUtilisateur').show();
	$('#changeMotPasse').show();	
	$('#main_content1').hide();
}

function showProfile() {
	var key = env.key;
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/profile",
		type : "get",
		dataType : "json",
		data : "key=" + key,
		success : function(rep){
			traiteReponseProfile(rep)
		},	
		error : function(jqXHR, textStatus, errorThrown){
			alert("erreur")
		}
	});
}
function traiteReponseProfile(reponse) {
	if(reponse != undefined) {
		texte = "last_name : " + reponse.last_name + ", " + reponse.first_name + ", " + decode_utf8(reponse.email)+ ", " + reponse.login;
		console.log(texte);
		$("#profile .firstname1").val(reponse.first_name);
		$("#profile .lastname1").val(reponse.last_name);
		$("#profile .email1").val(decode_utf8(reponse.email));
		$("#profile .usernameSignUp1").val(reponse.login);
	}
	
}
function traiteReponseProfileApresUpdate(reponse) {
	console.log("hey "+reponse.login);
	$('#login_existant1').hide();
	if(reponse != undefined) {
		if(reponse.code == 100 && reponse.login!=env.login){
				$('#login_existant1').show();
		}else{		
			$('#connected_name').text(reponse.login);
			$('#accueil').hide();
			$('#main').show();
			$('#header').show();
			$('#prof').hide();
			$('#menu').show();		
		}
	}
}

function saveEdit(obj) {
	document = obj.document;
	var firstname = document.getElementsByName("firstname1")[0].value;
	var lastname = document.getElementsByName("lastname1")[0].value;
	var email = document.getElementsByName("email1")[0].value;
	var login = document.getElementsByName("usernameSignUp1")[0].value;
	if(verifEdit(firstname, lastname, email,login)) {
		console.log("je rentre dans le verifEdit");
		$.ajax({
			url : "http://li328.lip6.fr:8280/titi/updateProfile",
			type : "get",
			dataType : "json",
			data : "id=" + env.id + "&key=" + env.key + "&lastname=" + lastname + "&firstname=" + firstname + "&email=" + email+ "&login=" + login,
			success : function (rep){
				traiteReponseProfileApresUpdate(rep);
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		}); 

	} else {
		console.log("probleme dans saveEdite")
	}
}


function verifEdit(firstname, lastname, email,login) {
	if ((firstname.length == 0) || (firstname == undefined)) {
		$('#lastName1').show();
		return false;
	}
	$('#lastName').hide();

	if ((lastname.length == 0) || (lastname == undefined)) {
		$('#firstName1').show();
		return false;
	}
	$('#firstName1').hide();

	/*********** Login verification **********************/
	if ((login == undefined) || (login.length == 0)) {
		$('#usernameSignUp1').show();
		return false;
	}
	$('#usernameSignUp1').hide();

	if (login.length < 6 || login.length > 20) {
		$('#login_tailleSignUp1').show();
		return false;
	}
	$('#login_tailleSignUp1').hide();

	if ((email == undefined) || (email.length == 0)) {
		$('#email1').show();
		return false;
	}
	$('#email1').hide();
	
	return true;
}


function saveChangeMotPasse(obj) {
	console.log("je rentre dans save");
	document = obj.document;
	var old_password = document.getElementsByName("old_password")[0].value;
	var password = document.getElementsByName("passwordchange")[0].value;
	var re_password = document.getElementsByName("re_passwordchange")[0].value;
	console.log(old_password+" , "+password+"," +re_password);
	if(verifEditChangeMotPasse(old_password, password,re_password)) {
		console.log("je rentre dans le verifEdit change mot de passe");
		$.ajax({
			url : "http://li328.lip6.fr:8280/titi/changePw",
			type : "get",
			dataType : "json",
			data : "&key=" + env.key + "&prec_pw=" + old_password + "&new_pw=" + password,
			success : function (rep){
				traiteReponseChangeMotPasseUpdate(rep);
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		}); 

	} else {
		console.log("probleme dans saveEdite")
	}

}

function verifEditChangeMotPasse(old_password, password,mdpConf) {
	if ((old_password.length == 0) || (old_password == undefined)) {
		$('#old_password').show();
		return false;
	}
	$('#old_password').hide();
	
	if ((password.length == 0) || (password == undefined)) {
		$('#passwordAchanger').show();
		return false;
	}
	$('#passwordAchanger').hide();
	if (password.length < 6 || password.length > 15) {
		$('#mdp_tailleSignUpAchanger').show();
		return false;
	}
	$('#mdp_tailleSignUpAchanger').hide();
	
	if ((mdpConf == undefined) || (mdpConf.length == 0)) {
		$('#re_passwordAchanger').show();
		return false;
	}
	$('#re_passwordAchanger').hide();
	
	if (password != mdpConf) {
		$('#passwordVerifAchanger').show();
		return false;
	}
	$('#passwordVerifAchanger').hide();
		return true;
}

function traiteReponseChangeMotPasseUpdate(reponse) {
	console.log("hey "+reponse.code);
	$('#Invalid_precedent_password').hide();
	if(reponse.code == "200"){
		$('#Invalid_precedent_password').show();
		return false;
	}else{
		$('#accueil').hide();
		$('#main').show();
		$('#header').show();
		$('#prof').hide();
		$('#menu').show();
		$('#listeUtilisateur').show();	
		$('#changeMotPasse').hide();
		$('#motPasseOublie').hide();
		$('#message_box').show();
		$('#main_content1').hide();
	}
}

function sendMotPasse(obj) {
	document = obj.document;
	var emailmotPasseOublie = document.getElementsByName("emailmotPasseOublie")[0].value;
	var loginmotPasseOublie = document.getElementsByName("loginmotPasseOublie")[0].value;
	console.log(emailmotPasseOublie+" , "+loginmotPasseOublie);
	if(verifsendMotPasse(emailmotPasseOublie,loginmotPasseOublie)) {
		console.log("je rentre dans le sendMotPasse");
		$.ajax({
			url : "http://li328.lip6.fr:8280/titi/forgotPassword",
			type : "get",
			dataType : "json",
			data : "login=" + loginmotPasseOublie + "&mail=" + emailmotPasseOublie,
			success : function (rep){
				traiteReponsesendMotPasse(rep);
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		}); 

	} else {
		console.log("probleme dans sendMotPasse")
	}
}

function verifsendMotPasse(emailmotPasseOublie,loginmotPasseOublie) {
	if ((loginmotPasseOublie.length == 0) || (loginmotPasseOublie == undefined)) {
		$('#loginmotPasseOublie').show();
		return false;
	}
	$('#loginmotPasseOublie').hide();
	if ((emailmotPasseOublie.length == 0) || (emailmotPasseOublie == undefined)) {
		$('#mailmotPasseOublie').show();
		return false;
	}
	$('#mailmotPasseOublie').hide();
	return true;
	
}

function traiteReponsesendMotPasse(reponse) {
	$('#Invalid_mail').hide();
	$('#Login_does_not_exist').hide();
	$('#Invalid_precedent_password').hide();
	if(reponse.code == "200"){
		console.log(reponse.code);
		$('#Login_does_not_exist').show();
		return false;
	}
	else if(reponse.code == "150"){
		$('#Invalid_mail').show();
		return false;
	}else{
		window.location.href = "index.html";
	}
	
}


function nblike(login) {
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/countLike",
		type : "get",
		data : "key=" + env.key+ "&loginFriend=" + login,
		dataType : "json",
		success :  function(rep){
			$('#countLike').text(rep.message);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			
		}
	});
}

function nbmessage(login) {
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/countMessage",
		type : "get",
		data : "key=" + env.key+ "&loginFriend=" + login,
		dataType : "json",
		success :  function(rep){
			$('#countmessage').text(rep.message);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			
		}
	});
}

////////////////////////////////////////fin profile/////////////////////////
///////////////////////////////////////////////////////////////////////////

//function Message(id, user, texte, date, score, liked) {
function Message(id, user, texte, date,score,liked,comment) {
	this.id=id;
	this.user=user;
	this.texte=texte;
	this.date=date;
	this.score=score;
	this.liked=liked;
	this.comment=comment;

	this.num=env.message_cpt;
	env.Messages[env.message_cpt++]=this;
	
} 

function envoiMessage(obj) {
	document = obj.document;
	var message = document.getElementsByName("message")[0].value;
	message = encode_utf8(message);
	console.log("Sending message : " + message);
	if(checkMessage(message)) {
		$.ajax({
			url : "http://li328.lip6.fr:8280/titi/Message",
			type : "get",
			data : "key=" + env.key + "&message=" + message,
			dataType : "json",
			success : function(rep){
				envoiMessageOK(rep);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("probleme ");
			}
		});
	} else {
		console.log(" probleme connexion.");
	}
}
function checkMessage(texte) {

	if(texte === undefined || texte.length === 0)
		return false;
	return true;

}

function envoiMessageOK(reponse) {
	if(reponse != undefined) {
		user = env.users[env.id];	
		message = new Message(reponse.id_message, user, decode_utf8(reponse.message), reponse.date, reponse.score, parseInt(reponse.liked, 10),"");
		var s = message.getHtml(message);
		$('#insert_messages').prepend(s).fadeIn("slow");;
		$('#message_box #message').val('');//pour vider le textarea avec le twite 
	}
}

Message.prototype.getHtml = function(com) {
	img = "src='img/ic_like.png'";
	if(this.liked == 1)
		img = "src='img/ic_liked.png'";
	
	var s = "<div class='message' id='message_" + this.id + "'><div class='message_text'>"
	+ this.texte + "</div>"
	+"<div class='message_info'><span class='message_nb_like'>    " + this.score 
	+ "</span><a href='#' onclick=\"likeMessage('" + this.num 
	+ "'); return false;\"><img id='img-" + this.id + "' " + img + " style='width:30px;height:30px;vertical-align:middle' />"
	//+	"</a><span class='message_date'>" + this.user.login + ", </span><span id='date'>" + this.date+ "</span>";
	+ "<a href='#' onclick=\"chargerComments('" + this.num 
	+ "'); return false;\">list comments</a>"
	+"<div style='display: none' id='form-" + this.id + "'><form class=\"message_form\" method=\"get\"	action=\"javascript:commentMessages(this,'"+this.id+"');\">"
    +"<textarea class=\"box\" name='message' id='message-"+this.id + "'></textarea>"
    +"<input type=\"submit\" value=\"comment\" id=\"message_button\" />"
    +"</form></div></div><div style='display: none' id='z_stats-"+ this.id + "'><span id='z_stats-"+ this.id + "'></span></div><hr>";
	/*
	deb = "<a id='" + this.num -" 
		  + "' href='#' onclick=\"addRemoveFriend('" + this.num + "'); return false;\"><span class='message_foll'>";
	fin = "</span></a></div></div>";

    if(env.id == this.user.id){
    	s += deb + "Posted by Me" + fin;
    } else if(this.user.contact == true){
    	s += deb + "Unfollow " + this.user.login + fin;
    }
    else{
    	s += deb + "Follow " + this.user.login + fin;
    }
    */
    return s;

};

function likeMessage(obj) {
	id_message = env.Messages[obj].id;
	console.log("id message "+id_message);
	if(env.Messages[obj].liked == 0) {
		$.ajax({
			url : "http://li328.lip6.fr:8280/titi/LikeMessage",
			type : "get",
			data : "key=" + env.key + "&id_message=" + id_message,
			dataType : "json",
			success : function(reponse) {
				
				Message.traiteReponseLikeJSON(reponse, obj);
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	} else {
		$.ajax({
			url : "http://li328.lip6.fr:8280/titi/UnLikeMessage",
			type : "get",
			data : "key=" + env.key + "&id_message=" + id_message,
			success : function(reponse) {
				Message.traiteReponseUnlikeJSON(reponse, obj);
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	}
}

Message.traiteReponseLikeJSON = function(reponse, id) {

	env.Messages[id].score = parseInt(parseInt(env.Messages[id].score, 10) + 1);
	env.Messages[id].liked = 1;
	
	str = "#message_" + env.Messages[id].id + " > .message_info >.message_nb_like";
	str2 = "#message_" + env.Messages[id].id + " #img-"+env.Messages[id].id;
	var src = "img/ic_liked.png";
	console.log("le nombre de like : "+env.Messages[id].score);
	$(str).text(env.Messages[id].score);
	$(str2).attr('src', src);

};

Message.traiteReponseUnlikeJSON = function(reponse, id) {
	
	env.Messages[id].score = parseInt(parseInt(env.Messages[id].score, 10) - 1);
	env.Messages[id].liked = 0;
	str1 = "#message_" + env.Messages[id].id + " .message_nb_like";
	str2 = "#message_" + env.Messages[id].id + " #img";
	var src = "img/ic_like.png";
	$(str1).text(env.Messages[id].score);
	$(str2).attr('src', src);

};
function chargerMessage(key) {
	console.log("je rentre de charger message");
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/ListMassage",
		type : "get",
		data : "key=" + key,
		dataType : "json",
		success : function (rep){
			$('#message_box').show();
			Message.traiteReponseJSON(rep);
		},
		error : function(jqXHR, textStatus, errorThrown) {
		}
	});
	console.log("on va charger les messages");
}

Message.traiteReponseJSON = function(json_text) {
	var coms = "";
	if(json_text.length == undefined || json_text.length < 0) {
		console.log("Something went wrong...");
		//logout();
	}
	if(json_text.length == 0) {
		console.log("Y a pas de messages à afficher.");
	}
	for (var i=0; i<json_text.length; i++){
		val = json_text[i];
		user = new User(val.id_user, val.login_user);
		com = new Message(val.id_message, user, decode_utf8(val.message), val.date, val.score, val.liked);
		console.log("je suis la "+com.num);
		coms += com.getHtml(coms);
	}
	$('#insert_messages').prepend(coms).fadeIn("slow");

};
Message.traiteReponseJSONComments = function(json_text,id) {
	var coms = "";
	var id_mess="";
	if(json_text.length == undefined || json_text.length < 0) {
		console.log("Something went wrong...");
		//logout();
	}
	
	if(json_text.length == 0) {
		console.log("Y a pas de comments à afficher.");
	}
	console.log("le nombre de commentaire "+json_text.length);
	for (var i=0; i<json_text.length; i++){
		val = json_text[i];
		id_mess=val.id_commentaire;
		console.log("hey"+val.id_commentaire);
		var str = val.texte+" Commenter par : "+val.login+"</br><hr>";
		coms+=str;
		
	}
	$('#z_stats-'+id_mess).append(coms).fadeIn("slow");
	$('#message-'+id_mess).val('');
	
};

function commentMessages(obj,id_message) {
	console.log("id_message"+id_message)
	document = obj.document;
	//var comment = document.getElementsById("message-'" + id_message + "'")[0].value;
	var comment = $('#message-'+ id_message).val();
	comment = encode_utf8(comment);
	console.log("comment : " + comment);
	if(checkMessage(comment)) {
		$.ajax({
			url : "http://li328.lip6.fr:8280/titi/comment",
			type : "get",
			data : "key=" + env.key + "&id_message=" + id_message + "&texte=" + comment,
			dataType : "json",
			success : function(rep){
				console.log(rep);
				var coms = comment+" Commenter par : "+rep.login+"</br><hr>";
				$('#z_stats-'+id_message).append(coms).fadeIn("slow");
				$('#message-'+id_message).val('');
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("probleme ");
			}
		});
	} else {
		console.log(" probleme connexion.");
	}

}
function chargerComments(obj) {
	id_message = env.Messages[obj].id;
	console.log("id de commentaire"+id_message)
	console.log("je rentre pour charger les commentaire");
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/listComments",
		type : "get",
		data : "id_message=" + id_message,
		dataType : "json",
		success : function (rep){
			console.log("1005"+id_message);
			Message.traiteReponseJSONComments(rep,obj);
			$('#form-' + id_message).show();
			$('#z_stats-'+id_message).show();
		},
		error : function(jqXHR, textStatus, errorThrown) {
		}
	});
	console.log("on va charger les commentaire");
}

function chargerMessageParLogin(login) {
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/listMassageParLogin",
		type : "get",
		data : "login=" + login,
		dataType : "json",
		success : function (rep){
			$('#message_box').hide();
			$('#insert_messages').hide();
			Message.traiteReponseJSON1(rep);
		},
		error : function(jqXHR, textStatus, errorThrown) {
		}
	});
	console.log("on va charger les messages");
}

Message.traiteReponseJSON1 = function(json_text) {
	var coms = "";
	if(json_text.length == undefined || json_text.length < 0) {
		console.log("Something went wrong...");
		//logout();
	}
	if(json_text.length == 0) {
		console.log("Y a pas de messages à afficher.");
	}
	for (var i=0; i<json_text.length; i++){
		val = json_text[i];
		user = new User(val.id_user, val.login_user);
		com = new Message(val.id_message, user, decode_utf8(val.message), val.date, val.score, val.liked);
		coms += com.getHtml(coms);
	}
	$('#insert_messagesparlogin').prepend(coms).fadeIn("slow");
	//console.log("ddddd"+this.id);
	$('#z_stats-'+this.id).append(coms).fadeIn("slow");
	$('#message-'+this.id).val('');

};
function encode_utf8(s) {
	  return unescape(encodeURIComponent(s));
	}

function decode_utf8(s) {
	  return decodeURIComponent(unescape(s));
}

/*####################################################################################################*
 *----------------------------------------Traitement de login----------------------------------------* 
 *####################################################################################################*/
/*
 * connexion(document)
 *
 * Elle fait appel à verifForm(), si tout est ok elle fait appel 
 * à connecte() pour connecter l'utilisation. 
 *
 */
function connexion(obj) {
	document = obj.document;
	var login = document.getElementsByName("login")[0].value;
	var mdp = document.getElementsByName("pwd")[0].value;
	var ok = verifForm(login, mdp);
	if (ok) {
		connecte(login, mdp);
	}
}

/*
 * verifForm(login, mot_de_passe)
 *
 * Cette fonction verifies si le login et le mot de passe de 
 * sont bien saisis. 
 *
 */
function verifForm(login, mdp) {

	/*********** Login verification **********************/
	if ((login == undefined) || (login.length == 0)) {
		$('#login_obligatoire').show();
		return false;
	}
	$('#login_obligatoire').hide();

	/************* Mot de passe verification **************/
	if ((mdp == undefined) || (mdp.length == 0)) {
		$('#mdp_obligatoire').show();
		return false;
	}
	$('#mdp_obligatoire').hide();

	return true;

}

function connecte(login, password) {
	$('#serveur_erreur').hide();
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/authenticate",
		type : "get",
		data : "login=" + login + "&mdp=" + password,
		dataType : "json",
		success : function(rep) {
			traiteReponseConnexion(rep);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#serveur_erreur').show();
		}
	});
}

function traiteReponseConnexion(reponse) {
	console.log("je suis la connexion "+reponse.code);
	$('#deja_connecter').hide();
	$('#erreur_login').hide();
	$('#main').hide();
	$('#erreur_mdp').hide();
	$('#header').hide();
	$('#menu').hide();
	if(reponse.code == 200){
		//main(reponse.id,reponse.login,reponse.key,reponse.follows);
		//chargeMess();
		//console.log("id de session"+reponse.key);
		//creerCookie(SESSION_ID, reponse.key);
		$('#accueil').hide();
		$('#main').show();
		$('#header').show();
		$('#menu').show();
		$('#listeUtilisateur').show();
		$('#changeMotPasse').hide();
		$('#main_content1').hide();
		$('#motPasseOublie').hide();
	}else if(reponse.code == 150) {
		$('#erreur_mdp').show();
	}
	else if(reponse.code == 100) {
		$('#erreur_login').show();
	}else {
		main(reponse.id,reponse.login,reponse.key,reponse.follows);
		//chargeMess();
		//creerCookie(SESSION_ID, reponse.key);
		$('#accueil').hide();
		$('#main').show();
		$('#header').show();
		$('#menu').show();
		$('#listeUtilisateur').show();	
		$('#changeMotPasse').hide();
		$('#main_content1').hide();
		$('#motPasseOublie').hide();
		
	}
}

/*####################################################################################################*
 *----------------------------------------Traitement de inscription -----------------------------------------* 
 *####################################################################################################*/
function creation(obj) {
	document = obj.document;
	var prenom = document.getElementsByName("firstName")[0].value;
	var nom = document.getElementsByName("lastName")[0].value;
	var login = document.getElementsByName("usernameSignUp")[0].value;
	var email = document.getElementsByName("email")[0].value;
	var mdp = document.getElementsByName("password")[0].value;
	var mdpConf = document.getElementsByName("re_password")[0].value;
	
	var ok = verifChamps(mdp, mdpConf, prenom, nom, email, login);
	if (ok) {
		createUser(nom, prenom, login, email, mdp);
	}
}

function verifChamps(mdp, mdpConf, prenom, nom, email, login) { 

	if ((prenom.length == 0) || (prenom == undefined)) {
		$('#lastName').show();
		return false;
	}
	$('#lastName').hide();

	if ((nom.length == 0) || (nom == undefined)) {
		$('#firstName').show();
		return false;
	}
	$('#firstName').hide();

	/** ********* Login verification ********************* */
	if ((login == undefined) || (login.length == 0)) {
		$('#usernameSignUp').show();
		return false;
	}
	$('#usernameSignUp').hide();

	if (login.length < 6 || login.length > 20) {
		$('#login_tailleSignUp').show();
		return false;
	}
	$('#login_tailleSignUp').hide();

	if ((email == undefined) || (email.length == 0)) {
		$('#email').show();
		return false;
	}
	$('#email').hide();

	if ((mdp == undefined) || (mdp.length == 0)) {
		$('#password1').show();
		return false;
	}
	$('#password1').hide();

	if (mdp.length < 6 || mdp.length > 15) {
		$('#mdp_tailleSignUp').show();
		return false;
	}
	$('#mdp_tailleSignUp').hide();

	if ((mdpConf == undefined) || (mdpConf.length == 0)) {
		$('#password2').show();
		return false;
	}
	$('#password2').hide();

	if (mdp != mdpConf) {
		$('#passwordVerif').show();
		return false;
	}
	$('#passwordVerif').hide();

	return true;

}

function createUser(nom, prenom, login, email, mdp) {
	$('#serveur_erreur').hide();
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/souscrire",
		type : "get",
		data : "login=" + login + "&nom=" + nom + "&prenom=" + prenom + "&mdp=" +mdp+ "&email=" + email,
		dataType : "json",
		success : function(rep) {
			traiteReponseCreation(rep, login, mdp);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#serveur_erreur').show();
		}
	});
}

function traiteReponseCreation(reponse, login, mdp) {
	$('#login_existant').hide();
	if(reponse.code == 100) {
		$('#login_existant').show();
	} else {
		connecte(login, mdp);
	}
}

/*####################################################################################################*
 *-----------------------------------------Traitement logout------------------------------------------* 
 *####################################################################################################*/


function logout() {
	
	cle = env.key;
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/logout",
		type : "get",
		data : "key=" + cle,
		dataType : "json",
		success : function(rep){
			traiteReponseDeconnexion(rep);
		},
		error : function(jqXHR, textStatus, errorThrown) {
		}
	});
}

function traiteReponseDeconnexion(rep) {
	//supprimerCookie(SESSION_ID);
	console.log("debut de la supression env: " + JSON.stringify(env));
	env.actif = false;
	env = new Object();
	console.log("fin de la supression env: " + JSON.stringify(env));
	$('#insert_messages').text("");
	$('#zone_stats1').text("");
	$('#zone_stats2').text("");
	window.location.href = "index.html";
}


