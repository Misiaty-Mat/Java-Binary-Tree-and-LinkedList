package abstractCls;

public class MyLinkedList implements NodeList {

    private ListItem root = null;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if(this.root == null) {
            //the list was empty, item becomes the first item in a list
            this.root = newItem;
            return true;
        }

        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = (currentItem.compareTo(newItem));
            if (comparison < 0) {
                // newItem is greater, move right if possible
                if (currentItem.next() != null) {
                    currentItem = currentItem.next();
                }
                else {
                    //there is on next, so set as a last element
                    currentItem.setNext(newItem).setPrevious(currentItem);
                    return true;
                }
            }
            else if(comparison > 0) {
                // newItem is less, insert before
                if (currentItem.previous() != null) {
                    currentItem.previous().setNext(newItem).setPrevious(currentItem.previous());
                    newItem.setNext(currentItem).setPrevious((newItem));
                }
                else {
                    // previous is the root
                    newItem.setNext(this.root).setPrevious((newItem));
                    this.root = newItem;
                }
                return true;
            }
            else {
                //equal
                System.out.println("\"" + newItem.getValue() + "\" is already in the List.");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());
        }

        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if (comparison == 0) {
                //found item to delete
                if (currentItem == this.root) {
                    this.root = currentItem.next();
                }
                else {
                    currentItem.previous().setNext(currentItem.next());
                    if (currentItem.next() != null) {
                        currentItem.next().setPrevious(currentItem.previous());
                    }
                }
                return true;
            }
            else if (comparison < 0) {
                // item is further ahead
                currentItem = currentItem.next();
            }
            else {
                //item not in a list
                return false;
            }
        }

        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty");
        }
        else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }
        }
        /*  if (root != null) {
            System.out.println(root.getValue());
            traverse(root.next());
        }*/
    }
}
