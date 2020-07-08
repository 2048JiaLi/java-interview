[转自](https://mp.weixin.qq.com/s/fSyJVvggxHq28a0SdmZm6Q)


## TowSum问题

如果假设输入一个数组 `nums` 和一个目标和 `target`，**请你返回 `nums` 中能够凑出 `target` 的两个元素的值**，比如输入 `nums = [5,3,1,6], target = 9`，那么算法返回两个元素 `[3,6]`。可以假设只有且仅有一对儿元素可以凑出`target`。

> 直接的方法是先对 `nums` 排序，然后双指针计算

```java
vector<int> twoSum(vector<int>& nums, int target) {
    // 先对数组排序
    sort(nums.begin(), nums.end());
    // 左右指针
    int lo = 0, hi = nums.size() - 1;
    while (lo < hi) {
        int sum = nums[lo] + nums[hi];
        // 根据 sum 和 target 的比较，移动左右指针
        if (sum < target) {
            lo++;
        } else if (sum > target) {
            hi--;
        } else if (sum == target) {
            return {nums[lo], nums[hi]};
        }
    }
    return {};
}
```

### **`nums` 中可能有多对儿元素之和都等于 `target`，请你的算法返回所有和为`target` 的元素对儿，其中不能出现重复**

`vector<vector<**int**>> twoSumTarget(vector<**int**>& nums, **int** target);`

比如说输入为 `nums = [1,3,1,2,2,3], target = 4`，那么算法返回的结果就是：`[[1,3],[2,2]]`。

对于修改后的问题，关键难点是现在可能有多个和为 `target` 的数对儿，还不能重复，比如上述例子中 `[1,3]` 和 `[3,1]` 就算重复，只能算一次。