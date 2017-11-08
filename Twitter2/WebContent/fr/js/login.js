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
		main(reponse.id,reponse.login,reponse.key,reponse.follows);
		chargeMess();
		console.log("id de session"+reponse.key);
		creerCookie(SESSION_ID, reponse.key);
		$('#accueil').hide();
		$('#main').show();
		$('#header').show();
		$('#menu').show();
	}else if(reponse.code == 150) {
		$('#erreur_mdp').show();
	}
	else if(reponse.code == 100) {
		$('#erreur_login').show();
	}else {
		main(reponse.id,reponse.login,reponse.key,reponse.follows);
		chargeMess();
		creerCookie(SESSION_ID, reponse.key);
		$('#accueil').hide();
		$('#main').show();
		$('#header').show();
		$('#menu').show();
		
	}
}





