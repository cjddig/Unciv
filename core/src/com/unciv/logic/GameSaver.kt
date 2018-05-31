package com.unciv.logic

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.Json
import com.unciv.ui.GameSettings

class GameSaver {
    private val saveFilesFolder = "SaveFiles"

    fun getSave(GameName: String): FileHandle {
        return Gdx.files.local("$saveFilesFolder/$GameName")
    }

    fun getSaves(): List<String> {
        return Gdx.files.local(saveFilesFolder).list().map { it.name() }
    }

    fun saveGame(game: GameInfo, GameName: String) {
        getSave(GameName).writeString(Json().toJson(game), false)
    }

    fun loadGame(GameName: String) : GameInfo {
        val game = Json().fromJson(GameInfo::class.java, getSave(GameName).readString())
        game.setTransients()
        return game
    }

    fun deleteSave(GameName: String){
        getSave(GameName).delete()
    }

    fun getGeneralSettings():GameSettings{
        return Json().fromJson(GameSettings::class.java, Gdx.files.local("GameSettings.json"))
    }

    fun setGeneralSettings(gameSettings: GameSettings){
        Gdx.files.local("GameSettings.json").writeString(Json().toJson(gameSettings), false)
    }
}
