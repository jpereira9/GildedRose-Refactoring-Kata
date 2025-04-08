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

    private GildedRose app;

    @Test
    void testNormalItemQualityDegradationBeforeSellIn() {
        // Given
        givenNameSellInAndQualityOfItem(NORMAL_ITEM, 10, 10);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(NORMAL_ITEM, 9, 9);
    }

    @Test
    void testNormalItemQualityDegradationAfterSellIn() {
        // Given
        givenNameSellInAndQualityOfItem(NORMAL_ITEM, 0, 10);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(NORMAL_ITEM, -1, 8);
    }

    @Test
    void testNormalItemQualityNotUnderMinimumBeforeSellIn() {
        // Given
        givenNameSellInAndQualityOfItem(NORMAL_ITEM, 10, MINIMUM_QUALITY);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(NORMAL_ITEM, 9, MINIMUM_QUALITY);
    }

    @Test
    void testNormalItemQualityNotUnderMinimumAfterSellIn() {
        // Given
        givenNameSellInAndQualityOfItem(NORMAL_ITEM, 0, MINIMUM_QUALITY);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(NORMAL_ITEM, -1, MINIMUM_QUALITY);
    }

    @Test
    void testAgedBrieQualityIncrease() {
        // Given
        givenNameSellInAndQualityOfItem(AGED_BRIE, 10, 10);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(AGED_BRIE, 9, 11);
    }

    @Test
    void testAgedBrieQualityNotOverMaximum() {
        // Given
        givenNameSellInAndQualityOfItem(AGED_BRIE, 10, MAXIMUM_QUALITY);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(AGED_BRIE, 9, MAXIMUM_QUALITY);
    }

    @Test
    void testSulfurasNotChanged() {
        // Given
        givenNameSellInAndQualityOfItem(SULFURAS, 10, SULFURAS_QUALITY);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(SULFURAS, 10, SULFURAS_QUALITY);
    }

    @Test
    void testBackstagePassesQualityIncreaseBy1WhenOver10DaysSellIn() {
        // Given
        givenNameSellInAndQualityOfItem(BACKSTAGE_PASSES, 12, 10);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(BACKSTAGE_PASSES, 11, 11);
    }

    @Test
    void testBackstagePassesQualityIncreaseBy2WhenBetween10And5DaysSellIn() {
        // Given
        givenNameSellInAndQualityOfItem(BACKSTAGE_PASSES, 8, 10);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(BACKSTAGE_PASSES, 7, 12);
    }

    @Test
    void testBackstagePassesQualityIncreaseBy3WhenBetween5And0DaysSellIn() {
        // Given
        givenNameSellInAndQualityOfItem(BACKSTAGE_PASSES, 3, 10);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(BACKSTAGE_PASSES, 2, 13);
    }

    @Test
    void testBackstagePassesQuality0WhenSellInPassed() {
        // Given
        givenNameSellInAndQualityOfItem(BACKSTAGE_PASSES, 0, 10);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(BACKSTAGE_PASSES, -1, 0);
    }

    @Test
    void testBackstagePassesQualityNotOver50() {
        // Given
        givenNameSellInAndQualityOfItem(BACKSTAGE_PASSES, 10, MAXIMUM_QUALITY);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(BACKSTAGE_PASSES, 9, MAXIMUM_QUALITY);
    }

    @Test
    void testConjuredItemQualityDoubleDegradation() {
        // Given
        givenNameSellInAndQualityOfItem(CONJURED_ITEM, 10, 10);

        // When
        app.updateQuality();

        // Then
        thenItemBecomesNameSellInAndQuality(CONJURED_ITEM, 9, 8);
    }

    private void givenNameSellInAndQualityOfItem(String name, int sellIn, int quality) {
        Item item = new Item(name, sellIn, quality);
        Item[] items = new Item[] { item };
        app = new GildedRose(items);
    }

    private void thenItemBecomesNameSellInAndQuality(String name, int sellIn, int quality) {
        assertEquals(name, app.items[0].name);
        assertEquals(sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);
    }
}
