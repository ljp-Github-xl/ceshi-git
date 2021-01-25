package aaa;

public class arr {
    public static void main(String[] args) {
        String a ="12233344445555";
        int startIndex =0;
        String str = "";
        for (int i = 1; i < 6; i++) {
            String substring = a.substring(startIndex, startIndex+i);
            int length = substring.length()+i;
            startIndex=length;
            System.out.println(substring);

        }
    }



}
