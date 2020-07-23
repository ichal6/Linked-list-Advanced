package com.codecool.datastructure;

public class DoubleLinkedList {
    private class Link {

        private int value;
        private Link before;
        private Link next;

        Link(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }

        Link getNext() {
            return next;
        }

        void setNext(Link next) {
            this.next = next;
        }

        public Link getBefore() {
            return before;
        }

        public void setBefore(Link before) {
            this.before = before;
        }
    }

    private Link head;
    private Link tail;

    public DoubleLinkedList() {
    }

    // Returns the number at 'index'.
    public int access(int index) {
        Link searchNode = head;
        if(index == 0){
            if(head == null){
                throw new IndexOutOfBoundsException();
            }
            return head.getValue();
        }
        for (int indexOfSearchElement = 1; indexOfSearchElement <= index ; indexOfSearchElement++) {
            searchNode = searchNode.getNext();
            if(searchNode == null){
                throw new IndexOutOfBoundsException();
            }
        }
        return searchNode.getValue();
    }

    //Returns value of tail;
    public int accessLastItem(){
        if(tail == null){
            throw new IndexOutOfBoundsException();
        }
        return tail.getValue();
    }

    // Returns the index of 'number' if it's in the array, otherwise -1;
    public int search(int number) {
        Link searchNode = head;
        int index = 0;
        while (searchNode != null){
            if(searchNode.getValue() == number){
                return index;
            }
            index++;
            searchNode = searchNode.getNext();
        }
        return -1;
    }

    // Returns the index of 'number' if it's in the array, otherwise -1;
    public int searchFromEnd(int number) {
        Link searchNode = tail;
        int index = 0;
        while (searchNode != null){
            if(searchNode.getValue() == number){
                return index;
            }
            index++;
            searchNode = searchNode.getBefore();
        }
        return -1;
    }

    // Inserts 'number' at 'index' into the array shifting elements if necessary.
    //
    // e.g. the result of inserting 42 at index 3 into [0, 1, 2, 3, 4] is [0, 1, 2, 42, 3, 4]
    public void insert(int index, int number) {
        Link insertNode = new Link(number);
        if(index == 0){
            Link oldHead = head;
            this.head = insertNode;
            this.head.setNext(oldHead);
            if(this.head.getNext() == null){
                this.tail = insertNode;
            }
            return;
        }

        if(index < 0){
            throw new IndexOutOfBoundsException();
        }

        Link nodeBeforeIndex = head;

        for (int indexOfSearchElement = 0; indexOfSearchElement < index - 1; indexOfSearchElement++) {
            nodeBeforeIndex = nodeBeforeIndex.getNext();
            if (nodeBeforeIndex == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        Link nodeAfterIndex = nodeBeforeIndex.getNext();
        nodeBeforeIndex.setNext(insertNode);
        insertNode.setNext(nodeAfterIndex);
        insertNode.setBefore(nodeBeforeIndex);

        if(nodeAfterIndex == null){
            this.tail = insertNode;
        }else{
            nodeAfterIndex.setBefore(insertNode);
        }
    }

    // Inserts 'number' at 'tail' into the array shifting elements if necessary.
    //
    // e.g. the result of inserting 42 into [0, 1, 2, 3, 4] is [0, 1, 2, 3, 4, 42]
    public void insertLastElement(int number) {
        Link insertNode = new Link(number);

        Link oldTail = this.tail;
        this.tail = insertNode;
        this.tail.setBefore(oldTail);
        if(oldTail != null){
            this.head = this.tail;
            oldTail.setNext(this.tail);
        }
    }

    // Deletes the element at 'index' from the array.
    //
    //  e.g. the result of deleting index 2 from [0, 1, 2, 3, 4] is [0, 1, 3, 4]
    public void delete(int index) {
        if (index == 0) {
            if (head == null) {
                throw new IndexOutOfBoundsException();
            } else {
                head = head.getNext();
                head.setBefore(null);
            }
            return;
        }
        Link elementBeforeIndex = head;
        while (index - 1 > 0) {
            elementBeforeIndex = elementBeforeIndex.getNext();
            index--;
            if (elementBeforeIndex == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        Link elementAtIndex = elementBeforeIndex.getNext();
        if (elementAtIndex == null) {
            throw new IndexOutOfBoundsException();
        }
        Link elementAfterIndex = elementAtIndex.getNext();
        elementBeforeIndex.setNext(elementAfterIndex);
        if(elementAfterIndex != null){
            elementAfterIndex.setBefore(elementAtIndex);
        }
    }
}
