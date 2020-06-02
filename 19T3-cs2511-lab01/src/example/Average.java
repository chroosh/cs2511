package example;

/**
 * Average example
 * @author Aarthi
 */
public class Average {

        /**
         * Returns the average of an array of numbers
         * @param the array of integer numbers
         * @return the average of the numbers
         */
        public float average(int[] nums) {
            float result = 0;
						for (float  i : nums) {
							result += i;
						}

						result = result/nums.length;
            // Add your code
            return result;
        }

        public static void main(String[] args) {
            // Add your code
						int[] nums = new int[]{ 1, 2, 3, 4, 5 };
						float result = new Average().average(nums);
						System.out.printf("The result is " + result);
        }

}

