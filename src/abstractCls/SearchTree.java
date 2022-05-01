package abstractCls;

public class SearchTree implements NodeList {
    private ListItem root = null;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if (this.root == null) {
            // tree has no items, add as a head of a tree
            this.root = newItem;
            return true;
        }

        // tree has items, traverse through a tree to find a good place for item
        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(newItem);
            if (comparison < 0) {
                // newItem is greater, move right if possible
                if (currentItem.next() != null) {
                    currentItem = currentItem.next();
                }
                else {
                    // no item at this place, add newItem here
                    currentItem.setNext(newItem);
                    return true;
                }
            }
            else if (comparison > 0) {
                // newItem is less, move left if possible
                if (currentItem.previous() != null) {
                    currentItem = currentItem.previous();
                }
                else {
                    // no item at this place, add newItem here
                    currentItem.setPrevious(newItem);
                    return true;
                }
            }
            else {
                System.out.println("\"" + newItem.getValue() + "\" is already in a tree!");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item: \"" + item.getValue() + "\"");
        }
        ListItem currentItem = this.root;
        ListItem parentItem = currentItem;

        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if (comparison < 0) {
                parentItem = currentItem;
                currentItem = currentItem.next();
            }
            else if (comparison > 0) {
                parentItem = currentItem;
                currentItem = currentItem.previous();
            }
            else {
                // item found
                performRemoval(currentItem, parentItem);
                return true;
            }
        }
        return false;
    }

    private void performRemoval(ListItem item, ListItem parent) {
        if (item.next() == null) {
            if (parent.next() == item) {
                parent.setNext(item.previous());
            }
            else if (parent.previous() == item) {
                parent.setPrevious(item.previous());
            }
            else {
                this.root = item.previous();
            }
        }
        else if (item.previous() == null) {
            if (parent.next() == item) {
                parent.setNext(item.next());
            }
            else if (parent.previous() == item) {
                parent.setPrevious(item.next());
            }
            else {
                this.root = item.next();
            }
        }
        else {
            ListItem current = item.next();
            ListItem leftMostParent = item;
            while (current.previous() != null) {
                leftMostParent = current;
                current = current.previous();
            }

            item.setValue(current.getValue());
            if (leftMostParent == item) {
                item.setNext(current.next());
            }
            else {
                leftMostParent.setPrevious(current.next());
            }
        }
    }

    @Override
    public void traverse(ListItem root) {
        // recursive method
        if (root != null) {
            traverse(root.previous());
            System.out.println(root.getValue());
            traverse(root.next());
        }
    }
}
