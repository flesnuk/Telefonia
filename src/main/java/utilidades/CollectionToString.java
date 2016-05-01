package utilidades;

import java.util.Collection;

public class CollectionToString {	
	
	public static <T> String toString(Collection<T> col) {
		StringBuilder ret = new StringBuilder();
		if(col==null)
			ret.append("\n");		
		else{
			for(T c : col){
				ret.append(c.toString());
				ret.append("\n");
			}
		}
		return ret.toString();
	}
}
