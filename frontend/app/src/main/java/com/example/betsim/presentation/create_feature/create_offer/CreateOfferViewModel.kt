package com.example.betsim.presentation.create_feature.create_offer


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.data.local.SessionManager
import com.example.betsim.data.model.OddItem
import com.example.betsim.data.model.OfferType
import com.example.betsim.presentation.common.util.TextFieldState
import com.example.betsim.presentation.common.util.validateDoubleInput
import com.example.betsim.presentation.common.util.validateTextFieldState
import com.example.betsim.presentation.create_feature.CreationEvent
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject
import kotlin.math.min
import kotlin.math.roundToInt

@HiltViewModel
class CreateOfferViewModel @Inject constructor(
    private val repository: BetSimRepository,
    private val sessionManager: SessionManager,
    savedStateHandle: SavedStateHandle
): ViewModel() {


    private val _dateTextField = mutableStateOf(TextFieldState<LocalDate?>(value = null))
    val dateTextField: State<TextFieldState<LocalDate?>> = _dateTextField

    private val _timeTextField = mutableStateOf(TextFieldState<LocalTime?>(value = null))
    val timeTextField: State<TextFieldState<LocalTime?>>  = _timeTextField

    private val _type = mutableStateOf(OfferType.Match)
    val type: State<OfferType> = _type

    private val _offerName = mutableStateOf(TextFieldState(value = ""))
    val offerName: State<TextFieldState<String>> = _offerName

    private val _drawable = mutableStateOf(false)
    val drawable: State<Boolean> = _drawable

    private val _odds = mutableStateListOf(OddState(), OddState())
    val odds: List<OddState> = _odds

    private val _remaining = mutableStateOf("100")
    val remaining: State<String> = _remaining

    private val _actionPossible = mutableStateOf(true)
    val actionPossible: State<Boolean> = _actionPossible

    private val _eventCreated = mutableStateOf(false)
    val eventCreated: State<Boolean> = _eventCreated

    private val _createErrorText = mutableStateOf("")
    val createErrorText: State<String> = _createErrorText

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String> = _toastMessage

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _id = mutableIntStateOf(-1)
    val id: State<Int> = _id

    init {
        _id.intValue = checkNotNull(savedStateHandle["id"])
    }

    fun onEvent(event: CreationEvent){
        when(event){
            CreationEvent.CreateClick -> {
                if(!checkInput(true)) return
                onCreate()
            }

            CreationEvent.AddTeam -> {
                if (_odds.size > 15) return
                _odds.add(
                    OddState(
                        name = TextFieldState(if (_drawable.value) "remis" else "")
                    )
                )
            }

            is CreationEvent.EnteredDate -> {
                if(validateDate(event.date))
                    _dateTextField.value = _dateTextField.value.copy(value = event.date)
                else
                    _toastMessage.value = "Podaj datę z przyszłości"

            }

            is CreationEvent.EnteredTime -> {
                if(validateTime(event.time))
                    _timeTextField.value = _timeTextField.value.copy(value = event.time)
                else
                    _toastMessage.value = "Podaj datę z przyszłości"
            }

            is CreationEvent.SelectDropdown -> {
                if (type.value == event.offerType!!) return
                _type.value = event.offerType
                _odds.subList(2, _odds.size).clear()
                _drawable.value = false
                _offerName.value = _offerName.value.copy(value = "", isError = false, errorText = "")
            }

            is CreationEvent.EnteredTeamName -> {
                val name = _odds[event.id].name.copy(value = event.name)
                _odds[event.id] = _odds[event.id].copy(name = name)
            }

            is CreationEvent.EnteredWinChance -> {
                val oldOdd = _odds[event.id].odd.value.replace(',','.').toDoubleOrNull() ?: 0.0 //9
                val remainingValue = _remaining.value.replace(',','.').toDouble() //91
                val min = min(100.0, remainingValue+oldOdd)
                val odd = validateDoubleInput(event.odd, min) ?: return
                val oddState = _odds[event.id].odd.copy(value = odd)
                _odds[event.id] = _odds[event.id].copy(odd = oddState)
                updateRemaining()
            }

            is CreationEvent.CheckBoxChange -> {
                _drawable.value = !_drawable.value
                if (_drawable.value) onEvent(CreationEvent.AddTeam)
                else (onEvent(CreationEvent.RemoveTeam(_odds[2])))
            }

            is CreationEvent.RemoveTeam -> {
                if ( _odds.size <= 2) return
                _odds.remove(event.team)
                updateRemaining()
            }

            is CreationEvent.EnteredName -> {
                _offerName.value = _offerName.value.copy(value = event.value)
            }
            CreationEvent.NavigateFurtherClick -> {
                _actionPossible.value = checkInput(false)
            }

        }
    }

    private fun updateRemaining(){
        var base = 100.0
        for (oddValue in _odds){
            val odd = oddValue.odd.value.replace(',','.').toDoubleOrNull() ?: continue
            base -= odd
        }
        _remaining.value = "%,2f".format(base).trimEnd('0').trimEnd(',')
    }


    private fun checkInput(creation: Boolean): Boolean {

        var correct = true

        if (creation){
            when(validateOdds()){
                0 -> {
                    _createErrorText.value = ""
                }
                1 -> {
                    _createErrorText.value = "Odsetek szansy musi być większy od 0"
                    correct = false
                }
                else -> {
                    _createErrorText.value = "Uzupełnij wszystkie pola"
                    correct = false
                }
            }

            if (correct && _remaining.value.toDoubleOrNull() != 0.0){
                _createErrorText.value = "Suma szans na wygraną musi wynieść 100%"
                correct = false
            }

        }
        else{
            _dateTextField.value = validateTextFieldState(_dateTextField.value)
            _timeTextField.value = validateTextFieldState(_timeTextField.value)
            if (type.value == OfferType.Selection) {
                _offerName.value = validateTextFieldState(_offerName.value)
            }
            correct = !_dateTextField.value.isError && !_timeTextField.value.isError && !_offerName.value.isError
        }

        return correct
    }

    private fun validateOdds(): Int {
        var type = 0

        for (i in _odds.indices) {
            val oddState = _odds[i]
            var oddError = false
            var nameError = false
            val name = oddState.name
            val odd = oddState.odd

            if (name.value.isBlank()) {
                nameError = true
                type = 2
            }
            if (odd.value.isBlank()) {
                oddError = true
                type = 2
            } else if (odd.value.toDoubleOrNull() == 0.0) {
                oddError = true
                if (type == 0) type = 1
            }

            if (oddError != odd.isError || nameError != name.isError) {
                _odds[i] = OddState(name.copy(isError = nameError), odd.copy(isError = oddError))
            }
        }

        return type
    }

    private fun onCreate(){
        viewModelScope.launch {

            val title = if (_type.value == OfferType.Selection) _offerName.value.value
                        else "${_odds[0].name.value} - ${_odds[1].name.value}"
            val dateTime = LocalDateTime.of(_dateTextField.value.value, _timeTextField.value.value)
            val oddItems = createOddItems()
            val login = sessionManager.getCurrent()
            if (login != null) {
                val response = repository.postOffer(
                    token = login.accessToken,
                    id = _id.intValue,
                    title = title,
                    offerType = _type.value.ordinal,
                    dateTime = dateTime.toString(),
                    odds = oddItems
                )
                if (response){
                    _toastMessage.value = "Utworzono"
                    _eventCreated.value = true
                }else{
                    _toastMessage.value = "Coś poszło nie tak"
                    _isLoading.value = false
                }
            }else{
                _toastMessage.value = "Coś poszło nie tak"
                _isLoading.value = false
            }
        }

    }

    private fun validateDate(date: LocalDate): Boolean{
        if (_timeTextField.value.value == null){
            return !LocalDate.now().isAfter(date)
        }
        return LocalDateTime.of(date, _timeTextField.value.value).isAfter(LocalDateTime.now())
    }

    private fun validateTime(time: LocalTime): Boolean{
        if (_dateTextField.value.value == null) return true
        return LocalDateTime.of(_dateTextField.value.value, time).isAfter(LocalDateTime.now())
    }

    private fun createOddItems(): List<OddItem>{

        val oddItems = mutableListOf<OddItem>()
        _odds.forEach {
            oddItems.add(convertOddStateToOddItem(it))
        }

        if (_type.value == OfferType.Match && oddItems.size == 3)
            return listOf(oddItems[0], oddItems[2], oddItems[1])

        return oddItems
    }

    private fun convertOddStateToOddItem(oddState: OddState): OddItem{

        val name = oddState.name.value
        val percentage = oddState.odd.value.replace(',','.').toDouble()
        val oddValue = (10000 / percentage).roundToInt().toDouble() / 100
        return OddItem(name, oddValue)

    }

    fun clearToast(){
        _toastMessage.value = ""
    }

}