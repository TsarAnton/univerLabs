package pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Algorithm {
    
    public Matrix matrix;
    public int v2;
    public int v1;
    public HashMap<Integer, ArrayList<Integer>> map;;
    public int paths;

    public Algorithm(Matrix matrix, int v1, int v2) {
        this.matrix = matrix;
        this.map = new HashMap<Integer, ArrayList<Integer>>();
        for(int i = 0; i < matrix.N; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        this.paths = 0;
        this.v2 = v2;
        this.v1 = v1;
    }

    public void fillArray(int v) {
        if(v == v2) {
            paths++;
            return;
        }
        
        for(int i = 0; i < matrix.N; i++) {
            if(matrix.getElement(v, i) == 1 && !map.get(v).contains(i)) {
                if(i != v2) {
                    map.get(i).clear();
                }
                for(int j = 0; j < map.get(v).size(); j++) {
                    map.get(i).add(map.get(v).get(j));
                }
                map.get(i).add(v);
                fillArray(i);
            }
        }
    }

    public ArrayList<Integer> getElements() {
        HashMap<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        ArrayList<Integer> array = map.get(v2);
        for(int i = 0; i < map.get(v2).size(); i++) {
            int v = array.get(i);
            if(!(v == v1 || v == v2)) {
                if(map1.containsKey(v)) {
                    map1.put(v, map1.get(v) + 1);
                } else {
                    map1.put(v, 1);
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        for(Entry<Integer, Integer> entry:map1.entrySet()) {
            if(entry.getValue() == paths) {
                result.add(entry.getKey() + 1);
            }
        }
        return result;
    }
}
