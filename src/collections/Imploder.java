package collections;

import java.util.Collection;

import collections.Stepwise.Action;
import collections.Stepwise.Reaction;

public class Imploder {
  
  public static String implode(String delim, Object ... args) {
    
    return implode(delim, new StringBuilder(), args);
    
  }
  
  private static String implode(final String delim, final StringBuilder sb, Object ... args) {
    
    for(Object item : args) {
      
      if(item == null) continue;
      
      if(item.getClass().isArray()) {
        
        // item becomes a list of arrays... need to fix
//        for(Object subItem : item) {
//          
//        }
        
        
      } else if(Collection.class.isAssignableFrom(item.getClass())) {
        
        // TODO: Fix unsafe cast?
        Collection<Object> colItem = Collection.class.cast(item);
        
        Stepwise.each(colItem.iterator(), new Action<Object>() {

          @Override
          public Reaction doIt(Object item) {
            
            implode(delim, sb, item);
            
            return Reaction.CONTINUE;
          }
          
        });
        
      } else {
        sb.append(delim);
        sb.append(item);
      }
      
    }
    
    return sb.substring(delim.length());
    
  }
  
//  public static void main(String[] args) {
//    int[] arr = {1, 2, 3, 4, 5, 6, 7};
//    List<String> l = new ArrayList<String>();
//    l.add("abc");
//    l.add("!23");
//    System.out.println(implode("\n", "foo", new Double(Math.PI), l, arr));
//  }
  
}