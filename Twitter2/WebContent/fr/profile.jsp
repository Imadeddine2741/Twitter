<html>

<head>
        <%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-language" content="fr" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="robots" content="index,follow">
        <meta name="viewport" content="width=device-width , initial-scale=1.0">
        <title>Zineb - Home</title>
        <link rel="stylesheet" href="css/index.css" type="text/css">
        <link rel="shortcut icon" href="img/icons/favicon.ico">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/themes/smoothness/jquery-ui.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
        <script src="js/inscription.js"></script>
        <script src="js/login.js"></script>
        <script src="js/mainIndex.js"></script>
        <script src="js/deconnexion.js"></script>
</head>
<body class="body">
	<script type="text/javascript">
        $(document).ready(function() {
        	function go() {
               <%
            		String id = request.getParameter("id");
                    String login = request.getParameter("login");
            		String key = request.getParameter("key");
            		String follows = request.getParameter("follows");
            		if((id != null) && (login != null) && (key != null)){
            			out.println("main('" + id + "','" + login + "','" + key + "' , '"+ follows + "');");
            		} else {
            			out.println("main();");
                    }
        	   %>
            }
            $(go());
        });		
	</script>
        <header class="mainheader">
                <nav>
                    <div id="title">
                        <a href="#" onclick="javascript:home(); return false;"><img src="img/logo.png" class="title" alt="logo" width="100px" height="60px"></a>
                    </div>
                    <div id="search_box">
                        <form class="form-wrapper"  method="get" action="javascript:rechercheCommentaire(this);">
	                       <input type="text" placeholder="Search ..." name="search" >
	                       <button type="submit">Go</button>
                        </form>
                    </div>
                    <div id="small_menu">
                        <input type="checkbox" name="search_friend" value="1" id="checkbox"><span style="{font-size: 0.8em}"> Friends only</span>
                    </div>       
                    <div id="button_logout">
                        Hi, <span id="connected_name"></span>
                        <a href="#" onclick="logout(); return false;">Disconnect</a>
                    </div>  
                </nav>
         </header>    
        <div id="main_content1">
                <div id="menu">
                  	<div id="menu1" class="menu1">
                        <a href="javascript:profile();"><img style="width:40px;height:40px;vertical-align:middle" src="img/ic_profile.png"/></a>
                        <a href="javascript:profile();">Profile</a>
                    </div>
                    <div id="menu3" class="menu3">
                   	    <a href="javascript:chargerlistesFriends(this);"><img src="img/ic_mine.png" style="width:40px;height:40px;vertical-align:middle"/></a>
                        <a href="javascript:chargerlistesFriends(this);">Number of friends : </a><span id="number_amis"></span>
                        <span id="zone_stats"></span>
                    </div>
                </div>
                <div id="global_content">
                	<div id="profile"> 
                		<div id="firstName">Prénom obligatoire</div>
        				<div id="lastName">Nom obligatoire</div>
        				<div id="usernameSignUp">Login obligatoire</div>
        				<div id="email">Email obligatoire</div>
        				<div id="login_tailleSignUp">La taille du login doit être entre 6 et 20 caractères.</div>
                   		<div id="login_existant">Veuillez choisir un autre login.</div>
                        <form class="inscription_form" method="get" action="javascript:saveEdit(this);">
                            <span id="firstname_error"></span>
                           Firstname : <input type="text" class="inscription_input firstname" name="firstname" placeholder="First name">
                                <br/>
                            <span id="lastname_error"></span>
                            Lastname : <input type="text" class="inscription_input lastname" name="lastname" placeholder="Last name">
                                <br/>
                            <span id="username_error"></span>
                       		Login <input type="text" class="inscription_input usernameSignUp" name="usernameSignUp" placeholder="Pick a username">
                           	 <br/>
                            <span id="email_error"></span>
                            Email : <input type="text" class="inscription_input email" name="email" placeholder="Your email" pattern="([a-z]|[A-Z]|[0-9]){4,}\@([a-z]|[A-Z]|[0-9]){3,}\.([a-z]|[A-Z]){2,3}" >
                                <br/>
                            <button type="submit" id="submit">Save Edits</button>
                        </form>
                    </div>
               </div>
        </div>
        	<div id="footer">
                	<a href="#">About Us</a> | 
                	<a href="#">Help</a> | 
               	 2017 Copyright &copy; <a href="#" title="imadthink">All rights reserved, Zineb Site.</a>
        	</div>
</body>
</html>