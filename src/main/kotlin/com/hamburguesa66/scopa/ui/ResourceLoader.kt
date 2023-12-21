package com.hamburguesa66.scopa.ui

import com.hamburguesa66.scopa.domain.Card
import java.net.URL

class ResourceLoader {
    enum class CardSpriteType {
        NORMAL, HOVER, HOVER_ALT, SELECT
    }
    companion object {
        fun getCardSprite(aCard: Card, type: CardSpriteType) : URL {
            var rootFolder = "/${aCard.suite.toString().lowercase()}/"
            rootFolder = when(type) {
                CardSpriteType.HOVER -> rootFolder.plus("hovered/")
                CardSpriteType.HOVER_ALT -> rootFolder.plus("canceled/")
                CardSpriteType.SELECT -> rootFolder.plus("selected/")
                CardSpriteType.NORMAL -> rootFolder
            }
            val fileName = "${aCard.score}.png"
            return ResourceLoader::class.java.getResource("${rootFolder}${fileName}")!!
        }
        fun getFaceDownCardSprite() : URL {
            return ResourceLoader::class.java.getResource("/card-back.png")!!
        }
        fun getAvatarSprite() : URL {
            return ResourceLoader::class.java.getResource("/avatar.png")!!
        }
        fun getDeckSprite() : URL {
            return ResourceLoader::class.java.getResource("/deck.png")!!
        }
    }

}