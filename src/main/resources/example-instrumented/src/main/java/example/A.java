package example;


public class A  {

    public static void main(String[] args) {

/**        System.out.println("A.main(String[] args)");
**/
		vv.spoon.logger.TreeBuilder.beginMethod("A.main(String[] args)");

        A a = new A();
        a.mth1(Integer.parseInt(args[0]));


		vv.spoon.logger.TreeBuilder.endMethod();
    }

    public void mth1(int count) {
/**        System.out.println("A.mth1(int count)");
**/
		vv.spoon.logger.TreeBuilder.beginMethod("A.mth1(int count)");

        B b = new B();
        for(int i = 0; i < count; i++) {
            try {
                b.mth1(i);
                b.mth2();
            } catch (Exception e) {
                System.err.println("error in A.mth1(int count)");
            }
        }


		vv.spoon.logger.TreeBuilder.endMethod();
    }

}
