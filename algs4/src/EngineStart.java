import java.util.*;

public class EngineStart {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//发动机总个数
        int e = sc.nextInt();//手动启动的发动机个数
        int[] launches = new int[n];//记录每个发动机最终启动时刻K·
        Queue<int[]> queue = new LinkedList<>();
        Arrays.fill(launches, 1001);//初始化为极大值
        for (int i = 0; i < e; i++){
            int t = sc.nextInt();//手动启动时刻
            int p = sc.nextInt();//发动机位置编号
            launches[p] = t;//手动启动时刻
            queue.offer(new int[]{p, t});//将手动启动的发动机加入队列
        }

        //进行BFS, 计算所有发动机的最早启动时间
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0];
            int time = current[1];
            //计算相邻的发动机位置
            int[] neighbors = {(pos - 1 + n) % n, (pos + 1) % n  };//考虑环状结构

            for (int neighbor : neighbors) {
                if (launches[neighbor] > time + 1 ) {
                    launches[neighbor] = time + 1;
                    queue.offer(new int[]{neighbor, time + 1});
                }
            }
        }

        int maxT = 0;//最晚启动时刻
        ArrayList<Integer> lastEngines = new ArrayList<>();//最晚启动的发动机集合
        for (int i = 0; i < n; i++){
            if (launches[i] > maxT) {
                maxT = launches[i];
                lastEngines.clear();
                lastEngines.add(i);
            } else if ( launches[i] == maxT){//相同的最晚时刻
                lastEngines.add(i);
            }
        }
        System.out.println(lastEngines.size());
        Collections.sort(lastEngines);
        for (int engine : lastEngines) {
            System.out.println(engine + " ");
        }
    }
}
