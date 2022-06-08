package personal.math;
import java.lang.Math;
import java.util.Arrays;
import java.util.*;

public class findRepresentationsGreaterThanTwo {
    public static int findHigh(int testNumber) {
        boolean powerNotFound = true;
        int counter = 1;
        while (powerNotFound) {
            if (Math.pow(2, counter) <= testNumber) {
                counter++;
            } else {
                powerNotFound = false;
            }
        }
        return counter;
    }

    public static int[] findNextBase(int[] base, int testValue){

         //find the leftmost base greater than 2
        int baseToChange = 0;
        int counter = 1;
        while (base[baseToChange] == 2 && counter < base.length) {
            baseToChange+=1;
            counter++;
        }
        if (baseToChange == base.length-1 && base[base.length - 1] == 2) {
            return new int[]{-1};
        }
        //decrease that base by 1, also define a value we will need
        base[baseToChange] -=1;
        int keptamount = base.length - baseToChange;

        //find the product of the bases to the right of and including the decreased base
        int[] outputbase = new int[keptamount + 1];
        int minorproduct = 1;
        for (int i = 0; i < keptamount; i++) {
            minorproduct = minorproduct * base[base.length-1-i];
            outputbase[i+1] = base[base.length -keptamount+i];
        }
        // check if that product is greater than the testValue if mulitplied by 2, if not, then 3, if not then 4, if not ...
        int newbase = 2;
        while((minorproduct*newbase <= testValue)) {
            newbase++;
        }
        //create a new string for completed new base, which will be part of the old string to the right of the leftmost base greater than two on the original plus the newbase value appended to the left of that
        outputbase[0] = newbase;
        return outputbase;
    }
    public static int[] numberInThisBase(int[] base, int testNumber){
        int[] representation = new int[base.length];
        int remainder = testNumber;
        int[] powerSizeList = new int[base.length];
        int cummulativeProduct = 1;
        for (int i = base.length-1; i >= 0 ; i--) {
            powerSizeList[i] = cummulativeProduct;
            cummulativeProduct = cummulativeProduct*base[i];
        }

        if (cummulativeProduct< testNumber) {
            System.out.println(cummulativeProduct);
            return base;

        }

        else {
            for (int i = 0; i <= base.length - 1; i++) {
                for (int j = base[i]; j >0; j--) {
                    if (remainder >= powerSizeList[i] * j) {
                        remainder = remainder - powerSizeList[i] * j;
                        representation[i] = j;
                    }
                }
            }
            return representation;
        }
    }

    public static void main(String[] args) {
        LinkedList<int[]> linkedlist = new LinkedList<>();

        int[] listylist;
        boolean go = true;
        int[] currentNumber;
        boolean add = true;
        for (int testvalue = 1; testvalue < 1000; testvalue++) {
            listylist = new int[]{testvalue + 1};

            System.out.println("number = " + testvalue);
            while (go) {
                currentNumber = numberInThisBase(listylist, testvalue);
                for (int[] Number : linkedlist) {
                    if (Arrays.equals(Number, currentNumber)) {
                        add = false;
                        break;
                    }
                }
                if (add) {
                    linkedlist.add(currentNumber);
                    System.out.print(Arrays.toString(currentNumber));
                }
                add = true;
                listylist = findNextBase(listylist, testvalue);

                if (Arrays.equals(listylist, new int[]{-1})) {
                    go = false;
                }

            }
            go = true;
            //System.out.println(testvalue+ " " + linkedlist.size());
            linkedlist.clear();
        }

//        int[] testarray = new int[findHigh(testvalue)];
//        while(running) {
//
//        }
//        for (int i = 0; i < findHigh(testvalue); i++) {
//            System.out.println(testarray[i]);
//        }
    }
}
 