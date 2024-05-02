package example.note;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест функционала класса NoteLogic
 */
class NoteLogicTest {
    private NoteLogic note;

    @BeforeEach
    void init(){
        this.note = new NoteLogic();
    }

    /**
     * тест на обработку комманды /add
     */
    @Test
    public void handleAddNoteTest(){
        String addResult = note.handleMessage("/add node1");
        assertEquals("Note added!", addResult);
        assertEquals(
                """
                Notes:
                1. node1
                """, note.handleMessage("/notes"));
    }

    /**
     * тест на обработку комманды /edit
     */
    @Test
    public void handleEditNoteTest(){
        note.handleMessage("/add node1");
        String editResult = note.handleMessage("/edit node1 node11");
        assertEquals("Note edited!", editResult);
        assertEquals(
                """
                Notes:
                1. node11
                """, note.handleMessage("/notes"));
    }

    /**
     * тест на обработку комманды /del
     */
    @Test
    public void handleDeleteNoteTest(){
        note.handleMessage("/add node1");
        note.handleMessage("/add node2");
        String deleteResult = note.handleMessage("/del node1");
        assertEquals("Note deleted!", deleteResult);
        assertEquals(
                """
                Notes:
                2. node2
                """, note.handleMessage("/notes"));
    }

    /**
     * тест на обработку комманды /notes
     */
    @Test
    public void notesTest() {
        assertEquals("Your notes:", note.handleMessage("/notes"));
        note.handleMessage("/add node1");
        assertEquals(
                """
                Notes:
                1. node1
                """, note.handleMessage("/notes"));
    }
}