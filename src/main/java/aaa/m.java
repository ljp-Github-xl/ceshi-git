package aaa;

public class m {
    public static void main(String[] args) {
        LambdaDemo demo = new LambdaDemo();
        String something = "I am using a Functional interface";
        //实现Printer接口
        LambdaDemo.Printer printer = new LambdaDemo.Printer() {
            @Override
            public void print(String val) {
                //控制台打印
                System.out.println(val);
            }
        };
        LambdaDemo.Printer printer1 = new LambdaDemo.Printer(){
            @Override
            public void print(String val) {

            }
        };
        demo.printSomething(something, printer);
    }
}
