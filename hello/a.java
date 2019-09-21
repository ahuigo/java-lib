public class a{
    /* 第一个Java程序
     * 它将打印字符串 Hello World
     */
    public static void main(String []args) {
         for (int i=1; i<=10; i++) {
            System.out.println("i = " + i);
            for (int j=1; j<=10; j++) {
                System.out.println("j = " + j);
                if (j >= i) {
                    break 2;
                }
            }
            // break跳到这里
            System.out.println("breaked");
        }
    }
}
