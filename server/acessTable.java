package server;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.util.Tables;

public class acessTable {
	static AmazonDynamoDBClient dynamoDB;
	
	private static void init() throws Exception {
        /*
         * The ProfileCredentialsProvider will return your [default]
         * credential profile by reading from the credentials file located at
         * (/Users/ganitha/.aws/credentials).
         */
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider("default").getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (/Users/ganitha/.aws/credentials), and is in valid format.",
                    e);
        }
        dynamoDB = new AmazonDynamoDBClient(credentials);
        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
        dynamoDB.setRegion(usWest2);
    }
	
	public static void updateAnswer(int question, int answer,String kidName){
		String tableName = "QuestionDB";

        // Create table if it does not exist yet
        if (Tables.doesTableExist(dynamoDB, tableName)) {
            System.out.println("Table " + tableName + " is already ACTIVE");
        }
        else
        {
        	System.out.println("No Table found");
        }
        
       
        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("QuestionID", new AttributeValue().withN(Integer.toString(question)));

        GetItemRequest getItemRequest = new GetItemRequest()
            .withTableName(tableName)
            .withKey(key);

        GetItemResult result = dynamoDB.getItem(getItemRequest);
        Map<String, AttributeValue> map = result.getItem();
        int isCorrect = 0;
        String  correctAns = map.get("Ans").getN();
        if (Integer.parseInt(correctAns)==answer){
        	isCorrect = 1;
        }
        System.out.println(correctAns);
        
        key.clear();
        //Retrieve the answers in kid DB
        key.put("Name", new AttributeValue().withS(kidName));
        getItemRequest = new GetItemRequest().withTableName("KidDB").withKey(key);
        result = dynamoDB.getItem(getItemRequest);
        map = result.getItem();
        
        
        
        
        
        //key = new HashMap<String, AttributeValue>();
		
         

		  
		
		  //Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
		  
		  map.put(Integer.toString(question), new AttributeValue().withN(Integer.toString(isCorrect)));
		  
		  
		  PutItemRequest putItemRequest = new PutItemRequest()
		  .withTableName("KidDB")
		  .withItem(map);
		  PutItemResult presult = dynamoDB.putItem(putItemRequest);
		  //System.out.println(map.get(Integer.toString(question)).getN());
       
	}

	
	public static void addKids(String name){
		 
        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
		key.put("Name", new AttributeValue().withS(name));

		 GetItemRequest getItemRequest = new GetItemRequest()
            .withTableName("KidDB")
            .withKey(key);

		 GetItemResult  result = dynamoDB.getItem(getItemRequest);
		 Map<String, AttributeValue> map = result.getItem();
         int rating = Integer.parseInt(map.get("a1").getN()) + Integer.parseInt(map.get("a2").getN()) + Integer.parseInt(map.get("a3").getN());
         Kid k1 = new Kid(name,rating);
 		 Kids.addKid(k1);       
         //System.out.println(rating);
		
	}
	
	
	
	 public static void main(String[] args) throws Exception {
	        init();

	        try {
	            String tableName = "QuestionDB";

	            // Create table if it does not exist yet
	            if (Tables.doesTableExist(dynamoDB, tableName)) {
	                System.out.println("Table " + tableName + " is already ACTIVE");
	            }
	            else
	            {
	            	System.out.println("No Table found");
	            }
	            
	           /*
	            HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
	            key.put("QuestionID", new AttributeValue().withN("1"));

	            GetItemRequest getItemRequest = new GetItemRequest()
	                .withTableName(tableName)
	                .withKey(key);

	            GetItemResult result = dynamoDB.getItem(getItemRequest);
	            Map<String, AttributeValue> map = result.getItem();*/
	            
	            
	            
	         /*   
	            //access kid table
	            tableName = "KidDB";
	            // Create table if it does not exist yet
	            if (Tables.doesTableExist(dynamoDB, tableName)) {
	                System.out.println("Table " + tableName + " is already ACTIVE");
	            }
	            else
	            {
	            	System.out.println("No Table found");
	            }
	            
	            key.clear();
	            map.clear();
	            String kidname = "a";
	            
	            //addKids(kidname);
	            //addKids("b");
	           // addKids("c");
	           // addKids("d");
	            //System.out.println(Kids.getPair("c").getName());*/
	            updateAnswer(3,2,"a");
	            
	            
	        }catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException, which means your request made it "
	                    + "to AWS, but was rejected with an error response for some reason.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException, which means the client encountered "
	                    + "a serious internal problem while trying to communicate with AWS, "
	                    + "such as not being able to access the network.");
	            System.out.println("Error Message: " + ace.getMessage());
	        }
	    }
	 
	 
}
