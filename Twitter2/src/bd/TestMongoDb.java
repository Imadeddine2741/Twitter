package bd;


import java.util.UUID;

import servicetools.Functions;
import servicetools.Tools;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class TestMongoDb {
	public static void main(String []args ){
		
		DB db=null;
	    
		DBCollection coll;
		try {
			
			db = Database.getMongoDB();
			//coll=db.getCollection("messages");
			//coll=db.getCollection("like");
			//coll=db.getCollection("commentaires");
			BasicDBObject obj=new BasicDBObject();

			String id=UUID.randomUUID()+""+Functions.getNowMillis();
			//BasicDBObject nb = new BasicDBObject();
			//obj.put("id", id);
			//obj.put("ton_obh", nb);
			/*
			obj.put("Autheur_id", 328);
			obj.put("Autheur_id", 328);
			obj.put("text", "premier commentaire");
			obj.put("Author_login", "toto");
			obj.put("date", Functions.getTimestamap());
			col
			DBCursor curs = coll.find(obj);
			while(curs.hasNext()) {
				DBObject o = curs.next();
				System.out.println(o);
			}
			*/
			//coll.remove(obj);
			//System.out.println(Tools.addComments("b57173b7-c471-4932-87d0-0de125c6d2b01488738022783","salut comment allez-vous mes ami(e)s"));
			//System.out.println(Tools.getLikeLogin("d12d5f51-d274-4314-92d6-fb5a5e779f6f1488738038233"));
			//System.out.println(Tools.likeComments("d12d5f51-d274-4314-92d6-fb5a5e779f6f1488738038233","b57173b7-c471-4932-87d0-0de125c6d2b01488738022783"));
			//System.out.println(Tools.unlikeComments("90add35a-79a9-4df2-9ce7-72f20162390b1488635634255","69159095-d336-4b1d-932f-29d495ec06121488634859423"));
			//System.out.println(Tools.CommentComments("d12d5f51-d274-4314-92d6-fb5a5e779f6f1488738038233","b57173b7-c471-4932-87d0-0de125c6d2b01488738022783","bien dit ;)"));

		}catch(MongoException e){
			e.getStackTrace();
		}
	}		
	
}


