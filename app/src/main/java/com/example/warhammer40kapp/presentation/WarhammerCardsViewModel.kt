package com.example.warhammer40kapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warhammer40kapp.model.domain.DomainCard
import com.example.warhammer40kapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class WarHammerCardsUiState(
    val cards: List<DomainCard> = emptyList(),
    val query: String = ""
)

@HiltViewModel
class WarhammerCardsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val _cards = mainRepository.getCardsStream()

    val uiState: StateFlow<WarHammerCardsUiState> = combine(
        _query, _cards,
    )
    { query, cards ->
        WarHammerCardsUiState(
            cards = filterItems(cards, query),
            query = query
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = WarHammerCardsUiState(query = "")
    )

    init {
        viewModelScope.launch {
                mainRepository.updateCards()
                mainRepository.updateWarbands()
                mainRepository.updateSets()
        }
    }

    fun onQueryChanged(query: String) {
        this._query.value = query
    }

    fun onFavoriteCard(isFavorite: Boolean, id:String) = viewModelScope.launch {
        mainRepository.updateFavorite(isFavorite, id)
    }

    private fun filterItems(cards: List<DomainCard>, query: String): List<DomainCard> {
        val filteredCards = ArrayList<DomainCard>()
        cards.forEach { card ->
            val ifCardNameOrRuleTextOrColorTextContainsQuery =
                card.name.contains(query, true) ||
                        card.rulesText.contains(query, true) ||
                        card.colourText.contains(query, true)

            if (ifCardNameOrRuleTextOrColorTextContainsQuery) {
                filteredCards += card
            }
        }
        return filteredCards
    }
}

