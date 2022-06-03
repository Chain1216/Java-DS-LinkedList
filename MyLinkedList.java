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
 * Penn email: zhaoqinw@seas.upenn.edu>
 * Date: 2022-6-02
 */

public class MyLinkedList {

    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1673689278942178657L;
    static class Node {
        @SuppressWarnings("unused")
        private static final long serialVersionUID = -539294075156871991L;
        String value;
        Node next;

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        Node(String value) {
            this(value, null);
        }
    }

    protected Node head = null;
    protected Node tail = null;
    protected int size = 0;

    public void addFirst(String value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
        size++;
    }

    public void addLast(String value) {
        Node newNode = new Node(value);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void add(int index, String value) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            Node newNode = new Node(value);
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            if (current.next == null) {
                tail = newNode;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        }
    }

    public boolean contains(String value) {
        Node current = head;
        while (current != null) {
            if (current.value == value || current.value != null && current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
        } else {
            return;
        }
        if (head == null) {
            tail = null;
        }
        if (size > 0)
            size--;
    }

    public void removeLast() {
        if (head == null) { // empty list
            return;
        } else if (head == tail) {
            // single element list
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            current.next = null;
        }
        size--;
    }

    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        else if (index == 0)
            removeFirst();
        else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            if (current.next == null) {
                tail = current;
            }
            size--;
        }
    }

    /*
     * Implement the methods below. Please do not change their signatures!
     */

    public void reverse() {
    	Node left_pointer = null;  // Three pointer methods
    	Node mid_pointer = this.head;
    	Node right_pointer = null;
    	this.tail = mid_pointer;
    	
    	while (mid_pointer != null) {
    		right_pointer = mid_pointer.next;
    		mid_pointer.next = left_pointer;
    		left_pointer = mid_pointer;
    		mid_pointer = right_pointer;
	
    	}
         	
        this.head = left_pointer;
        

    }

    public void removeMaximumValues(int N) {
        for (int i = 0; i < N; i++) {
        	
        	if (N <=0 || this.head == null) {
        		break;
        	}
        	
        	String max = findMax(this.head);
            this.head = removeMax(this.head,max); //update the head after one time removing
        }
        
        int new_size = 0;
        Node current = this.head;

        while(current != null){
            new_size += 1;
            current = current.next;
        }

        this.size = new_size;
        

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

    public Boolean containsSubsequence(MyLinkedList two) {
    	if (two == null) {
        	return true;
        }
        
        Node curr_other = two.head;
        Node curr_head = this.head;
        
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
