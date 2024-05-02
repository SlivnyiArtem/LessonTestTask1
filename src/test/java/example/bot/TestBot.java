package example.bot;

import java.util.ArrayList;
import java.util.List;

public class TestBot implements Bot{
    public List<String> getLogger() {
        return logger;
    }

    public List<String> logger;

    public TestBot(){
        this.logger = new ArrayList<>();
    }

    /**
     * Отправка сообщения пользователю
     *
     * @param chatId  идентификатор чата
     * @param message текст сообщения
     */
    @Override
    public void sendMessage(Long chatId, String message) {
        logger.add(message);
    }
}
