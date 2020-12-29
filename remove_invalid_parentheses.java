// Time Complexity : O(n!), where n is the size of the String (at each level we remove 1 extra parentheses)
// Space Complexity : O(n), where n is the size of the String (size of the queue)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : I am not sure about the time complexity, as for each substring we are checking if it is a valid
                                             //String or no, (extra O(n)) .can you please explain how much exponential?

//Three liner explanation of your code in plain English
//Intuition -> You literally follow the question, try removing 1 parentheses at a time at every index and check if it can form a valid
                //parentheses (All at one level, hence BFS). If not, go 1 level down to remove 1 more character at every index and 
                //check again it is a valid parentheses
//1. Create a set and a queue. Add input string in the set and the queue to start BFS. Create a flag, to mark if a valid parenthese is
            //is formed at oone level, so we dont go and explore the further levels
//2. Maintian a size variable. remove the first element in the queue and check if it a valid parentheses. if yes, make the flag true.
//3. Now, if the flag is still false (meaning you did not find a valid parentheses yet at the level)add the childrens (formed 
            // and add the string in the result by removing 1 less character at each index) of the current string in the queue 
            //and set, ONLY if that child doesnot exist in the set(you don't want to add more than 1 similar substring as they
            // will generate duplicate answers).
//return the result

// Your code here along with comments explaining your approach

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        //result to return
        List<String> result = new ArrayList<>();
        //edge case
        if(s == null) return result;
        
        //set to make sure you perform BFS on the unique subStrings
        Set<String> set = new HashSet<>();
        //create a queue to do BFS
        Queue<String> q = new LinkedList<>();
        //boolean to check if a valid parantheses is found at a level
        boolean found = false;
        
        //put the String s as a start for the BFS
        q.add(s);
        set.add(s);
        //perform BFS
        while(!q.isEmpty() && found == false){
            //maintain a size variable to process all the string at one level
            int size = q.size();
            for(int i=0; i<size; i++){
                //get the queue top
                String curr = q.poll();
                //check if it is a valid parentheses and add to the result
                if(isValid(curr)){
                    result.add(curr);
                    found = true; 
                }
                if(!found){
                    //process the all the children of curr
                    for(int j=0; j<curr.length(); j++){
                        //if character at j is a letter, move on to the next children
                        if(Character.isLetter(curr.charAt(j))) continue;
                        //omit the character at the jth index
                        String child = curr.substring(0,j) + curr.substring(j+1);
                        //check if it a unique subString and add it to the queue and set
                        if(!set.contains(child)){
                            q.add(child);
                            set.add(child);
                        }
                    }
                }
            }
        }
        return result;
    }
    //function to check if a String is a valid parentheses
    private boolean isValid(String s){
        //variable to maintain the count of opening and closing parentheses
        int count = 0;
        
        //iterate over the String
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '(') count++;
            else if(count == 0 && s.charAt(i) == ')') return false;
            else if(s.charAt(i) == ')') count --;
        }
        return count == 0;
    }
}