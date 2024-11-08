package it.unibo.nestedenum;


import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(final String o1,final String o2) {
                return Integer.compare(Month.fromString(o1).getDays(), Month.fromString(o2).getDays()); 
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(final String o1,final String o2) {
                return Month.fromString(o1).compareTo(Month.fromString(o2));
            }
        };
    }
}
