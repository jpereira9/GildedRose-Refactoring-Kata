package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.ConstantsUtils.AGED_BRIE;
import static com.gildedrose.ConstantsUtils.BACKSTAGE_PASSES;
import static com.gildedrose.ConstantsUtils.CONJURED_ITEM;
import static com.gildedrose.ConstantsUtils.MAXIMUM_QUALITY;
import static com.gildedrose.ConstantsUtils.MINIMUM_QUALITY;
import static com.gildedrose.ConstantsUtils.SULFURAS;
import static com.gildedrose.ConstantsUtils.SULFURAS_QUALITY;
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
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
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
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
    }

    @Test
    void testNormalItemQualityNotUnderMinimum() {
        // Given
        Item item = new Item(NORMAL_ITEM, 10, MINIMUM_QUALITY);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(NORMAL_ITEM, 9, MINIMUM_QUALITY);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
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
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
    }

    @Test
    void testAgedBrieQualityNotOverMaximum() {
        // Given
        Item item = new Item(AGED_BRIE, 10, MAXIMUM_QUALITY);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(AGED_BRIE, 9, MAXIMUM_QUALITY);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
    }

    @Test
    void testSulfurasNotChanged() {
        // Given
        Item item = new Item(SULFURAS, 10, SULFURAS_QUALITY);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(SULFURAS, 10, 80);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
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
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
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
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
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
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
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
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
    }

    @Test
    void testBackstagePassesQualityNotOver50() {
        // Given
        Item item = new Item(BACKSTAGE_PASSES, 10, MAXIMUM_QUALITY);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(BACKSTAGE_PASSES, 9, MAXIMUM_QUALITY);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
    }

    @Test
    void testConjuredItemQualityDoubleDegradation() {
        // Given
        Item item = new Item(CONJURED_ITEM, 10, 10);
        Item[] items = new Item[] { item };
        Item itemExpected = new Item(CONJURED_ITEM, 9, 8);
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(itemExpected.sellIn, app.items[0].sellIn);
        assertEquals(itemExpected.quality, app.items[0].quality);
    }
}
