package com.gildedrose;

import static com.gildedrose.ConstantsUtils.AGED_BRIE;
import static com.gildedrose.ConstantsUtils.BACKSTAGE_PASSES;
import static com.gildedrose.ConstantsUtils.CONJURED_ITEM;
import static com.gildedrose.ConstantsUtils.MAXIMUM_QUALITY;
import static com.gildedrose.ConstantsUtils.MINIMUM_QUALITY;
import static com.gildedrose.ConstantsUtils.STANDARD_QUALITY_DEGRADATION;
import static com.gildedrose.ConstantsUtils.SULFURAS;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case AGED_BRIE:
                    item.sellIn -= 1;
                    agedBrieQualityChange(item);
                    break;
                case SULFURAS:
                    break;
                case BACKSTAGE_PASSES:
                    item.sellIn -= 1;
                    backstagePassesQualityChange(item);
                    break;
                case CONJURED_ITEM:
                    item.sellIn -= 1;
                    conjuredItemsQualityChange(item);
                    break;
                default:
                    item.sellIn -= 1;
                    normalItemsQualityChange(item);
                    break;
            }
        }
    }

    private static void agedBrieQualityChange(Item item) {
        if (item.quality < MAXIMUM_QUALITY) {
            item.quality += STANDARD_QUALITY_DEGRADATION;
        }
    }

    private void backstagePassesQualityChange(Item item) {
        if (item.sellIn > 0 && item.quality < MAXIMUM_QUALITY) {
            if (item.sellIn > 10) {
                item.quality += STANDARD_QUALITY_DEGRADATION;
            } else if (item.sellIn > 5) {
                item.quality += STANDARD_QUALITY_DEGRADATION * 2;
            } else {
                item.quality += STANDARD_QUALITY_DEGRADATION * 3;
            }
        } else if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private void normalItemsQualityChange(Item item) {
        standardQualityChange(item, 1);
    }

    private void conjuredItemsQualityChange(Item item) {
        standardQualityChange(item, 2);
    }

    private void standardQualityChange (Item item, int degradationMultiplier) {
        if (item.sellIn > 0 && item.quality > MINIMUM_QUALITY) {
            item.quality -= STANDARD_QUALITY_DEGRADATION * degradationMultiplier;
        } else if (item.sellIn < 0) {
            item.quality -= STANDARD_QUALITY_DEGRADATION * 2 * degradationMultiplier;
            if (item.quality < MINIMUM_QUALITY) {
                item.quality = MINIMUM_QUALITY;
            }
        }
    }
}
