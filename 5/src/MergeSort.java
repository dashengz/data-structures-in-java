import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MergeSort {

        /**
         * Internal method that merges two sorted halves of a subarray (from Weiss Data Structures and Algorithm Analysis in Java)
         * @param a an array of Comparable items.
         * @param tmpArray an array to place the merged result.
         * @param leftPos the left-most index of the subarray.
         * @param rightPos the index of the start of the second half.
         * @param rightEnd the right-most index of the subarray.
         */
        private static void merge(Integer[] a, Integer[] tmpArray, int leftPos, int rightPos, int rightEnd) {
            int leftEnd = rightPos - 1;
            int tmpPos = leftPos;
            int numElements = rightEnd - leftPos + 1;

            // Main loop
            while(leftPos <= leftEnd && rightPos <= rightEnd) {
                if( a[leftPos] <= a[rightPos ]) { 
                    tmpArray[tmpPos++] = a[leftPos++];
                } else {
                    tmpArray[tmpPos++] = a[rightPos++];
                }   
            }  

            while( leftPos <= leftEnd ) {   // Copy rest of first half
                tmpArray[tmpPos++] = a[leftPos++];
            }

            while( rightPos <= rightEnd ) { // Copy rest of right half
                tmpArray[tmpPos++] = a[rightPos++];
            }

            // Copy tmpArray back
            for( int i = 0; i < numElements; i++, rightEnd-- ) {
                a[rightEnd] = tmpArray[rightEnd];
            }
        }

        /**
         * Merge Sort algorithm.
         * This is the Merge Sort algorithm from from Weiss, Data Structures and Algorithm Analysis in Java, 
         * as presented in class. 
         * @param a an array of Comparable items.
         */
        public static void mergeSort(Integer[] a ) {
            Integer[] tmpArray = new Integer[a.length];
            mergeSort(a, tmpArray, 0, a.length - 1 );
        }
        /**
         * Internal method that makes recursive calls. 
         * This is part of the MergeSort algorithm from from Weiss, Data Structures and Algorithm Analysis in Java, 
         * as presented in class. 
         * @param a an array of Comparable items.
         * @param tmpArray an array to place the merged result.
         * @param left the left-most index of the subarray.
         * @param right the right-most index of the subarray.
         */
        private static void mergeSort(Integer[] a, Integer[] tmpArray, int left, int right) {
            if(left < right) {
                int center = (left + right) / 2;
                mergeSort(a, tmpArray, left, center);
                mergeSort( a, tmpArray, center + 1, right);
                merge(a, tmpArray, left, center + 1, right);
            }
        }


        /**
         * Problem 5: Iterative Bottom-up Merge Sort
         */
        public static void mergeSortB(Integer[] inputArray) {
            //temporary array that takes up O(N) space
            Integer[] tempArray = new Integer[inputArray.length];
            //first for loop with counter i to 'split' array into subarrays
            //multiply counter i by 2 each time the loop runs once to increase the size of the subarrays
            for (int i = 1; i < inputArray.length; i = i * 2) {
                //merge subarray pairs one by one
                //using counter j to move on to the next pair
                for ( int j = i; j < inputArray.length; j = j + i * 2) {
                    //in case the second half is not full
                    int end = j + i - 1;
                    if (j + i > inputArray.length) {
                        end = inputArray.length - 1;
                    }
                    //make use of the internal merge function to merge subarray pairs
                    merge(inputArray, tempArray, j - i, j , end);
                }
            }
        }


        /** 
         * Problem 6: Merge Sort for Lists, Without Side Effects
         */
        public static List<Integer> sortList(List<Integer> inputList) {
            List<Integer> left = new LinkedList<>();
            List<Integer> right = new LinkedList<>();

            //base case
            if (inputList.size() == 1) {
                return inputList;
            } else {
                for (int l : inputList.subList(0, inputList.size() / 2)) {
                    left.add(l);
                }

                for (int r : inputList.subList(inputList.size() / 2, inputList.size())) {
                    right.add(r);
                }
                //recursively sort left and right halves
                left = sortList(left);
                right = sortList(right);
            }
            //merge and return the final results
            return mergeLists(left,right);
        }
        

        /**
         * New merge method that merges two lists and returns a new list.
         * Use this method to implement sortList.
         */
        public static List<Integer> mergeLists(List<Integer> left, List<Integer> right) {
            LinkedList<Integer> mergedList = new LinkedList<>();
            ListIterator<Integer> iteratorLeft = left.listIterator();
            ListIterator<Integer> iteratorRight = right.listIterator();
            int leftTemp;
            int rightTemp;

            while (iteratorLeft.hasNext() && iteratorRight.hasNext()) {
                leftTemp = iteratorLeft.next();
                rightTemp = iteratorRight.next();
                if (leftTemp <= rightTemp) {
                    mergedList.add(leftTemp);
                    iteratorRight.previous();
                }
                else {
                    mergedList.add(rightTemp);
                    iteratorLeft.previous();
                }
            }

            while (iteratorLeft.hasNext()) {
                leftTemp = iteratorLeft.next();
                mergedList.add(leftTemp);
            }

            while (iteratorRight.hasNext()) {
                rightTemp = iteratorRight.next();
                mergedList.add(rightTemp);
            }

            return mergedList;
        }
        
 
        public static void main(String[] args) {
            // Weiss sort
//            Integer[] a = {1};
//            Integer[] b = {1,4,9,131,0,2,7};
//            Integer[] c = {1,4,9,131,0,2,7,19};
//            Integer[] d = {1,4,9,131,0,2,7,19,245};
//            Integer[] e = {1,4,9,131,0,2,7,19,245,18,3,55,99,5,100,10,33};
//            MergeSort.mergeSortB(a);
//            MergeSort.mergeSortB(b);
//            MergeSort.mergeSortB(c);
//            MergeSort.mergeSortB(d);
//            MergeSort.mergeSortB(e);
//            System.out.println(Arrays.toString(a));
//            System.out.println(Arrays.toString(b));
//            System.out.println(Arrays.toString(c));
//            System.out.println(Arrays.toString(d));
//            System.out.println(Arrays.toString(e));

            List<Integer> left = Arrays.asList(new Integer[] { 1,3,6,11 });
            List<Integer> right = Arrays.asList(new Integer[] { 2,4,5 });
            List<Integer> resultStudent = MergeSort.mergeLists(left, right);
            System.out.println(Arrays.toString(resultStudent.toArray()));
            List<Integer> inputList = IntStream.range(1, 10).boxed().collect(Collectors.toList());
            Collections.shuffle(inputList);
            List<Integer> resultStudent2 = MergeSort.sortList(inputList);
            System.out.println(Arrays.toString(resultStudent2.toArray()));

        }
}
