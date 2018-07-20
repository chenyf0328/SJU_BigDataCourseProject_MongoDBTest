import java.net.UnknownHostException;
import java.util.Set;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class ConnectToMongoDB {
    Mongo m=null;
    DB db;

    public void connect(){
        m = new Mongo("127.0.0.1",27017);
    }

    public void listAllCollections(String dbName){
        if (m!=null){
            db=m.getDB(dbName);
            Set<String> collections=db.getCollectionNames();
            for (String s:collections){
                System.out.println(s);
            }
        }
    }

    public void listLocationCollectionDocuments(){
        if (m!=null){
            db=m.getDB("classes");
            DBCollection collection=db.getCollection("location");
            DBCursor cur=collection.find();
            while(cur.hasNext()){
                System.out.println(cur.next());
            }
        }
        else{
            System.out.println("Please connect to MongoDB and then fetch the collection");
        }
    }

    public static void main(String[] args){
        ConnectToMongoDB connectToMongoDB = new ConnectToMongoDB();
        connectToMongoDB.connect();
        connectToMongoDB.listAllCollections("classes");
        connectToMongoDB.listLocationCollectionDocuments();
    }
}
