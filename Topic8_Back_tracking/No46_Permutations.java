package Lecode.test;

import java.util.*;

public class RP46_Permutations {

    public static void main(String[] args) {
        int [] nums = {1,2,3};
        List<List<Integer>> res = permute(nums);
        System.out.println(res);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0){
            return res;
        }
        //定义栈容器对每步取到的排列数进行记录
        Stack<Integer> path = new Stack<>();
        //定义数组，判断在排列过程中数字是否被使用过
        boolean[] used = new boolean[len];
        //depth:递归到了第几层
        findall(nums,len,0,path,used,res);
        return res;
    }

    private static void findall(int[] nums, int len, int depth, Stack<Integer> path, boolean[] used, List<List<Integer>> res) {
        //递归层数等于数组数组长度的时候，所有元素考虑完成，得到了一个排列
        if (depth == len){
            //添加path的拷贝，防止添加之后path回到根节点，向res中添加空列表
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0;i < len;i++){
            //如果使用过就跳过
            if (used[i]){
                continue;
            }
            path.push(nums[i]);
            used[i] = true;
            findall(nums,len,depth+1,path,used,res);
            //进行回溯
            path.pop();
            used[i]=false;
        }
    }
}
