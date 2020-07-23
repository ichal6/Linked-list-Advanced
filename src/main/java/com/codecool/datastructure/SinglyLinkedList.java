package com.codecool.datastructure;

public class SinglyLinkedList {

    private class Link {

        private int value;
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
    }

    private Link head;

    public SinglyLinkedList() {
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

    // Returns the index of 'number' if it's in the array, otherwise -1;
    public int search(int number) {
        return 0;
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
        elementBeforeIndex.setNext(elementAtIndex.getNext());
    }
}
