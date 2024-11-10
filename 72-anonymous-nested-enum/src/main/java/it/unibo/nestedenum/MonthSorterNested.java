package it.unibo.nestedenum;


import java.util.Comparator;
import java.util.List;

import it.unibo.functional.Transformers;
import it.unibo.functional.api.Function;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private enum Month{
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
            final List<Month> matches = Transformers.select(List.of(Month.values()), new Function<Month,Boolean>() {
                @Override
                public Boolean call(final Month input) {
                    return input.toString().toLowerCase().contains(monthString.toLowerCase());
                }
            });
            if(matches.size() == 0){
                throw new IllegalArgumentException("No month with such name exists");
            }
            if(matches.size() > 1){
                throw new IllegalArgumentException("More matches found, string ambiguous");
            }
            return matches.get(0);
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    private static class SortByMonthOrder implements Comparator<String>{
        @Override
        public int compare(final String o1,final String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }
    }

    private static class SortByDate implements Comparator<String>{
        @Override
        public int compare(final String o1,final String o2) {
            return Integer.compare(Month.fromString(o1).getDays(), Month.fromString(o2).getDays()); 
        }
    }
}
