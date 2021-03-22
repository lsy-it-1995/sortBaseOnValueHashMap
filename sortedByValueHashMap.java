
import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class sortedByValueHashMap{

  public static List< List <Integer> >  sort(List<Integer> arr){
    HashMap<Integer, Integer> map = new HashMap<>();
    for(int i = 0; i < arr.size();i++){

      if(!map.containsKey(arr.get(i))){
        map.put(arr.get(i), 0);
      }
      map.put(arr.get(i), 1 + map.get(arr.get(i)));
    }

    valueSortedFirst vsf = new valueSortedFirst(map);
    TreeMap<Integer, Integer> sorted = new TreeMap<Integer, Integer> (vsf);

    sorted.putAll(map);

    List< List <Integer> > list = new ArrayList<List<Integer>>();
    for(Map.Entry<Integer, Integer> entry: sorted.entrySet()){
      List<Integer> temp = new ArrayList<>();
      temp.add(entry.getKey());
      temp.add(entry.getValue());
      list.add(temp);
    }
    return list;
  }

  public static void main(String[] args){
    int[] arr =  {1,2,2,3,4,2,1,3};//,3,
    List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
    List< List <Integer> > l = sort(list);
    for(int i = 0; i < l.size();i++){
      System.out.println(l.get(i).get(0) + " " + l.get(i).get(1));
    }
  }
}

class valueSortedFirst implements Comparator<Integer>{
  Map<Integer, Integer> base;
  public valueSortedFirst(Map<Integer, Integer> map){
    this.base = map;
  }

  public int compare(Integer a, Integer b){
    Integer val_a = base.get(a),
        val_b = base.get(b);
    if(val_b == val_a){
      return (b > a)?1:-1;
    }
    return (val_b > val_a)?1:-1;
  }
}
