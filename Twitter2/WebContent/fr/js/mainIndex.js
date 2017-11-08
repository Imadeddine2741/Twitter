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

/****************** Main Function **********************/
function main(id, login, key,follows) {

	env = new Object();
    env.users = new Array();
    env.Messages = new Array();
    env.message_cpt = 0;
    env.erreur_cpt = 0;
    env.recherche = new Object();
    
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
		//chargerMessage(key, id, login);
	} else {
		console.log("Erreur : main (probleme avec les parametres)");
		// window.location.href = "index.html";
	}

}
function chargeMess(){
	chargerMessage(env.key, env.id,env.login);
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
		var size = rep.listes_friends.length;
		var s = "";
		for(var i=0;i<size;i++){
			var am = rep.listes_friends[i] ;
			s + "<p><a href>";
			s +=JSON.stringify(am)
			s +="</a></p>";
		}
		$('#zone_stats').append(s);
	}
}

function home() {
	//main(env.id,env.login,env.key,env.follows);
	chargeMess();
	$('#accueil').hide();
	$('#main').show();
	$('#header').show();
	$('#prof').hide();
	$('#menu').show();
	
}
///////////////////////////profile///////////////////////////////////

function profile() {
	//main(env.id,env.login,env.key,env.follows);
	$('#accueil').hide();
	$('#main').hide();
	$('#header').show();
	$('#prof').show();
	$('#menu').show();
	
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
		$("#profile .firstname").val(reponse.first_name);
		$("#profile .lastname").val(reponse.last_name);
		$("#profile .email").val(decode_utf8(reponse.email));
		$("#profile .usernameSignUp").val(reponse.login);
	}
	
}
function traiteReponseProfileApresUpdate(reponse) {
	$('#login_existant').hide();
	if(reponse != undefined) {
		if(reponse.code == 100 && reponse.login!=env.login){
				$('#login_existant').show();
		}else{
			//window.location.href = "main.jsp?id=" + env.id + "&login="+ reponse.login + "&key=" + env.key+"&follows="+env.follows;
			//main(env.id,env.login,env.key,env.follows);
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
		
	}

}

function verifEdit(firstname, lastname, email,login) {

	if ((firstname.length == 0) || (firstname == undefined)) {
		$('#lastName').show();
		return false;
	}
	$('#lastName').hide();

	if ((lastname.length == 0) || (lastname == undefined)) {
		$('#firstName').show();
		return false;
	}
	$('#firstName').hide();

	/*********** Login verification **********************/
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
	
	return true;
	

}


////////////////////////////////////////fin profile/////////////////////////


///////////////////////////////////////////////////////

//function Message(id, user, texte, date, score, liked) {
function Message(id, user, texte, date,score,liked) {
	this.id=id;
	this.user=user;
	this.texte=texte;
	this.date=date;
	this.score=score;
	this.liked=liked;

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
		console.log("Message envoie OK: " + reponse);
		user = env.users[env.id];	
		message = new Message(reponse.id_message, user, decode_utf8(reponse.message), reponse.date, reponse.score, parseInt(reponse.liked, 10));
		console.log(message);
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
			+ this.texte + "</div><div class='message_info'><span class='message_nb_like'>" + this.score 
			+ "</span><a href='#' onclick=\"likeMessage('" + this.num 
			+ "'); return false;\"><img id='img' " + img + " style='width:30px;height:30px;vertical-align:middle' />"
			+	"</a><span class='message_date'>" + this.user.login + ", </span><span id='date'>" + this.date + "</span>";

	deb = "<a id='" + this.num 
		  + "' href='#' onclick=\"addRemoveFriend('" + this.num + "'); return false;\"><span class='message_foll'>";
	fin = "</span></a></div></div>";

    if(env.id == this.user.id)
    	s += deb + "Posted by Me" + fin;
    else if(this.user.contact == true)
    	s += deb + "Unfollow " + this.user.login + fin;
    else
    	s += deb + "Follow " + this.user.login + fin;

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
	
	str = "#message_" + env.Messages[id].id + " .message_nb_like";
	str2 = "#message_" + env.Messages[id].id + " #img";
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
function chargerMessage(key, id, login) {
	console.log("je rentre de charger message");
	$.ajax({
		url : "http://li328.lip6.fr:8280/titi/ListMassage",
		type : "get",
		data : "key=" + key,
		dataType : "json",
		success : Message.traiteReponseJSON,
		error : function(jqXHR, textStatus, errorThrown) {
		}
	});
	console.log("on va charger les messages");

}


Message.traiteReponseJSON = function(json_text) {

	console.log(json_text);
	console.log("Nombre de messages retournes : " + json_text.length);
	var coms = "";
	if(json_text.length == undefined || json_text.length < 0) {
		console.log("Something went wrong...");
		//logout();
	}

	if(json_text.length == 0) {
		console.log("Y a pas de messages à afficher.");
	}
	console.log()
	for (var i=0; i<json_text.length; i++){
		val = json_text[i];
		user = new User(val.id_user, val.login_user);
		com = new Message(val.id_message, user, decode_utf8(val.message), val.date, val.score, val.liked);
		console.log("je suis la "+com.num);
		coms += com.getHtml(coms);
	}
	$('#insert_messages').prepend(coms).fadeIn("slow");

};
function encode_utf8(s) {
	  return unescape(encodeURIComponent(s));
	}

function decode_utf8(s) {
	  return decodeURIComponent(unescape(s));
}
