package example.container;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест функционала класса Container
 */
class ContainerTest {
    private Container container;

    /**
     * Инициализация объекта-контейнера
     */
    @BeforeEach
    void init() {
        this.container = new Container();
    }

    /**
     * добавление нового элемента в контейнер
     */
    @Test
    void add() {
        Item item = new Item(1);
        container.add(item);
        assertEquals(item, container.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
    }

    /**
     * удаление элемента из контейнера
     */
    @Test
    void remove() {
        Item item1 = new Item(1);
        Item item2 = new Item(2);
        container.add(item1);
        container.remove(item1);
        assertFalse(container.contains(item1));
    }

    /**
     * получение элемента из контейнера
     */
    @Test
    void get() {
        Item item1 = new Item(1);
        container.add(item1);
        assertEquals(item1, container.get(0));
    }

    /**
     * проверка, что нельзя получить элемент, удаленный из коллекции
     */
    @Test
    void notGetUnexisted(){
        Item item1 = new Item(1);
        container.add(item1);
        container.remove(item1);
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(0));
    }

    /**
     * проверка правильного изменения размера содержимого
     * контейнера при добавлении и удалении элементов
     */
    @Test
    void size() {
        assertEquals(0, container.size());
        Item item1 = new Item(1);
        Item item2 = new Item(2);
        container.add(item1);
        container.add(item2);
        assertEquals(2, container.size());
        container.remove(item2);
        assertEquals(1, container.size());
    }

    /**
     * проверка возможности получить элемент из контейнера
     */
    @Test
    void contains() {
        Item item1 = new Item(1);
        Item item2 = new Item(2);
        container.add(item1);
        assertTrue(container.contains(item1));
        assertFalse(container.contains(item2));
    }
}