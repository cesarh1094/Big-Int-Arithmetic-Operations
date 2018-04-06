public class ArrayLongIntList implements LongIntList<ArrayEntry> {
    //    private int n;
    private ArrayEntry[] entries;

    public ArrayLongIntList() {
        entries = new ArrayEntry[1];
    }

    public void insertFirst(int value) {
        if (this.first() == null) {
            this.entries[0] = new ArrayEntry(value, 0);
            return;
        }

        // Create new array of ArrayEntry type
        ArrayEntry[] new_entries = new ArrayEntry[this.size() + 1];
        // Set value at first index of array
        new_entries[0] = new ArrayEntry(value, 0);

        for (ArrayEntry entry : this.entries) {
            new_entries[entry.getIndex() + 1] = entry;
            new_entries[entry.getIndex() + 1].setIndex(entry.getIndex() + 1);
        }

        this.entries = new_entries;
    }

    public void insertLast(int value) {
        if (this.first() == null) {
            this.entries[0] = new ArrayEntry(value, 0);
            return;
        }

        // Create new array of ArrayEntry type
        ArrayEntry[] new_entries = new ArrayEntry[this.size() + 1];
        // Set value at last index of array
        new_entries[new_entries.length - 1] = new ArrayEntry(value, new_entries.length - 1);

        for (ArrayEntry entry : this.entries) {
            new_entries[entry.getIndex()] = entry;
            new_entries[entry.getIndex()].setIndex(entry.getIndex());
        }

        this.entries = new_entries;
    }

    public ArrayEntry first() {
        return this.entries[0];
    }

    public ArrayEntry last() {
        return this.entries[this.entries.length - 1];
    }

    public boolean isFirst(ArrayEntry p) {
        return p.getIndex() == 0;
    }

    public boolean isLast(ArrayEntry p) {
        return p.getIndex() == this.entries.length - 1;
    }

    public ArrayEntry before(ArrayEntry p) {
        return (p.getIndex() > 0) ? this.entries[p.getIndex() - 1] : null;
    }

    public ArrayEntry after(ArrayEntry p) {
        return (p.getIndex() < this.entries.length - 1) ? this.entries[p.getIndex() + 1] : null;
    }

    public boolean isEmpty() {
        return this.entries.length == 0 || this.isDeepEmpty();
    }

    private boolean isDeepEmpty() {
        for (ArrayEntry entry : this.entries) {
            if (entry != null)
                return false;
        }

        return true;
    }

    public int size() {
        return this.entries.length;
    }

    public static void main(String args[]) {
        ArrayLongIntList list = new ArrayLongIntList();

        list.insertFirst(1);
        list.insertLast(10);
        list.insertFirst(5);

        int size = list.size();
        System.out.println("First entry in the list: " + list.first().getValue());
        System.out.println("Last entry in the list: " + list.last().getValue());

        ArrayEntry iterator = list.first();

        while (iterator != null) {
            System.out.println("Entry #" + iterator.getIndex() + " in the list: " + iterator.getValue());
            iterator = list.after(iterator);
        }
    }
}