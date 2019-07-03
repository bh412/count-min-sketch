package com.ipa;
import java.util.Scanner;

/**
 * I want delta to be 0.001 (99.9% not error prob) so use depth = ceiling(ln 1/delta) to get depth of 7. could let user specify in future
 * Using int so want error to be small enough for no overlap, I chose ~0.3 so width = ceiling(2.7/0.3) so width of 10
 *  **/

class CountMinSketchStructure
{
    private int[] d1;
    private int[] d2;
    private int[] d3;
    private int[] d4;
    private int[] d5;
    private int[] d6;
    private int[] d7;
    private int size;
    private static int width = 10;

    public CountMinSketchStructure()
    {
        size = width;
        d1 = new int[ size ];
        d2 = new int[ size ];
        d3 = new int[ size ];
        d4 = new int[ size ];
        d5 = new int[ size ];
        d6 = new int[ size ];
        d7 = new int[ size ];
    }

    public void clear()
    {
        size = width;
        d1 = new int[ size ];
        d2 = new int[ size ];
        d3 = new int[ size ];
        d4 = new int[ size ];
        d5 = new int[ size ];
        d6 = new int[ size ];
        d7 = new int[ size ];
    }

    public void insert(int val)
    {
        int hash1 = hashFunction1(val);
        int hash2 = hashFunction2(val);
        int hash3 = hashFunction3(val);
        int hash4 = hashFunction4(val);
        int hash5 = hashFunction5(val);
        int hash6 = hashFunction6(val);
        int hash7 = hashFunction7(val);
        /** move counter so no collisions **/
        d1[ hash1 ]++;
        d2[ hash2 ]++;
        d3[ hash3 ]++;
        d4[ hash4 ]++;
        d5[ hash5 ]++;
        d6[ hash6 ]++;
        d7[ hash7 ]++;
    }
    public int instancesOf(int val)
    {
        int hash1 = hashFunction1(val);
        int hash2 = hashFunction2(val);
        int hash3 = hashFunction3(val);
        int hash4 = hashFunction4(val);
        int hash5 = hashFunction5(val);
        int hash6 = hashFunction6(val);
        int hash7 = hashFunction7(val);
        return Math.min( d1[ hash1 ], Math.min(d2[ hash2 ], Math.min(d3[ hash3 ] , Math.min(d4[ hash4 ] , Math.min(d5[ hash5 ] , Math.min(d6[ hash6 ] , d7[ hash7 ]))))));
    }
    /**
     * hash functions are simple, use form of (a * i + b mod p mod w) (Approximating Data with the Count-Min Data Structure, Graham Cormode et al 2011)
     * p is some prime, a and b are 0 < a/b < p
     * w is width
     * i is the value to be put in
     * **/
    private int hashFunction1(int val)
    {
        return val % size;
    }
    private int hashFunction2(int val)
    {
        return ((val * (val + 1)) % size);
    }
    private int hashFunction3(int val)
    {
        return (size - 1) - (val - 1) % size;
    }
    private int hashFunction4(int val)
    {
        return ((val + 2) * val) % size;
    }
    private int hashFunction5(int val)
    {
        return (size + val) * val % size;
    }
    private int hashFunction6(int val)
    {
        return (3 * val + 7) % size;
    }
    private int hashFunction7(int val)
    {
        return ((val) + (21 * val)) % size % size;
    }
    public void printCountMinSketch()
    {
        System.out.print("d1 : ");
        for (int i = 0; i < size; i++)
            System.out.print(d1[i] +" ");
        System.out.print("\nd2 : ");
        for (int i = 0; i < size; i++)
            System.out.print(d2[i] +" ");
        System.out.print("\nd3 : ");
        for (int i = 0; i < size; i++)
            System.out.print(d3[i] +" ");
        System.out.print("\nd4 : ");
        for (int i = 0; i < size; i++)
            System.out.print(d4[i] +" ");
        System.out.print("\nd5 : ");
        for (int i = 0; i < size; i++)
            System.out.print(d5[i] +" ");
        System.out.print("\nd6 : ");
        for (int i = 0; i < size; i++)
            System.out.print(d6[i] +" ");
        System.out.print("\nd7 : ");
        for (int i = 0; i < size; i++)
            System.out.print(d7[i] +" ");
        System.out.println();
    }
}

public class CountMin
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        CountMinSketchStructure cms = new CountMinSketchStructure();

        /** Initialise with random ints if required otherwise as you like **/

//        for (int i=0; i<=100; i++) {
//            int x = (int) (Math.random() * 100);
//            System.out.println(x);
//            cms.insert(x);
//        }

        char ch;
        do
        {
            System.out.println("\nCount-Min Sketch Operations\n");
            System.out.println("1. insert");
            System.out.println("2. frequency count");
            System.out.println("3. reset count-min space");

            int choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Enter int value");
                    cms.insert(scan.nextInt() );
                    break;
                case 2 :
                    System.out.println("Enter int value");
                    int val = scan.nextInt();
                    System.out.println("\nSketch count for "+ val +" = "+ cms.instancesOf( val ));
                    break;
                case 3 :
                    cms.clear();
                    System.out.println("count-min structure reset\n");
                    break;
                default :
                    System.out.println("Input must be an int \n ");
                    break;
            }
            cms.printCountMinSketch();

            System.out.println("\nDo you want to continue (y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'y');
    }
}