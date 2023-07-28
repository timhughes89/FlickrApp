package com.flickr.data.utils

object ImageUtils {


    fun buildImageUrl(server: String, id: String, secret: String): String {
        return "https://live.staticflickr.com/${server}/${id}_${secret}_w.jpg"
    }

    /**
     * If the icon server is greater than zero, the url takes the following format:
     * http://farm{icon-farm}.staticflickr.com/{icon-server}/buddyicons/{nsid}.jpg
     * else the following url should be used:
     * https://www.flickr.com/images/buddyicon.gif
     */
    fun buildIconUrl(farm: Int, server: String, id: String): String {
        return "http://farm$farm.staticflickr.com/$server/buddyicons/$id.jpg"
    }
}