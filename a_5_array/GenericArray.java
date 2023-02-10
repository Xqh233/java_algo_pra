package a_5_array;

public class GenericArray<T> {
    private T[] data;
    private int count;

    public GenericArray(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.count = 0;
    }

    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int find(T val) {
        for (int i = 0; i < count; i++) {
            if (data[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public void set(int index, T val) {
        checkIndex(index);
        data[index] = val;
    }

    public void insert(int index, T val) {
        checkIndexForAdd(index);
        if (count == data.length) {
            resize(data.length * 2);
        }
        for (int i = count - 1; i >= index; i--) {
            data[i + 1] = data[i]; 
        }
        data[index] = val;
        count++;
    }

    public void add(T val) {
        insert(count, val);
    }
    public T remove(int index) {
        checkIndexForAdd(index);
        if (isEmpty()) return null;
        T removeVal = data[index];
        for (int i = index + 1; i < count; i++) {
            data[i - 1] = data[i];
        }
        data[count--] = null;
        if (count == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return removeVal;
    }
    public T removeElement(T val) {
        int index = find(val);
        if (index == -1) return null;
        T removeVal = data[index];
        for (int i = index + 1; i < count; i++) {
            data[i - 1] = data[i];
        }
        data[count--] = null;
        if (count == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return removeVal;
    }

    private void resize(int size) {
        T[] newArr = (T[]) new Object[size];
        for (int i = 0; i < count; i++) {
            newArr[i] = data[i];
        }
        data = newArr;
    }


    // check whether visitable [0, count)
    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("can not access this index");
        }
    }
    // addable, can insert in data last [0, size]
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > count) {
            throw new IllegalArgumentException("can not access this index");
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array count = %d, capacity = %d \n", count, data.length));
        builder.append('[');
        for (int i = 0; i < count; i++) {
            builder.append(data[i]);
            if (i != count - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    public static void main(String[] args) {
        GenericArray<String> arr = new GenericArray<>(2);
        arr.add("a");
        arr.add("b");
        // 测试扩容
        System.out.println(arr);
        arr.add("c");
        System.out.println(arr);
        arr.add("d");
        arr.add("e"); 
        System.out.println(arr);
        // 测试插入搬移
        arr.insert(2, "x");
        System.out.println(arr);
        arr.remove(4);
        System.out.println(arr);
        arr.removeElement("b");
        System.out.println(arr);
        // 缩容
        arr.removeElement("a");
        arr.removeElement("c");
        System.out.println(arr);
    }
}
