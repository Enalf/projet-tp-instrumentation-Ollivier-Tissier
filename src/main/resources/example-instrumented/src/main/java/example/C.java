package example;

public class C  {
    private int i;

    public C(int i) {
/**        System.out.println("C.C(int i)");
**/
		vv.spoon.logger.TreeBuilder.beginMethod("C.C(int i)");
        this.i = i;
 
		vv.spoon.logger.TreeBuilder.endMethod();
   }

    public int mth1() {
/**       System.out.println("C.mth1()");
**/
		vv.spoon.logger.TreeBuilder.beginMethod("C.mth1()");
 
		vv.spoon.logger.TreeBuilder.endMethod();
       return 100/i;
    }
}
