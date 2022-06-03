/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/ >
 * Signed,
 * Author: Zhaoqin Wu
 * Penn email: <zhaoqinw@seas.upenn.edu>
 * Date: 2022-06-01
 */

public class MyRawLinkedList {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1561306366555780559L;

    static class Node {
        @SuppressWarnings("unused")
        private static final long serialVersionUID = -3505677833599614054L;
        String value;
        Node next = null;

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        Node(String value) {
            this(value, null);
        }
    }

    /* This is intentionally left private so that you can't erroneously try to
     * instantiate a `new MyRawLinkedList()`
     */
    private MyRawLinkedList() {}

    /*
     * These methods included as examples for how to use Node as a linked list.
     */
    public static String listToString(Node head) {
        String ret = "";
        while (head != null) {
            ret += "\"" + head.value + (head.next == null ? "\" " : "\", ");
            head = head.next;
        }
        return "[ " + ret + "]";
    }

    public static void print(Node head) {
        System.out.println(listToString(head));
    }

    /*
     * Do not call this method in your code; it is not efficient. It is just for our
     * test cases.
     */
    public static String get(Node head, int index) {
        if (index < 0 || index >= size(head)) {
            throw new IndexOutOfBoundsException();
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        }
    }

    /* Do not call this method in your code. It is just for the test cases. */
    public static boolean contains(Node head, String value) {
        Node current = head;
        while (current != null) {
            if (current.value == value || current.value != null && current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /* Do not call this method in your code. It is just for the test cases. */
    public static int size(Node head) {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    public static void main(String[] args) {
        Node list1 = new Node("One", new Node("Two", new Node("Three", null)));
        print(list1);

        Node args_as_list = null;
        for (int i = args.length - 1; i >= 0; i--)
            args_as_list = new Node(args[i], args_as_list);

        print(args_as_list);

        Node list2 = null;
        list2 = new Node("a", list2);
        list2 = new Node("50", list2);
        list2 = new Node("c", list2);
        Node list50 = null;
        
        Node list0 = null;
		Node list11 = new Node("a", list0);
		Node list22 = new Node("50", list11);
		Node list3 = new Node("KANGAROO", list22);
		Node list4 = new Node("d", list3);
		Node list5 = new Node("b", list4);
		Node list6 = new Node("c", list5);
		
        print(list6);
        

        System.out.println(findMax(list6));
        print(removeMaximumValues(list6,1));
        System.out.println(containsSubsequence(list6,list2));
        
       
    }


    public static Node reverse(Node head) {
    	Node left_pointer = null;  // Three pointer methods
    	Node mid_pointer = head;
    	Node right_pointer = null;
    	while (mid_pointer != null) {
    		right_pointer = mid_pointer.next;
    		mid_pointer.next = left_pointer;
    		left_pointer = mid_pointer;
    		mid_pointer = right_pointer;
	
    	}
         	
        return left_pointer;
    }

    public static Node removeMaximumValues(Node head, int N) {    
        
        for(int i = 0; i < N; i++) {       //Remove the maximum values N times
        	
        	if (N <= 0  || head == null) {      
            	return head;
            }
        	
        	String max = findMax(head);
            head = removeMax(head,max); //update the head after one time removing
        }

		return head;
        
    }
    
    public static String findMax(Node head) {
    	String max = head.value;
    	while(head.next != null) {
    		int result = max.compareTo(head.next.value);
    		if(result<0) {
    			max = head.next.value;
    		}
    		head = head.next;
    	}
		return max;
    	
    }
    
    public static Node removeMax(Node head, String max) { 
    	
    	while(head.value == max) {          // in case that the leading node is the largest one
    		if (head.next == null) {        
    			return null;                // in case that all nodes are the largest one
    		}else  {
    			head = head.next;           // remove the leading node and update the head
    		}
    	}
    	
    	Node current = head;               // after removing the leading node, update the current node to the head node
    	
		while(current.next != null) {     
			if(current.next.value == max) {
				current.next = current.next.next; // remove any nodes in the middle with the largest value 
			} else {
			current = current.next; // update the current 
		}
	}
    	
    	return head;
    }



    public static boolean containsSubsequence(Node head, Node other) {
    	
        if (other == null) {
        	return true;
        }
        
        Node curr_other = other;
        Node curr_head = head;
        
        while(curr_other != null && curr_head != null) {   
        	if(curr_other.value == curr_head.value) {  // if one element in other = one element in head
        		curr_other = curr_other.next;          // update both element in head and other
        		curr_head = curr_head.next;
        	}else {
        		curr_head = curr_head.next;            // in case of not equal, only update the element in head
        	}
        	
        	if (curr_other == null) {                  // other is a subsequence of head
        		return true;
        	}
        }
        

        return false;
    }

}
