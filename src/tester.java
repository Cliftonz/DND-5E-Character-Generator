/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZAC16
 */
public class tester {
    public static void main(String[] args)
    {
        double[] test ={15,14,12,11,10,8};
       double s = modiferchecker(test);
       System.out.print(s);
    }
                
    public static double modiferchecker(double[] a){
        
        System.out.println("in mod");
        double totalmodifier=0;
            for (int i = 0; i < a.length ; i++)
                {
                    totalmodifier += Math.floor((a[i]-10)/2);
                }
        return totalmodifier;
    }
}
