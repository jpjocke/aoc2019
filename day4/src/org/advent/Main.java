package org.advent;



public class Main {

    public static void main(String[] args) {

        int[] start = new int[]{2,4,0,2,9,8};
        int[] end = new int[]{7,8,4,9,5,6};
        Counter c = new Counter(start, end);

        int totalPossibilities = 0;

        while (!c.isDone()) {
            if(c.isCurrentNumberOk()) {
                totalPossibilities++;
            }

            System.out.println(c);
            c.nextNumber();
        }

        System.out.println("total: " + totalPossibilities);

    }
}
