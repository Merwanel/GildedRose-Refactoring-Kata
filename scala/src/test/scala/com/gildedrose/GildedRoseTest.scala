package com.gildedrose

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GildedRoseTest  extends AnyFlatSpec with Matchers {
      "The creation of an item" should "work" in {
        val items = Array[Item](new Item("basicItem", sellIn=20, quality=30))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).name should equal ("basicItem")
      }
      "A basicItem" should "have its quality and sellIn values decreased by one after one day" in {
        val originalQuality = 30
        val originalSellIn = 20
        val items = Array[Item](new Item("basicItem", sellIn=originalSellIn, quality=originalQuality))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).quality should equal (originalQuality - 1)
        app.items(0).sellIn should equal (originalSellIn - 1)
      }
      "A basicItem" should "have its quality and sellIn values superior or equal to zero" in {
        val originalQuality = 0
        val items = Array[Item](new Item("basicItem", sellIn=0, quality=originalQuality))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).quality should equal (originalQuality)
      }
      "A basicItem" should "have its quality values decreased by 2 when the sell date has passed" in {
        val originalQuality = 30
        val originalSellIn = 0
        val items = Array[Item](new Item("basicItem", sellIn=originalSellIn, quality=originalQuality))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).quality should equal (originalQuality - 2)
        app.items(0).sellIn should equal (originalSellIn - 1)
      }
      "An Aged Brie" should "have its quality increased by one after one day" in {
        val originalQuality = 30
        val items = Array[Item](new Item("Aged Brie", sellIn=20, quality=originalQuality))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).quality should equal (originalQuality + 1)
      }
      "Sulfuras, Hand of Ragnaros" should "never has to decrease in quality" in {
        val originalQuality = 30
        val items = Array[Item](new Item("Sulfuras, Hand of Ragnaros", sellIn=42, quality=originalQuality))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).quality should equal (originalQuality)
      }
      "A Backstage passes to a TAFKAL80ETC concert" should "increase in Quality by when Sellin > 10" in {
        val originalQuality = 30
        val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", sellIn=42, quality=originalQuality))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).quality should equal (originalQuality + 1)
      }
      "A Backstage passes to a TAFKAL80ETC concert" should "increase in Quality by 2 when 10 <= SellIn < 5" in {
        val originalQuality = 30
        val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", sellIn=8, quality=originalQuality))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).quality should equal (originalQuality + 2)
      }
      "A Backstage passes to a TAFKAL80ETC concert" should "increase in Quality by 3 when 5 <= SellIn <= 0" in {
        val originalQuality = 30
        val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", sellIn=3, quality=originalQuality))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).quality should equal (originalQuality + 3)
      }
      "A Backstage passes to a TAFKAL80ETC concert" should "have a Quality of 0 after the concert" in {
        val originalQuality = 30
        val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", sellIn=0, quality=originalQuality))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).quality should equal (0)
      }
      "A Conjured item" should "have a Quality degrading by 2 everyday, if sellIn >= 0" in {
        val originalQuality = 30
        val items = Array[Item](new Item("Conjured item", sellIn=42, quality=originalQuality))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).quality should equal (originalQuality - 2)
      }
      "A Conjured item" should "have a Quality degrading by 2 everyday, if sellIn < 0" in {
        val originalQuality = 30
        val items = Array[Item](new Item("Conjured item", sellIn=0, quality=originalQuality))
        val app = new GildedRose(items)
        app.updateQuality()
        app.items(0).quality should equal (originalQuality - 4)
      }
}