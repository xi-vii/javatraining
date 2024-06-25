import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumTest {

    @Test
    void getMinTest() {
        Num num = new Num(5,25);
        assertEquals(5, num.getMin());
    }

    @Test
    void getMaxTest() {
        Num num = new Num(5,25);
        assertEquals(25, num.getMax());
    }
}