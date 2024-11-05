package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    /*Instead of using a raw array consider using a data structure such as 
     * ArrayList or List. This allows the use of the collections' specific functions. 
     * Eventually a defensive copy of the array can be created so that the elements can't be 
     * modified by anyone
    */
    private final List<T> collection;
    private Predicate<T> predicate;

    public IterableWithPolicyImpl(final T[] objects,final  Predicate<T>  predicate){
        this.collection = List.of(objects);
        this.predicate = predicate;
    }

    public IterableWithPolicyImpl(final T[] objects){
        this(objects,new Predicate<T>() {
            public boolean test(final T elem) {
                return true;
            }
        });
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.predicate = filter;
    }

    public class IteratorImpl implements Iterator<T>{
        private int current = 0;

        @Override
        public boolean hasNext() {
            while (current < collection.size()) {
                if(predicate.test(collection.get(current))){
                    return true;
                } 
                current++;
            }

            return false;
        }

        @Override
        public T next() {
            return collection.get(current++);
        }
    }
}
