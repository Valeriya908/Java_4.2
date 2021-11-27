package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class SearchTicketManagerTest {
    private TicketRepository repo = new TicketRepository();
    private SearchTicketManager manager = new SearchTicketManager(repo);

    private Ticket first = new Ticket(1, 3000, "SVO", "LED", 90);
    private Ticket second = new Ticket(2, 2000, "SVO", "LED", 90);
    private Ticket third = new Ticket(3, 2000, "SVO", "LED", 90);

    @BeforeEach
    public void setUp() {
        manager.addTicket(first);
        manager.addTicket(second);
        manager.addTicket(third);
    }

    @Test
    void shouldSearchWhenBothSearchConditionsTrueAndSortByPrice() {

        Ticket[] expected = new Ticket[]{second, third, first};
        Ticket[] actual = manager.searchBy("SVO", "LED");

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchWhenFirstSearchConditionFalseAndSortByPrice() {

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("VKO", "LED");

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchWhenSecondSearchConditionFalseAndSortByPrice() {

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("SVO", "AER");

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchWhenBothSearchConditionsFalseAndSortByPrice() {

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("VKO", "AER");

        assertArrayEquals(expected, actual);

    }
}

    class SearchTicketManagerTestWithEmptySetAndSortByPrice {
        private TicketRepository repo = new TicketRepository();
        private SearchTicketManager manager = new SearchTicketManager(repo);

        @Test
        void shouldSearchBySearchConditionsInEmptySet() {

            Ticket[] expected = new Ticket[]{};
            Ticket[] actual = manager.searchBy("SVO", "LED");

            assertArrayEquals(expected, actual);
        }
    }