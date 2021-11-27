package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByTravelTimeAscComparator;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class SearchTicketManagerTest {
    private TicketRepository repo = new TicketRepository();
    private SearchTicketManager manager = new SearchTicketManager(repo);
    private TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();

    private Ticket first = new Ticket(1, 3000, "SVO", "LED", 55);
    private Ticket second = new Ticket(2, 3000, "SVO", "LED", 45);
    private Ticket third = new Ticket(3, 1500, "SVO", "LED", 50);

    @BeforeEach
    public void setUp() {
        manager.addTicket(first);
        manager.addTicket(second);
        manager.addTicket(third);
    }

    @Test
    void shouldSearchWhenBothSearchConditionsTrueAndSortByTravelTime() {

        Ticket[] expected = new Ticket[]{second, third, first};
        Ticket[] actual = manager.searchBy("SVO", "LED", comparator);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchWhenFirstSearchConditionFalseAndSortByTravelTime() {

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("VKO", "LED", comparator);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchWhenSecondSearchConditionFalseAndSortByTravelTime() {

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("SVO", "AER", comparator);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchWhenBothSearchConditionsFalseAndSortByTravelTime() {

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("VKO", "AER",comparator);

        assertArrayEquals(expected, actual);

    }
}

    class SearchTicketManagerTestWithEmptySet {
        private TicketRepository repo = new TicketRepository();
        private SearchTicketManager manager = new SearchTicketManager(repo);
        private TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();

        @Test
        void shouldSearchBySearchConditionsInEmptySetAndSortByTravelTime() {

            Ticket[] expected = new Ticket[]{};
            Ticket[] actual = manager.searchBy("SVO", "LED", comparator);

            assertArrayEquals(expected, actual);
        }
    }