package example.bot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotLogicTest {
    private BotLogic botLogic;
    private TestBot bot;
    private User user;
    @BeforeEach
    public void init(){
        bot = new TestBot();
        botLogic = new BotLogic(bot);
        user = new User(1L);
    }

    /**
     * Обработка команды /test
     */
    @Test
    void processCommandTest() {
        botLogic.processCommand(user, "/test");
        assertEquals(State.TEST, user.getState());
        assertEquals("Вычислите степень: 10^2", bot.getLogger().getLast());
    }

    /**
     * Обработка команды /notify
     */
    @Test
    void processCommandNotify() {
        botLogic.processCommand(user, "/notify");
        assertEquals(State.SET_NOTIFY_TEXT, user.getState());
        String expectedMsg = "Введите текст напоминания";
        assertEquals(expectedMsg, bot.getLogger().getLast());
    }

    /**
     * Обработка команды /repeat
     */
    @Test
    void processCommandRepeat() {
        Question wrongQ = new Question("Вычислите степень: 10^2", "100");
        botLogic.processCommand(user, "/test");
        user.addWrongAnswerQuestion(wrongQ);
        botLogic.processCommand(user, "/repeat");
        assertEquals(State.REPEAT, user.getState());
        String expectedMsg = "Вычислите степень: 10^2";
        assertEquals(expectedMsg, bot.getLogger().getLast());
        assertEquals(2, bot.logger.size());
    }

    /**
     * Обработка команды /repeat, при условии, что нет вопросов для повторения
     */
    @Test
    void processCommandRepeatNoQuestions() {
        botLogic.processCommand(user, "/repeat");
        assertEquals(State.INIT, user.getState());
        String expectedMsg = "Нет вопросов для повторения";
        assertEquals(expectedMsg, bot.getLogger().getLast());
    }
}