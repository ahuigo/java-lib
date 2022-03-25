public class a{
    /* 第一个Java程序
     * 它将打印字符串 Hello World
     */
    public static void main(String []args) {
         for (int i=1; i<=3; i++) {
            System.out.println("i = " + i);
            for (int j=1; j<=3; j++) {
                System.out.println("j = " + j);
                if (j >= i) {
                    break;
                    // 不支持: break 2;
                }
            }
            // break跳到这里
            System.out.println("breaked 1");
        }
        System.out.println("java.class.path:"+System.getProperty("java.class.path"));
        System.out.println("breaked 2");
    }
}
