package com.itis.android_homework.repositories

import com.itis.android_homework.models.Fruit

object FruitRepository {
    private const val fruitsURL = "https://ufeelgood.ru/upload/iblock/eae/eae20c26143f31036d1acbcde61d5f75.jpg"

    var fruits = arrayListOf(
        Fruit(
            "Apple",
            "A, С, В1, В2, РР, Е",
            arrayListOf(
                "https://static9.depositphotos.com/1011549/1208/i/600/depositphotos_12089121-stock-photo-green-apple-with-leaf.jpg",
                "https://m.dom-eda.com/uploads/images/catalog/item/86df51de21/c25c94fe96_1000.jpg",
                "https://chudesalegko.ru/wp-content/uploads/2013/07/yabloko.jpg"
            )
        ),
        Fruit(
            "Orange",
            "A, В1, B2, B5, B6, B9, C, Е, H, PP",
            arrayListOf(
                "https://img.perekrestok.ru/i/400x400-fit/xdelivery/files/cd/9e/e104dc0262fdbeab7999a21d6b7c.jpg",
                "https://sc04.alicdn.com/kf/U3f818dc61b164bd3996575580efd2b4b6.jpg",
                "https://assets.bonappetit.com/photos/58a37e2309ffa8f718634793/master/w_1600%2Cc_limit/tangelo-citrus.jpg"
            )
        ),
        Fruit(
            "Banana",
            "А, В1, В2, В6, В9, Е, РР",
            arrayListOf(
                "https://www.gastronom.ru/binfiles/images/20151029/bddcbbce.jpg",
                "https://static.libertyprim.com/files/familles/banane-large.jpg?1569271725",
                "https://static.turbosquid.com/Preview/2014/08/05__06_16_11/nmodel_03_09_01.jpgaa0c21c7-6874-4332-a26e-f6f15b5b871dLarge.jpg"
            )
        ),
        Fruit(
            "Lemon",
            "А, E, D, Р, C, B",
            arrayListOf(
                "https://media.healthyfood.com/wp-content/uploads/2017/03/In-season-August-Lemons-500x332.jpg",
                "https://i2.wp.com/agroarcade.com/wp-content/uploads/2020/06/Lemon-Whole-Qtr.jpg",
                "https://springfieldnutra.com/app/uploads/2017/12/Citrusvrucht-vrijstaand.jpg"
            )
        ),
        Fruit(
            "Avocado",
            "В, Е, А, С, К",
            arrayListOf(
                "https://californiaavocado.com/wp-content/uploads/2020/07/avocado-fruit-berry.jpg",
                "https://m.media-amazon.com/images/I/41ZPamxjAoL.jpg",
                "https://chefsmandala.com/wp-content/uploads/2018/03/Avocado.jpg"
            )
        ),
        Fruit(
            "Mango",
            "В, С, А, Е",
            arrayListOf(
                "https://plantogram.com/wa-data/public/shop/products/29/03/329/images/589/589.750@2x.jpg",
                "https://media.istockphoto.com/photos/whole-and-slice-ripe-mango-fruit-with-green-leaves-isolated-on-white-picture-id1008183290?k=20&m=1008183290&s=170667a&w=0&h=HioZnH-FGaaE2TkMAweJmc-cvulozl6eMeZAUdQavao=",
                "https://www.svz.com/wp-content/uploads/2018/05/Mango.jpg"
            )
        ),
        Fruit(
            "Peach",
            "В, С, Е, К, Н, PP",
            arrayListOf(
                "https://cdn.shopify.com/s/files/1/2971/2126/products/Peach_df4f4b9c-acd4-414c-b7b4-426b1dc51811_2000x.jpg?v=1594159335",
                "https://m.media-amazon.com/images/I/71gN9UmIBJL._SL1500_.jpg",
                "https://img3.goodfon.ru/wallpaper/nbig/1/4e/persiki-peaches-frukty-fruit.jpg"
            )
        )
    )

    fun addNewItem(index: Int, name: String, vitamins: String) {
        val fruit = Fruit(
            name,
            vitamins,
            arrayListOf(fruitsURL)
        )
        fruits.add(index, fruit)
    }

    fun deleteById(id: Int){
        fruits.removeAt(id)
    }
}
