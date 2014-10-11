package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Kids {
private static List<Kid> kidsList = new ArrayList<Kid>();
private static Map <Kid,Kid> pairs = new HashMap<Kid,Kid>();

	
public static void addKid(Kid k1){
	kidsList.add(k1);
	Collections.sort(kidsList, new Comparator<Kid>(){

		@Override
		public int compare(Kid k1, Kid k2) {
			// TODO Auto-generated method stub
			if (k1.getRating() < k2.getRating()){
				return -1;
			}
			
			else if (k1.getRating()== k2.getRating()){
				return 0;
			}
			
			else{
				return 1;
			}
		}
		
	});
	
	pairKids();

}


private static void pairKids() {
	pairs.clear();
	if (kidsList.size()<2){
		return;
	}
	if(kidsList.size()%2==0){
		//System.out.println("Size is "+kidsList.size());
		for (int i=0;i<(kidsList.size()/2);i++){
			
			//System.out.println("Putting "+kidsList.get(i).getName()+" & "+kidsList.get(kidsList.size()-1-i).getName());
			pairs.put(kidsList.get(i), kidsList.get(kidsList.size()-1-i));
			//System.out.println("Putting "+kidsList.get(kidsList.size()-1 -i).getName()+" & "+kidsList.get(i).getName());
			pairs.put(kidsList.get(kidsList.size()-1 -i), kidsList.get(i));
		}
	}
	else {
		for (int j = 0; j <(kidsList.size()/2)-1;j++){
			
			pairs.put(kidsList.get(j), kidsList.get(kidsList.size()-2-j));
			pairs.put( kidsList.get(kidsList.size()-2-j),kidsList.get(j) );
		}
	}

	
}
	
public static Kid getPair(String name){
	//System.out.println("Size is "+pairs.size());
	Kid k1 = getKid(name);
	return pairs.get(k1);
}

public static Kid getKid(String name){
	Iterator<Kid> i1 = kidsList.iterator();
	while(i1.hasNext()){
		Kid k1 = i1.next();
		if (k1.getName().equals(name)){
			return k1;
		}
	}
	return null;
}


}
