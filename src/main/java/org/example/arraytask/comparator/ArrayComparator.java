package org.example.arraytask.comparator;

import org.example.arraytask.entity.IntegerArrayWrapper;

import java.util.Comparator;

public enum ArrayComparator implements Comparator<IntegerArrayWrapper> {
    ID {
        @Override
        public int compare(IntegerArrayWrapper o1, IntegerArrayWrapper o2) {
            return Integer.compare(o1.getId(), o2.getId());
        }
    },
    FIRST_ELEMENT {
        @Override
        public int compare(IntegerArrayWrapper o1, IntegerArrayWrapper o2) {
            int[] firstWrapper = o1.toArray();
            int[] secondWrapper = o2.toArray();
            boolean isFirstWrapperEmpty = firstWrapper.length == 0;
            boolean isSecondWrapperEmpty = secondWrapper.length == 0;

            if (isFirstWrapperEmpty && isSecondWrapperEmpty) {
                return 0;
            }
            if (isFirstWrapperEmpty) {
                return -1;
            }
            if (isSecondWrapperEmpty) {
                return 1;
            }

            return Integer.compare(firstWrapper[0], secondWrapper[0]);
        }
    },
    SIZE{
        @Override
        public int compare(IntegerArrayWrapper o1, IntegerArrayWrapper o2) {
            return Integer.compare(o1.length(), o2.length());
        }
    }

}
