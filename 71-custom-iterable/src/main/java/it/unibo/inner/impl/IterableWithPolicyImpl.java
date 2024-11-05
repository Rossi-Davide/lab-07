package it.unibo.inner.impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final T[] collection;
    private Predicate<T> predicate;

    public IterableWithPolicyImpl(final T[] objects,final  Predicate<T>  predicate){
        this.collection = objects;
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
            return (current >= 0 && current < collection.length) ? true : false; 
        }

        @Override
        public T next() {
            return IterableWithPolicyImpl.this.collection[this.current++];
        }
    }
}
