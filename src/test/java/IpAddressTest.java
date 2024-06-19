import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IpAddressTest {

    @Test
    void getIndexTest() {
        assertEquals(1, IpAddress.getIndex("192.168.0.1"));
    }

    @Test
    void getNewIpTest() {
        assertEquals("192.168.0.3", IpAddress.getNewIp("192.168.0.1", 3));
    }
}