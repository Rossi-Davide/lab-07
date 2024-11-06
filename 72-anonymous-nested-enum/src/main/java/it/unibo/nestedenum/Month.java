package it.unibo.nestedenum;

import java.util.ArrayList;

public enum Month{
    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
    AUGUST(31),
    SEPTEMBER(30),
    OCTOBER(31),
    NOVEMBER(30),
    DECEMBER(31);

    private final int days;

    private Month(final int days){
        this.days = days;
    }

    public int getDays(){
        return this.days;
    }

    public static Month fromString(final String monthString){
        final ArrayList<Month> candidates= new ArrayList<>();
        for(Month m : Month.values()){
            if(m.toString().contains(monthString)){
                candidates.add(m);
            }
        }
        if(candidates.size() == 0){
            throw new IllegalArgumentException("No month with such name exists");
        }
        if(candidates.size() > 1){
            throw new IllegalArgumentException("More matches found, string ambiguous");
        }
        return candidates.get(0);
    }
}
