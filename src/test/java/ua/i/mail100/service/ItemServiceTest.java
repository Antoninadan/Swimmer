package ua.i.mail100.service;

import ua.i.mail100.AppRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(AppRunner.class)
@ActiveProfiles("test")
class ItemServiceTest {
//
//    @Autowired
//    ItemService itemService;
//
//    @Test
//    void saveToMongo() {
//        Franchise item = new Franchise();
//        item.setName("test name");
//        item.setCode("test code");
//        item.setPrice(12345);
//
//        itemService.saveToMongo(item);
//
//        List<Franchise> items = itemService.getAllFromMongo();
//
//        assertNotNull(items);
//        assertTrue(items.size() >= 1);
//    }
}