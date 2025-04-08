package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.KnownItemsUtils.AGED_BRIE;
import static com.gildedrose.KnownItemsUtils.BACKSTAGE_PASSES;
import static com.gildedrose.KnownItemsUtils.SULFURAS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private static final String NORMAL_ITEM = "Normal item";

    @Test
    void testNormalItemQualityDegradationBeforeSellIn() {
        // Given
        Item item = new Item(NORMAL_ITEM, 10, 10);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(NORMAL_ITEM, 9, 9);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.getSellIn(), app.items[0].getSellIn());
        assertEquals(itemExpected.getQuality(), app.items[0].getQuality());
    }

    @Test
    void testNormalItemQualityDegradationAfterSellIn() {
        // Given
        Item item = new Item(NORMAL_ITEM, 0, 10);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(NORMAL_ITEM, -1, 8);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.getSellIn(), app.items[0].getSellIn());
        assertEquals(itemExpected.getQuality(), app.items[0].getQuality());
    }

    @Test
    void testNormalItemQualityNotNegative() {
        // Given
        Item item = new Item(NORMAL_ITEM, 10, 0);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(NORMAL_ITEM, 9, 0);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.getSellIn(), app.items[0].getSellIn());
        assertEquals(itemExpected.getQuality(), app.items[0].getQuality());
    }

    @Test
    void testAgedBrieQualityIncrease() {
        // Given
        Item item = new Item(AGED_BRIE, 10, 10);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(AGED_BRIE, 9, 11);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.getSellIn(), app.items[0].getSellIn());
        assertEquals(itemExpected.getQuality(), app.items[0].getQuality());
    }

    @Test
    void testAgedBrieQualityNotOver50() {
        // Given
        Item item = new Item(AGED_BRIE, 10, 50);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(AGED_BRIE, 9, 50);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.getSellIn(), app.items[0].getSellIn());
        assertEquals(itemExpected.getQuality(), app.items[0].getQuality());
    }

    @Test
    void testSulfurasNotChanged() {
        // Given
        Item item = new Item(SULFURAS, 10, 150);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(SULFURAS, 10, 150);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.getSellIn(), app.items[0].getSellIn());
        assertEquals(itemExpected.getQuality(), app.items[0].getQuality());
    }

    @Test
    void testBackstagePassesQualityIncreaseBy1WhenOver10DaysSellIn() {
        // Given
        Item item = new Item(BACKSTAGE_PASSES, 12, 10);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(BACKSTAGE_PASSES, 11, 11);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.getSellIn(), app.items[0].getSellIn());
        assertEquals(itemExpected.getQuality(), app.items[0].getQuality());
    }

    @Test
    void testBackstagePassesQualityIncreaseBy2WhenBetween10And5DaysSellIn() {
        // Given
        Item item = new Item(BACKSTAGE_PASSES, 8, 10);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(BACKSTAGE_PASSES, 7, 12);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.getSellIn(), app.items[0].getSellIn());
        assertEquals(itemExpected.getQuality(), app.items[0].getQuality());
    }

    @Test
    void testBackstagePassesQualityIncreaseBy3WhenBetween5And0DaysSellIn() {
        // Given
        Item item = new Item(BACKSTAGE_PASSES, 3, 10);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(BACKSTAGE_PASSES, 2, 13);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.getSellIn(), app.items[0].getSellIn());
        assertEquals(itemExpected.getQuality(), app.items[0].getQuality());
    }

    @Test
    void testBackstagePassesQuality0WhenSellInPassed() {
        // Given
        Item item = new Item(BACKSTAGE_PASSES, 0, 10);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(BACKSTAGE_PASSES, -1, 0);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.getSellIn(), app.items[0].getSellIn());
        assertEquals(itemExpected.getQuality(), app.items[0].getQuality());
    }
}
