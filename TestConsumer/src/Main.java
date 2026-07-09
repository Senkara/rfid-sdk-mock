interface MessageAction {
    void execute(String message);
}

// 1) ESKİ YÖNTEM:
// Interface'i implement eden ayrı bir class yazıyoruz.
class ConsoleMessageAction implements MessageAction {

    @Override
    public void execute(String message) {
        System.out.println("Eski yöntem: " + message);
    }
}

class Message {

    private MessageAction action;

    // Burada dışarıdan bir "davranış" alıyoruz.
    // Yani MessageAction interface'ini implement eden herhangi bir nesne gelebilir.
    public void setMessageAction(MessageAction action) {
        this.action = action;
    }

    // Burada ise daha önce kaydettiğimiz davranışı çalıştırıyoruz.
    public void sendMessage(String text) {
        if (action != null) {
            action.execute(text);
        }
    }
}

public class Main {

    public static void main(String[] args) {

        Message message = new Message();

        // -------------------------------
        // 1) ESKİ HAL
        // -------------------------------
        // Önceden ayrı bir class yazmıştık:
        // ConsoleMessageAction implements MessageAction
        MessageAction oldAction = new ConsoleMessageAction();

        message.setMessageAction(oldAction);
        message.sendMessage("Merhaba Yiğit");


        // -------------------------------
        // 2) ANONYMOUS CLASS HALİ
        // -------------------------------
        // Ayrı class yazmadan, direkt burada interface'i implement ediyoruz.
        message.setMessageAction(new MessageAction() {
            @Override
            public void execute(String message) {
                System.out.println("Anonymous class: " + message);
            }
        });

        message.sendMessage("RFID bulundu");


        // -------------------------------
        // 3) LAMBDA HALİ
        // -------------------------------
        // Çünkü MessageAction içinde sadece 1 tane abstract metot var:
        // void execute(String message);
        message.setMessageAction(messageText -> {
            System.out.println("Lambda: " + messageText);
        });

        message.sendMessage("Tag okundu");
    }
}