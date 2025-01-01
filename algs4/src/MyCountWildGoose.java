import java.util.Scanner;

public class MyCountWildGoose {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        String[] split = str.split("");
        int count = 0;//大雁数量计数
        int offset = 0;
        String[] quack = new String[]{"q", "u", "a", "c", "k"};
        for (int i = 0; i < split.length; i++ ) {
            String s = split[i];
            //如果等于第一个值，偏移量加1
            if (s.equals(quack[offset])) {
                offset++;
            } else {
                offset = 0;
                if (s.equals(quack[offset])){
                    offset++;
                } else {
                    offset = 0;
                }
            }
            //如果出现完整的quack，大雁数量加一
            if (offset == quack.length) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("大雁的数量:-1");
        } else {
            System.out.println("大雁的数量:" + count);
        }
    }
}
