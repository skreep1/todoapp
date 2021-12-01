package com.skreep.todoapp.viewmodel


import androidx.lifecycle.*
import com.skreep.todoapp.model.Note
import com.skreep.todoapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val allNote: LiveData<List<Note>> = repository.allWords.asLiveData()

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
}

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
