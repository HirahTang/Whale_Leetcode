## 一、思路

![1610201947289](C:\Users\IronmanJay\AppData\Roaming\Typora\typora-user-images\1610201947289.png)

1. 进入递归：当前左右括号的数量大于0的时候才可以进入递归，产生分支

2. 递归跳出：必须当左右括号的剩余数量同时为0时，才可以加入到结果数组并返回

3. 剪枝操作：因为括号生成是从左向右匹配，也就是一定先使用左括号生成接下来的括号，所以在递归过程中左括号的剩余数量一定严格小于右括号的剩余数量

4. 回溯操作：当找到一组括号组合或者进行了剪枝操作都会回到还未加入当前括号的状态

## 二、代码

#### 2.1 Java版

```java
class Solution {
    
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backTrack(res,"",n,n);
        return res;
    }

    public void backTrack(List<String> res,String path,int leftCount,int rightCount){
        if(leftCount == 0 && rightCount == 0){
            res.add(path);
            return;
        }
        if(leftCount > rightCount){
            return;
        }
        if(leftCount > 0){
            backTrack(res,path+"(",leftCount-1,rightCount);
        }
        if(rightCount > 0){
            backTrack(res,path+")",leftCount,rightCount-1);
        }
    }
    
}
```

#### 2.2 C版

```c
void backTrack(char** res,int *returnSize,char* path,int pathIndex,int leftCount,int rightCount)
{
    if(leftCount == 0 && rightCount == 0)
    {
        res[(*returnSize)] = (char*)calloc(pathIndex+1,sizeof(char));
        strcpy(res[(*returnSize)++],path);
        return;
    }
    if(leftCount > rightCount)
    {
        return;
    }
    if(leftCount > 0)
    {
        path[pathIndex++] = '(';
        backTrack(res,returnSize,path,pathIndex,leftCount-1,rightCount);
        path[--pathIndex] = 0;
    }
    if(rightCount > 0)
    {
        path[pathIndex++] = ')';
        backTrack(res,returnSize,path,pathIndex,leftCount,rightCount-1);
        path[--pathIndex] = 0;
    }
}

char** generateParenthesis(int n, int* returnSize)
{
    char** res = (char**)malloc(sizeof(char*)*1500);
    *returnSize = 0;
    char* path = (char*)calloc((2*n+1),sizeof(char));
    p22_GenerateParentheses(res,returnSize,path,0,n,n);
    return res;
}
```

## 三、性能分析

#### 3.1 时间复杂度

&nbsp;&nbsp;理论上，$n$对括号，也就是$n$值对应的决策树的层数为$2*n$层，那么就有$2^{2n}-1$个结点，每个结点代表一个子问题，需要用$O(1)$的时间来进行解决，所以理论上的时间复杂度为$O(2^{2n}-1)=O(4^n)$，但实际上存在随机的剪枝行为，所以这个时间复杂度我也不确定😖

#### 3.2 空间复杂度

&nbsp;&nbsp;递归实际上是出栈和入栈的操作，同上，理论上每个结点需要用$S(1)$的空间来解决问题，所以理论上的空间复杂度为$S(2^{2n}-1)=S(4^n)$，但实际上由于有随机剪枝的操作，所以我也不确定这个空间复杂度😭

