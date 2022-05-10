package br.com.rorizinfo.peladaorganizada.di

import br.com.rorizinfo.peladaorganizada.viewmodel.settings.SettingsViewModel
import br.com.rorizinfo.peladaorganizada.data.persistence.LocalPreference
import br.com.rorizinfo.peladaorganizada.domain.usecase.AddNewPlayersUseCase
import br.com.rorizinfo.peladaorganizada.domain.usecase.CreateGameUseCase
import br.com.rorizinfo.peladaorganizada.repository.HistoryGameRepository
import br.com.rorizinfo.peladaorganizada.repository.PlayersPayRepository
import br.com.rorizinfo.peladaorganizada.viewmodel.game.GameViewModel
import br.com.rorizinfo.peladaorganizada.viewmodel.history.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val projectModules = module {
    viewModel { SettingsViewModel(get(), get(), get(), get(), get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { parameters -> GameViewModel(get(), get(), get(), get(), get(), parameters.get()) }
    single { LocalPreference(get()) }
    single { CreateGameUseCase() }
    single { AddNewPlayersUseCase() }
    single { HistoryGameRepository() }
    single { PlayersPayRepository() }
}
