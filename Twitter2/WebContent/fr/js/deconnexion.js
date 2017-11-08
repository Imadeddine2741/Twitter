/*
 * creation(document)
 *
 * Elle fait appel à verifChamps(), si tout est ok elle fait appel 
 * à connecte() pour connecter l'utilisateur. 
 *
 */


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
	supprimerCookie(SESSION_ID);
	console.log("apres la suprission "+SESSION_ID)
	env.actif = false;
	env = {};
	env.users = [];
	$('#accueil').show();
	$('#main').hide();
	$('#header').hide();
	$('#menu').hide();
	$('#prof').hide();
}