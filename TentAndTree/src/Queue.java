public class Queue {

    private Node front;
    private Node rear;

    //Inner class to represent a Node in LinkedList
    private class Node {
        private Element data;
        private Node next;

        public Node(Element data) {
            this.data = data;
            this.next = null;
        }
    }

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    boolean isEmpty() {
        return front == null;
    }

    void enqueue(Element element) {
        Node newNode = new Node(element);

        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    Element dequeue() {
        Element dequeuedElement = null;

        if (front != null) {
            dequeuedElement = front.data;
            front = front.next;

            if (front == null) {
                rear = null;
            }
        }
        return dequeuedElement;
    }
}
