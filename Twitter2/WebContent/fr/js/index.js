var SESSION_ID = "my_session_id";

/********les cookies function *******/ 
function creerCookie(nom,valeur){
	//di les jours ont bien été définis
		//On crée un objet date stockant la date actuelle
		var date = new Date();
		//on définit la date d'expiration du cookie
		date.setTime(date.getTime()+(30*60*1000));
		//On met la date au bon format pour cookie
		var exp = '; expires='+date.toGMTString();

	//Si les jours n'ont pas été définis, pas besoin de calcul
	document.cookie = nom+'='+valeur+exp+';path=/';
}

function lireCookie(nom){
	//On recupère le nom du cookie et le signe '='
	var nomEtEgal = nom + '=';
	//récupère tous les cookies dans un tableau 
	var cTableau = document.cookie.split(';');
	//parcourt le tabeau créé
	for(var i=0;i<cTableau.length;i++){
		//On récupère tous les noms et valeur des cookies
		var c = cTableau[i];
		//On supprime les espaces potentielles dans c jusqu'à tomber sur le nom d'un cookie
		while(c.charAt(0) == ' '){
			c = c.substring(1,c.length);
		}
		if(c.indexOf(nomEtEgal)==0){
			return c.substring(nomEtEgal.length,c.length);
		}	
	}
	//Si nom du cookie non trouvé, il n'existe pas 
	return null;
}

function supprimerCookie(nom){
	/*On donne le nom du cookie à supprimer, on utilise creerCookie() pour indiquer une date d'expiration passée*/
	creerCookie(nom,'',-1);
}