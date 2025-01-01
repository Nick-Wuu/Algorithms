import java.util.*;
import java.util.stream.Collectors;

public class DouDiZhu {
    private static final HashMap<String, Integer> CARD_TO_NUMBER = new HashMap<>();
    static {
        CARD_TO_NUMBER.put("3", 3);
        CARD_TO_NUMBER.put("4", 4);
        CARD_TO_NUMBER.put("5", 5);
        CARD_TO_NUMBER.put("6", 6);
        CARD_TO_NUMBER.put("7", 7);
        CARD_TO_NUMBER.put("8", 8);
        CARD_TO_NUMBER.put("9", 9);
        CARD_TO_NUMBER.put("10", 10);
        CARD_TO_NUMBER.put("J", 11);
        CARD_TO_NUMBER.put("Q", 12);
        CARD_TO_NUMBER.put("K", 13);
        CARD_TO_NUMBER.put("A", 14);
        CARD_TO_NUMBER.put("2", 15);

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);//创建scanner用于读取输入
        String[] cards = sc.nextLine().split(" ");//读取一行输入，并按空格分割成数组
        //对输入的扑克牌按照定义的牌面大小进行排序
        Arrays.sort(cards, Comparator.comparingInt(CARD_TO_NUMBER::get));
        //用于存储所有可能的顺子序列
        ArrayList<LinkedList<String>> straights = new ArrayList<>();
        //初始化当前正在检查的顺子序列
        LinkedList<String> currentStraight = new LinkedList<>();
        //放入第一张牌
        currentStraight.add(cards[0]);
        //放入顺子
        straights.add(currentStraight);

        for (int i = 1; i < cards.length; i++){
            String card = cards[i];
            boolean added = false;//标记当前牌是否已经被添加到某个顺子中
            //遍历当前已存在的所有顺子序列，尝试将当前牌加入
            for (LinkedList<String> straight : straights) {
                //判断当前牌是否可以追加到顺子的末尾
                if (CARD_TO_NUMBER.get(card) - CARD_TO_NUMBER.get(straight.getLast()) == 1) {
                    straight.add(card);
                    added = true;
                    break;
                }
            }
            //如果当前牌没有加入到任何顺子中，创建一个新的顺子队列
            if (!added){
                LinkedList<String> newStraight = new LinkedList<>();
                newStraight.add(card);
                straights.add(newStraight);

            }
        }
        //筛选出长度至少为5的有效顺子序列
        List<LinkedList<String>> validStraights = straights.stream().
                filter(straight -> straight.size() >= 5).
                collect(Collectors.toList());
        //如果欸有找到有效的顺子队列，输出NO
        if(validStraights.isEmpty()){
            System.out.println("NO");
        } else {
            //将有效的顺子按照起始牌的大小有序输出
            validStraights.stream().sorted(Comparator.comparingInt(a -> CARD_TO_NUMBER.get(a.getFirst()))).
                    forEach(straight -> System.out.println(String.join(" ", straight)));
        }

    }
}
