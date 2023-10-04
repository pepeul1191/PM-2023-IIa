package pe.edu.ulima.pm20232.aulavirtual.screenmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pe.edu.ulima.pm20232.aulavirtual.models.Pokemon
import pe.edu.ulima.pm20232.aulavirtual.services.PokemonService

class HomeScreenViewModel: ViewModel(){
    //var pokemons = mutableStateListOf<Pokemon>()
    val generationsMap = mutableMapOf<Int, String>()

    private var _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> get() = _pokemons
    fun setPokemons(newItems: List<Pokemon>) {
        _pokemons.value = newItems
    }

    fun listAll(){
        val pokemonService: PokemonService = PokemonService()
        val list = pokemonService.listAll()
        setPokemons(list)
    }

    fun getGenerations(){
        for(p: Pokemon in _pokemons.value){
            val generationId = p.generationId
            val generationName = p.generationName
            if(!generationsMap.containsKey(generationId)){
                generationsMap[generationId] = generationName
            }
        }
    }

    fun filterByGenerations(generationId: Int){
        val pokemonService: PokemonService = PokemonService()
        val list = pokemonService.pokemonListByGenerationId(generationId)
        setPokemons(list)
    }
}