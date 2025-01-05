import java.util.*;

public class CountWildGoose {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String charts = scanner.nextLine();
        //定义大雁叫声的字符串
        String quack = "quack";
        //定义一个数组，用于存储每个字符的状态
        int states[] = new int[quack.length()];
        //定义一个ArrayLists,用于存储每只大雁的叫声数量
        ArrayList<Integer> dp = new ArrayList<>();
        //初始化最大值0
        int max = 0;
        //遍历输入的字符串
        for (int i = 0; i < charts.length(); i++) {
            //获取当前字符在”quack"中的索引
            int index = quack.indexOf(charts.charAt(i));
            //如果索引为-1，表示输入的字符串包含非法字符，输出-1并推出程序
            if (index == -1) {
                System.out.println("-1");
                System.exit(0);
            }

            if (index == 0){
                states[index] += 1;
            } else {
                //如果当前字符的前一个字符的状态大于0，更新状态数组
                if (states[index - 1] == 0) {
                    states[index] += 1;
                } else {
                    //如果当前字符的前一个字符的状态大于0，更新状态数组
                    if (states[index - 1] > 0 ){
                        states[index -1] -= 1;
                        states[index] += 1;
                    }

                    //如果当前字符是k，表示一个完整的quack叫声已结束
                    if (quack.charAt(quack.length() - 1) == charts.charAt(i)) {
                        //如果状态数组的最后一个元素不为零，表示有大雁在叫
                        if (states[states.length - 1] != 0) {
                            //创建一个临时数组，计算当前大雁叫声数量
                            int[] temp = Arrays.copyOf(states, states.length);
                            temp[states.length - 1] = 0;
                            max = Math.max(max, Arrays.stream(temp).sum());
                            //遍历剩余的字符，更新临时数组
                            for (int j = i; j < charts.length(); j++) {
                                index = quack.indexOf(charts.charAt(j));
                                if (index > 0 && temp[index - 1] > 0) {
                                    temp[index - 1] -= 1;
                                    temp[index] += 1;
                                }
                                if (temp[states.length - 1] == max) {
                                    break;
                                }
                            }
                            //将当前大雁的叫声数量添加到arrayList中
                            dp.add(temp[states.length - 1] + 1);
                            //更新数组状态
                            states[states.length - 1] -= 1;
                        }
                    }
                }
            }
        }
        //输出结果，如果dp为零，表示没有找到一只大雁，输出-1，否则输出最大值
        System.out.println(dp.isEmpty() ? -1 : (int)Collections.max(dp));
    }
}
