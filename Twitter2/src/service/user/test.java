package service.user;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.DBObject;

import servicetools.BCrypt;
import servicetools.Tools;

public class test {
	
	public static void  main(String [] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException{
	
		//String email="JJJJ";String  mot_de_passe="hahagafda";
		//String login="titi";String nom ="coucou";String prenom="blabla";
		//String key="titi";
		//JSONObject objCreate=CreateUserService.createUser(login,mot_de_passe,nom,prenom,email);
		//System.out.println(objCreate.toString());
		//JSONObject objAthenticate=AuthenticateUserService.authenticateUser("coucouc","imadeddine");
		//System.out.println(objAthenticate.toString());	
		//JSONObject objLogout=LogoutService.logout("d645c7b7-77c7-41a2-84af-a0fb60a1c0251487081582544");
		//System.out.println(objLogout.toString());	
		
		//long toto =Tools.getDate("317e4720-b3d4-4ce9-bd6e-7cbfd5a8b1eb1486324424172");
		//System.out.println(toto);	
		//int id_user =Tools.getId("a0a4aee3-5960-4f6b-9a26-6c9efd006ac81492033035441");
		//System.out.println(id_user);	
		/*
		JSONObject objAddFriends=FriendsService.addFriends("d645c7b7-77c7-41a2-84af-a0fb60a1c0251487081582544", login);
		System.out.println(objAddFriends.toString());
		JSONObject objremFriends=RemoveFriendService.removeFriend("d645c7b7-77c7-41a2-84af-a0fb60a1c0251487081582544", login);
		System.out.println(objremFriends.toString());
		JSONObject 	listFriends=ListFriendsService.ListFriends("d645c7b7-77c7-41a2-84af-a0fb60a1c0251487081582544");
		System.out.println(listFriends.toString());
*/
		//JSONObject objMassage=Tools.addMessages("bbd732e6-aef6-4fd5-82af-2487f388aca51491761191130", "salut");
		//System.out.println(objMassage.toString());
		
		//JSONObject objSupMessage=RemoveMassagesService.removeMassage("69159095-d336-4b1d-932f-29d495ec06121488634859423", "salut tout le monde ");
		//System.out.println(objSupMessage.toString());
		
		//List<JSONObject> objListMessages=Tools.listesMassages("d9fd9179-3ead-4f59-a3c8-1b67f50bf7e01492699306962");
		//System.out.println(objListMessages.toString());
	   // JSONObject objListMessages=Tools.listesMassages("cf49499c-e7a1-4278-a107-4193c30d68bd1492692981162");
	   // System.out.println(objListMessages.toString());
	    
		//String mail=Tools.email("7a3e6309-772d-4fdd-9757-7db74a1b4b231492283302977");
		//System.out.println(mail);
		/*
		String prenom=Tools.first_name("97339e12-afda-4f8e-af28-eaa4d02ea04d1491479326205");
		System.out.println(prenom);
		String nom=Tools.last_name("97339e12-afda-4f8e-af28-eaa4d02ea04d1491479326205");
		System.out.println(nom);
		 */ 
		//boolean mail=servicetools.Tools.updateProfile("1","1b60e4cf-ed22-4e29-979c-d7bb0fe91b041492082312012","tototititotot","imadeddine","imadeddine.rahmani@gmail.com","bibibibi");
		//System.out.println("loooool "+mail);
		//e0627824-2ee4-47a0-93cb-26ddbe4819eb1492506544181
		//JSONObject toto1=Tools.addMessages("110aec93-e03b-41e8-8c7a-6ef1a013ef8e1492600665328","mdr");
		//System.out.println(toto1);
		
		//List<JSONObject> objListMessages=Tools.listesMassages("87596604-a87d-4759-8341-72cd178acf6a1492681998866");
		//System.out.println(objListMessages.toString());
		
		//JSONObject toto=Tools.CommentMessages("bd4a4716-b1d9-49ce-af97-4af527f82c471492612890627","8cfbbdaa-d317-4518-872c-d7c22eec94cc1492604309058", "hahahahha");
		//System.out.println(toto);
		//List<JSONObject> objListCommentMessages=Tools.listesCommentsParMassages("7a86c94e-943e-44b4-8451-ae79a2bf54461492606750774");
		//System.out.println(objListCommentMessages.toString());
		//boolean vraioufaux=Tools.supMessage("291202ee-6098-4fbe-8492-f162c3723f031492592486872", "8a54ff97-fcd7-4ae9-b805-db1a3b3b114c1492593679676");
		//System.out.println(vraioufaux);
		//boolean vraioufauxC=Tools.supComment("291202ee-6098-4fbe-8492-f162c3723f031492592486872", "1a42fa99-9cd4-4a38-bd1a-f01100f1f6ca1492594986402");
		//System.out.println(vraioufauxC);
		//List<JSONObject> objListMessagesparlogin=Tools.listesMassagesParlogin("coucouc");
		//System.out.println(objListMessagesparlogin.toString());
		//String nom=Tools.likeMessage("33249f88-9ae6-4544-a258-850545e8d4f91491778774759","1586b40c-bd86-4cd1-9c5d-a88ba3d6eecd1491765444868");
		//System.out.println(nom);
		//int cpt=Tools.getScoreLike("1586b40c-bd86-4cd1-9c5d-a88ba3d6eecd1491765444868");
		//System.out.println(cpt);
		
		//String loginLike =Tools.getLikeLogin("1586b40c-bd86-4cd1-9c5d-a88ba3d6eecd1491765444868");
		//System.out.println(loginLike);
		/*
		ForgotPasswordService ft=new ForgotPasswordService();
		String mdp =ft.forgotPassword("imadeddine.rahmani@gmail.com", "imadeddine1");
		System.out.println(mdp);
		
		
		BCrypt bc=new BCrypt();
		String mdp =bc.hashpw("imadeddine", BCrypt.gensalt());
		System.out.println(mdp);
		String mdp=servicetools.Tools.getMDP("imadeddine1");
		BCrypt bc=new BCrypt();
		boolean pass_user = bc.checkpw("imadeddine",mdp);
		System.out.println(pass_user);
		
		
		
		ChangePwService ch=new ChangePwService();
		String mdp1 =ch.changePw("f1f1c556-628f-4d38-9e28-a1d051077d521492458085474", "imadeddine", "tototiti");
		System.out.println(mdp1);
		*/
		
		//List<String> objListMessages=Tools.listUtilisateurs("92f2f7a3-d20e-46cb-bba9-ee4e83bc01e41492675621787");
		//System.out.println(objListMessages.toString());
		//int nblike=Tools.countLike("toto");
		//System.out.println(nblike);
		//int nbmessages=Tools.counMessage("imadeddine1");
		//System.out.println(nbmessages);
		
		JSONObject objAddFriends=FriendsService.addFriends("d645c7b7-77c7-41a2-84af-a0fb60a1c0251487081582544", "i");
		System.out.println(objAddFriends.toString());
		
	}	
}

