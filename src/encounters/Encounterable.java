package encounters;

public interface Encounterable {

    void encounter();
    void talk(String talk);

    void handleResponse(Option option);
}
