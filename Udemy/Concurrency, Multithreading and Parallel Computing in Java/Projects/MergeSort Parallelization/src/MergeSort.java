public class MergeSort {
    private int[] nums;
    private int[] tempArray;

    public MergeSort(int[] nums) {
        this.nums = nums;
        tempArray = new int[nums.length];
    }

    public void showArray(){
        for(int i = 0;i<nums.length;i++)
            System.out.println(nums[i]+" ");
    }
    public void sort(){
        mergeSort(0, nums.length-1);
    }

    public void mergeSort(int low, int high) {
        if(low >= high)
            return;
        int middleIndex = (low+high)/2;
        mergeSort(low,middleIndex);
        mergeSort(middleIndex+1,high);

        merge(low,middleIndex,high);
    }

    public void merge(int low, int middle, int high) {
        for(int i = low;i<=high;i++)
            tempArray[i] = nums[i];
        int i = low;
        int j = middle + 1;
        int k = low;

        while(i<=middle && j<=high){
            if(tempArray[i] < tempArray[j]){
                nums[k] = tempArray[i];
                ++i;
            }
            else{
                nums[k] = tempArray[j];
                ++j;
            }
            ++k;
        }
        while(i<=middle){
            nums[k] = tempArray[i];
            ++k;
            ++i;
        }
        while(j<=high){
            nums[k] = tempArray[j];
            ++k;
            ++j;
        }

    }


    private void swap(int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
