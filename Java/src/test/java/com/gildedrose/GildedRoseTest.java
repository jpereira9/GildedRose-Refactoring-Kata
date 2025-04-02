package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void qualityAndSellInDecreased() {
        // Given
        Item foo = new Item("foo", 10, 10);
        Item agedBrie1 = new Item( "Aged Brie", 10, 10);
        Item agedBrie2 = new Item( "Aged Brie", 10, 50);
        Item item1 = new Item("item", 10, 0);
        Item item2 = new Item("item", 0, 10);
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 2, 30);
        Item concertTickets1 = new Item("Backstage passes to a TAFKAL80ETC concert", 12, 30);
        Item concertTickets2 = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 30);
        Item concertTickets3 = new Item("Backstage passes to a TAFKAL80ETC concert", 2, 30);
        Item concertTickets4 = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 30);
        Item[] items = new Item[] {foo, agedBrie1, agedBrie2, item1, item2, sulfuras, concertTickets1, concertTickets2, concertTickets3, concertTickets4};
        GildedRose gildedRoseItem = new GildedRose(items);

        // When
        gildedRoseItem.updateQuality();

        // Then
        assertEquals(9, gildedRoseItem.items[0].quality);
        assertEquals(9, gildedRoseItem.items[0].sellIn);
        assertEquals(11, gildedRoseItem.items[1].quality);
        assertEquals(9, gildedRoseItem.items[1].sellIn);
        assertEquals(50, gildedRoseItem.items[2].quality);
        assertEquals(9, gildedRoseItem.items[2].sellIn);
        assertEquals(0, gildedRoseItem.items[3].quality);
        assertEquals(9, gildedRoseItem.items[3].sellIn);
        assertEquals(8, gildedRoseItem.items[4].quality);
        assertEquals(-1, gildedRoseItem.items[4].sellIn);
        assertEquals(30, gildedRoseItem.items[5].quality);
        assertEquals(2, gildedRoseItem.items[5].sellIn);
        assertEquals(31, gildedRoseItem.items[6].quality);
        assertEquals(11, gildedRoseItem.items[6].sellIn);
        assertEquals(32, gildedRoseItem.items[7].quality);
        assertEquals(5, gildedRoseItem.items[7].sellIn);
        assertEquals(33, gildedRoseItem.items[8].quality);
        assertEquals(1, gildedRoseItem.items[8].sellIn);
        assertEquals(0, gildedRoseItem.items[9].quality);
        assertEquals(-2, gildedRoseItem.items[9].sellIn);
    }

}
