package ua.org.oracle.academy.malahk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 21.06.2015.
 */
public class SameIndexSet {

    List<Integer> indexSet = new ArrayList<>();

    public SameIndexSet(Integer index) {

        this.add(index);
    }
    public SameIndexSet(){

    }
    public boolean isEmpty(){

        return this.indexSet.isEmpty();
    }

    public Integer get() {

        return this.indexSet.get(0);
    }

    public void add (Integer index) {

        this.indexSet.add(index);
    }
}
