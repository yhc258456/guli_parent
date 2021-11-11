public class Test {
    public static void main(String[] args) {
        int[] numArr = {1, 2, 1};
        System.out.println(twoSum(numArr, 2));
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1 && nums[i] < target; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}