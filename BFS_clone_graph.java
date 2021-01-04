// Time Complexity : O(V+E), where is the number of vertices and E is the number o edges in the graph
// Space Complexity :O(V+E), where is the number of vertices and E is the number o edges in the graph (queue and map space)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

//Three liner explanation of your code in plain English
//1. Create a queue(to perform BFS) and a Map(to avoid creation of duplicate clones). Add the node in the queue. Create a clone of
        //the node and add to the map, the node and its value as its clone.
//2. .Remove the first node from the queue Iterate over its neighbors and check if it has a entry in the map(it's clone is already 
        //created), If not created, then follow step 1. Add the neighbor clone in the neighbor list of the clone node in the map
//3. Keep doing above two steps till queue becomes empty and in the end return clone node (that holds the graph clone)

// Your code here along with comments explaining your approach

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        //edge case
        if(node == null) return node;
        
        //Create a hashmap to store the nodes with it's corresponding clones
        HashMap<Node, Node> map = new HashMap<>();
        //create a queue to perfrom BFS
        Queue<Node> q = new LinkedList<>();
        
        //add the node in the queue. create it's clone and add the mapping in the map
        q.add(node);
        Node clone = new Node(node.val);
        map.put(node, clone);
        
        //perform BFS
        while(!q.isEmpty()){
            //get the first node from the queue
            Node curr = q.poll();
            //iterate over it neighbors
            List<Node> connections = curr.neighbors;
            for(int i=0; i<connections.size(); i++){
                //create a clone of the neighbors if map doesnot have an entry
                if(!map.containsKey(connections.get(i))){
                    Node cloneChild = new Node(connections.get(i).val);
                    //add it to the queue and the map
                    q.add(connections.get(i));
                    map.put(connections.get(i), cloneChild);
                }
                //add the cloneChild tothe clone node's neighbors list
                map.get(curr).neighbors.add(map.get(connections.get(i))); 
            }
        }
        //return the clone node
        return map.get(node);
    }
}