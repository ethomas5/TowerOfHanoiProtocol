import java.util.ArrayDeque;
import java.util.Iterator;

public class Peg {
    private int size;
    private static int maxSize;
    private int topDiskSize = 0;
    ArrayDeque<Disk> storing = new ArrayDeque<>();

    /***
     * Simple constructor for each of the "peg" objects in the game. Although utalizing an ArrayDeque for the storing,
     * it functions completely as a Stack. The java implementation of a Stack has some issues due to the way it's implemented.
     * @param numDisksParam The number of disks to add on this peg
     * @param maxSizeParam The max number of disks that this peg will be able to hold in the current game
     */
    public Peg(int numDisksParam, int maxSizeParam) {
        for (int i = numDisksParam; i > 0; i--) {
            if (i == 1) {
                topDiskSize = 1;
            }
            storing.push(new Disk(i));
            size++;
        }
        maxSize = maxSizeParam;
    }

    /***
     *
     * @return number of disks on the this peg
     */
    public int getSize() {
        return size;
    }

    /***
     *
     * @return the smallest disk on this peg
     */
    public int getTopDiskSize() {
        return topDiskSize;
    }

    /***
     *
     * @param diskSize the size of the disk attempting to be added to this peg.
     * @throws Exception Only thrown if the disk being added is larger than the current smallest disk
     */
    public void add(int diskSize) throws Exception {
        if (getTopDiskSize() != 0) {
            if (diskSize < getTopDiskSize()) {
                storing.push(new Disk(diskSize));
                topDiskSize = diskSize;
                size++;
            } else {
                throw new Exception("Disk being added is larger than current smallest disk.");
            }
        } else {
            storing.add(new Disk(diskSize));
            size++;
            topDiskSize = diskSize;
        }
    }

    /***
     * Small variation of the ArrayDeque .pop() function that decrements size and sets the new smallest disk value
     * @return returns the width of the disk that was removed, or -1 if peg is empty
     */
    public int removeFromTop() {
        if (size != 0) {
            size--;
            int removed =  storing.pop().getWidth();
            if (size != 0) {
                topDiskSize = storing.peek().getWidth();
            } else {
                topDiskSize = 0;
            }
            return removed;
        } else {
            return -1;
        }
    }

    /***
     *
     * @param level the level of the peg you want to print, the top being 0 and bottom being maxSize - level
     * @return A string representation of the level, or a buffer disk when the level is lower than the maxSize and
     * greater than the current size, and throws an error if the level is completely invalid.
     */
    public String printLevel(int level) {
        if (level >= 0 && level < size) {
            Iterator<Disk> iter = storing.iterator();
            for (int i = 0; i < level; i++) {
                iter.next();
            }
            return iter.next().toString();
        } else if (level >= maxSize){
            throw new IndexOutOfBoundsException("Cannot printLevel(): " + level + " because it is greater than maxSize");
        } else {
            return " ".repeat(maxSize) + "|" + " ".repeat(maxSize);
        }
    }

    /***
     *
     * @return a console representation of the entire peg
     */
    @Override
    public String toString() {
        int bufferZone = maxSize - size;
        String pegStr = "";
        Iterator<Disk> iter = storing.iterator();
        for (int i = 0; i < bufferZone; i++) {
            pegStr = pegStr + new Disk(0) + "\n";
        }
        while (iter.hasNext()) {
            pegStr = pegStr + iter.next().toString() + "\n";
        }
        return pegStr;
    }

    /**
     * Class that represents each of the disks on a peg
     */
    static class Disk {
        private int width;

        public Disk(int width) {
            this.width = width;
        }
        public int getWidth() {
            return width;
        }

        /***
         *
         * @return string representation of the disk, along with buffering the outside to ensure it
         * takes up maxSize * 2 + 1 characters
         */
        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            int width = getWidth();
            str.append(" ".repeat(maxSize - width));
            str.append("_".repeat(width));
            str.append("|");
            str.append("_".repeat(width));
            str.append(" ".repeat(maxSize - width));
            return str.toString();
        }
    }
}
