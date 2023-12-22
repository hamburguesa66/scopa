package com.hamburguesa66.scopa.handlers

import com.hamburguesa66.scopa.domain.Card
import java.net.URL

class ResourceHandler {
    enum class CardSpriteType(val subPath: String) {
        NORMAL(""),
        HOVER("hovered/"),
        HOVER_ALT("canceled/"),
        SELECT("selected/")
    }
    enum class Sprite(val url: String) {
        GAME_LOGO("/game-logo.png"),
        COMPANY_LOGO("/company-logo.png"),
        FACE_DOWN_CARD("/card-back.png"),
        DECK("/deck.png"),
        SKELETON_AVATAR("/avatar.png"),
    }
    companion object {
        fun getCardSprite(aCard: Card, type: CardSpriteType) : URL {
            var rootFolder = "/${aCard.suite.toString().lowercase()}/"
            rootFolder = rootFolder.plus(type.subPath)
            val fileName = "${aCard.score}.png"
            return getResource("${rootFolder}${fileName}")
        }
        fun getSprite(sprite: Sprite) = getResource(sprite.url)
        private fun getResource(path: String) : URL =
            ResourceHandler::class.java.getResource(path)!!
    }

}