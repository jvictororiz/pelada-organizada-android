package br.com.rorizinfo.peladaorganizada.viewmodel.history

import androidx.lifecycle.ViewModel
import br.com.rorizinfo.peladaorganizada.repository.HistoryGameRepository
import br.com.rorizinfo.peladaorganizada.viewmodel.common.SingleLiveEvent
import br.com.rorizinfo.peladaorganizada.viewmodel.history.model.HistoryGamesEvent

class HistoryViewModel(
    repository: HistoryGameRepository
) : ViewModel() {
    
    val eventLiveData = SingleLiveEvent<HistoryGamesEvent>()
    
    init {
        val list = repository.getAllHistories()
        if (list.isEmpty()) {
            HistoryGamesEvent.EmptyList.run()
        } else {
            HistoryGamesEvent.LoadGames(
                list
            ).run()
            
        }
    }
    
    private fun HistoryGamesEvent.run() {
        eventLiveData.value = this
    }
}