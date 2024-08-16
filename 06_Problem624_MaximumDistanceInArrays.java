class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int minVal = arrays.get(0).get(0);
        int maxVal = arrays.get(0).get(arrays.get(0).size() - 1);
        int maxDistance = 0;

        for (int i = 1; i < arrays.size(); ++i) {
            List<Integer> arr = arrays.get(i);
            int firstDiff = Math.abs(arr.get(0) - maxVal);
            int lastDiff = Math.abs(arr.get(arr.size() - 1) - minVal);
            maxDistance = Math.max(maxDistance, Math.max(firstDiff, lastDiff));
            minVal = Math.min(minVal, arr.get(0));
            maxVal = Math.max(maxVal, arr.get(arr.size() - 1));
        }

        return maxDistance;
    }
}
