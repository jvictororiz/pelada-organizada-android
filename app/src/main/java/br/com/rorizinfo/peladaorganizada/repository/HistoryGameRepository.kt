package br.com.rorizinfo.peladaorganizada.repository

import br.com.rorizinfo.peladaorganizada.domain.model.RoudModel

class HistoryGameRepository {
    private var historyRounds = mutableListOf<RoudModel>()
    
    fun addHistory(round: RoudModel) {
        historyRounds.add(round)
    }
    
    fun getAllHistories() = historyRounds
    fun clearAll() {
        historyRounds.clear()
    }
    
}