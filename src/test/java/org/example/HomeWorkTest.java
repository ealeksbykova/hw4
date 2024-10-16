package org.example;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    HomeWork homeWork;
    TicketManager ticket;


    @BeforeEach
    void setUp() {
        homeWork = new HomeWork();
        ticket = homeWork.managerFabric();
    }

    @SneakyThrows
    @Test
    void managerFabric() {
        addTicket("ticket1");
        addTicket("ticket2");
        addTicket("pension");
        addTicket("pension");
        addTicket("ticket3");
        addTicket("ticket4");
        addTicket("ticket5");

        assertAll(
            () -> assertEquals(3, ticket.next().id),
            () -> assertEquals(4, ticket.next().id),
            () -> assertEquals(1, ticket.next().id),
            () -> assertEquals(2, ticket.next().id),
            () -> assertEquals(5, ticket.next().id),
            () -> assertEquals(6, ticket.next().id),
            () -> assertEquals(7, ticket.next().id)
        );
    }

    @Test
    void check() {
        List<Integer> expectedQueue = generateQueue(1, 4);
        List<String> pairs = generatePairs(expectedQueue);
        assertEquals(expectedQueue, homeWork.check(pairs));
    }

    private List<String> generatePairs(List<Integer> expectedQueue) {
        List<String> pairs = new ArrayList<>();
        Function<Integer, Integer> map = (x) -> (x < 0 || x >= expectedQueue.size()) ? 0 : expectedQueue.get(x);

        for (int i = 0;
             i < expectedQueue.size(); i++) {
            pairs.add(String.format("%d:%d", map.apply(i - 1), map.apply(i + 1)));
        }
        Collections.shuffle(pairs);
        return pairs;
    }

    private List<Integer> generateQueue(int seed, int length) {
        return new Random(seed)
                .ints(1, length * 100)
                .limit(length)
                .boxed()
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private void addTicket(String type) {
        ticket.add(new Ticket(type));
        Thread.sleep(1000);
    }
}