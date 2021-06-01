package lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class LambdaWithCollection {
	
	public static void main(String[] args) {
		/* -------------List---------------*/
		
		//list : replaceAll
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++) {
			list.add(i);
		}
		list.replaceAll(x -> x * 10);
		System.out.println("List replaceAll : "+ list);
		
		//list : forEach
		System.out.print("List forEach : ");
		list.forEach(x -> System.out.print(x +" "));
		
		//list : removeIf
		list.removeIf(x -> x % 3 == 0);
		System.out.println("\nList removeIf : "+list);
		
		//list : replaceAll
		list.replaceAll(x -> x + 2);
		System.out.println("List replaceAll : "+list);
		
		System.out.println("\n");
		
		
		/* -------------Map---------------*/
		Map<String, Integer> map = new HashMap<>();
		map.put("1", 1);
		map.put("2", 2);
		map.put("3", 3);
		map.put("4", 4);
		
		//map : compute
		map.compute("1", (k, v) -> v * 10);
		System.out.println("Map compute : "+map);
		
		//map : computeIfAbsent
		map.computeIfAbsent("5", v -> Integer.MAX_VALUE);
		System.out.println("Map computeIfAbsent : "+map);
		
		//map : computeIfPresent
		map.computeIfPresent("5", (k, v) -> v = Integer.MIN_VALUE);
		System.out.println("Map computeIfPresent : "+map);
		
		map.put("6", 1);
		//map : merge
		//"6"에 해당하는 key가 있다면 100, 없다면 4
		map.merge("6", 4, (oldValue, newValue) -> oldValue * 100);
		System.out.println("Map merge : "+map);
		
		//map : replaceAll
		//key, value를 파라미터로 받아 값을 리턴,
		map.replaceAll((k, v) -> k.equals("2") ? v : v * 10);
		System.out.println("map replcateAll : "+map);
		
		map.putIfAbsent("7", 0);
		System.out.println(map);
		
	}
}
